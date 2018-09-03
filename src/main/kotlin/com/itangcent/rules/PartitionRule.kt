package com.itangcent.rules

import com.itangcent.*

import java.util.Arrays

/**
 * Created by TangMing on 2017/4/8.
 * 抽象规则：分成九组，分别对每组进行校验
 */
abstract class PartitionRule : SudokuRule {

    //cache previous
    private var group = 0//组号

    //当前标记
    //optionsFlags[i] = j
    //j=-1:没有空格可以填i
    //j=-2:超过一个空格可以填i
    //j=other:j处填i
    protected var optionsFlags = IntArray(9)

    override fun check(sudokuData: SudokuData): Boolean {
        var result = false
        for (i in 0..8) {
            //该组已填写完毕
            val selected = selected(sudokuData, i)
            if (SudokuUtils.all(selected))
                continue

            Arrays.fill(optionsFlags, -1)
            val rGroup = SudokuUtils.shift(i, group)
            check(sudokuData, rGroup, selected)
            for (j in 0..8) {
                if (optionsFlags[j] != -1 && optionsFlags[j] != -2) {
                    if (!result) {
                        result = true
                        group = SudokuUtils.shift(rGroup, 1)
                    }
                    val value = Value(j + 1, SudokuUtils.index2XY(optionsFlags[j]))
                    sudokuData.sure(value)
                    SudokuUtils.printMsg("\n" + javaClass.simpleName + " sure:" + value.toString()
                            + "\noptionsFlags:" + Arrays.toString(optionsFlags))
                }
            }
        }
        return result
    }

    protected fun setFlag(number: Int, coordinate: Coordinate) {
        when (optionsFlags[number]) {
            -1 -> optionsFlags[number] = SudokuUtils.toIndex(coordinate)
            -2 -> {
            }
            else -> optionsFlags[number] = -2//multi
        }
    }

    abstract fun selected(sudokuData: SudokuData, num: Int): Int

    abstract fun check(sudokuData: SudokuData, num: Int, selected: Int)
}
