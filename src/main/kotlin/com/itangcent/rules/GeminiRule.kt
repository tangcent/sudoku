package com.itangcent.rules

import com.itangcent.*
import java.util.*
import java.util.function.Consumer
import kotlin.streams.toList

/**
 * Created by TangMing on 2017/4/8.
 */
class GeminiRule : SudokuRule {

    private val cache = HashSet<Int>()

    override fun check(sudokuData: SudokuData): Boolean {
        val resultHolder = Holder(false)
        val rests = sudokuData.rests().stream()
                .map<Coordinate> { SudokuUtils.index2XY(it) }
                .toList().toTypedArray()
        val optionals = sudokuData.optionals(rests)
        for (i in optionals.indices) {
            if (SudokuUtils.oneBits(optionals[i]) == 2) {//有两个可选项
                val optional = optionals[i]
                val coordinate = rests[i]
                SudokuUtils.sibling(rests[i], Consumer { sibling ->
                    //两个位置同时在两个相同的数字上选择
                    if (sudokuData.optional(sibling.x, sibling.y) == optional) {
                        if (SudokuUtils.cache(cache, build(coordinate, sibling))) {
                            resultHolder.target = true
                            ensure(sudokuData, coordinate, sibling, optional)
                        }
                    }
                })
            }
        }
        return resultHolder.target!!
    }

    /**
     * @param one     -第一个位置
     * @param another -第二个位置
     * @param options -擦除的选项
     */
    private fun ensure(sudokuData: SudokuData, one: Coordinate, another: Coordinate, options: Int) {
        val cache = HashSet<Int>()
        SudokuUtils.cache(cache, SudokuUtils.toIndex(one))
        SudokuUtils.cache(cache, SudokuUtils.toIndex(another))
        var ensureEleList: MutableList<Coordinate> = ArrayList<Coordinate>()
        if (SudokuUtils.sameRow(one, another)) {
            Collections.addAll(ensureEleList, *SudokuUtils.entireRow(one.x))
        }
        if (SudokuUtils.sameColumn(one, another)) {
            Collections.addAll(ensureEleList, *SudokuUtils.entireColumn(one.y))
        }
        if (SudokuUtils.sameBlock(one, another)) {
            Collections.addAll(ensureEleList, *SudokuUtils.entireBlock(SudokuUtils.toZ(one)))
        }
        ensureEleList = ensureEleList.stream()
                .filter { coordinate -> SudokuUtils.cache(cache, SudokuUtils.toIndex(coordinate)) }
                .toList().toMutableList()
        val ensureEles = sudokuData.todos(ensureEleList.toTypedArray())
        sudokuData.ensure(ensureEles, options)
    }

    private fun build(one: Coordinate, another: Coordinate): Int {
        val oneIndex = SudokuUtils.toIndex(one)
        val anotherIndex = SudokuUtils.toIndex(another)
        return (Integer.max(oneIndex, anotherIndex) shl 8) + Integer.min(oneIndex, anotherIndex)
    }
}
