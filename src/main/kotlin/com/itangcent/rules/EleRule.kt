package com.itangcent.rules

import com.itangcent.*
import kotlin.streams.toList

/**
 * Created by TangMing on 2017/4/8.
 * 规则:每列中每个数字必出现一次
 * 如果每列中某个数字只能出现在某格，则此格中数字可确定
 */
class EleRule : SudokuRule {

    override fun check(sudokuData: SudokuData): Boolean {
        val resultHolder = Holder(false)
        val rests = sudokuData.rests()
                .stream()
                .map<Coordinate> { SudokuUtils.index2XY(it) }
                .toList().toTypedArray()
        val optionals = sudokuData.optionals(rests)
        for (i in optionals.indices) {
            if (SudokuUtils.single(optionals[i])) {
                resultHolder.target = true
                val value = SudokuUtils.unbox(optionals[i])
                sudokuData.sure(Value(value, rests[i]))
            }
        }
        return resultHolder.target!!
    }
}
