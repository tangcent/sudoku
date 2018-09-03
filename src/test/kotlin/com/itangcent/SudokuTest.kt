package com.itangcent

/**
 * Created by TangMing on 2017/4/9.
 */
object SudokuTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val elements = SudokuUtils.string2Sudoku(
                "400000010" +
                        "032000700" +
                        "000003904" +
                        "607052090" +
                        "000070000" +
                        "040310805" +
                        "305800000" +
                        "008000130" +
                        "020000006")
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
