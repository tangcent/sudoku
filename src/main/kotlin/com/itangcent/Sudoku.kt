package com.itangcent

import com.itangcent.rules.*

import java.util.ArrayList

/**
 * Created by TangMing on 2017/4/9.
 */
class Sudoku(elements: Array<IntArray>) {
    private val sudokuExecutor: SudokuExecutor

    constructor(eles: String) : this(SudokuUtils.string2Sudoku(eles)) {}

    init {
        val sudokuData = SudokuData(elements)
        val sudokuRules = ArrayList<SudokuRule>()
        sudokuRules.add(EleRule())
        sudokuRules.add(BlockRule())
        sudokuRules.add(RowRule())
        sudokuRules.add(ColumnRule())
        sudokuRules.add(GeminiRule())
        sudokuExecutor = SudokuExecutor(sudokuData, sudokuRules)
    }

    fun start() {
        sudokuExecutor.start()
    }

    fun result(): Array<IntArray> {
        return sudokuExecutor.result()
    }
}
