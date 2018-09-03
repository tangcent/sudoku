package com.itangcent.rules

import com.itangcent.SudokuData
import com.itangcent.SudokuUtils
import java.util.function.Consumer

/**
 * Created by TangMing on 2017/4/8.
 * 规则:每行中每个数字必出现一次
 * 如果每行中某个数字只能出现在某格，则此格中数字可确定
 */
class RowRule : PartitionRule() {

    override fun selected(sudokuData: SudokuData, x: Int): Int {
        return sudokuData.sx(x)
    }

    override fun check(sudokuData: SudokuData, row: Int, selected: Int) {

        //收集空位
        val blanks = sudokuData.todos(SudokuUtils.entireRow(row))
        val optionals = sudokuData.optionals(blanks)
        for (i in optionals.indices) {
            SudokuUtils.forBit(optionals[i], Consumer { o -> setFlag(o, blanks[i]) })
        }
    }
}
