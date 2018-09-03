package com.itangcent

/**
 * Created by TangMing on 2017/4/8.
 */
interface SudokuRule {
    //是否有修改
    fun check(sudokuData: SudokuData): Boolean
}
