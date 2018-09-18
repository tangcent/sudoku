package com.itangcent

/**
 * Created by TangMing on 2017/4/9.
 */
object SudokuTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val elements = SudokuUtils.string2Sudoku(
                "700005010" +
                        "000070006" +
                        "008000430" +
                        "000000064" +
                        "002839500" +
                        "510000000" +
                        "064000100" +
                        "300080000" +
                        "020400009")
        print("new Sudoku:")
        SudokuUtils.print(elements)
        SudokuUtils.enableLog(true)
        val sudoku = Sudoku(elements)
        sudoku.start()
        val result = sudoku.result()
        print("\nresult:")
        SudokuUtils.print(result)
    }

}
