package com.itangcent

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by TangMing on 2017/4/9.
 */
object SudokuFileTest {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        BufferedReader(InputStreamReader(
                SudokuFileTest::class.java.classLoader.getResourceAsStream("sudoku.txt")
        )).use { reader ->
            var sb = StringBuilder()
            var cnt = 0
            var line: String? = reader.readLine()
            while (line != null) {
                val trimedLine = line.trim { it <= ' ' }
                line = reader.readLine()
                if (trimedLine.isEmpty() || trimedLine.startsWith("#")) {
                    continue
                }
                sb.append(trimedLine)
                if (++cnt == 9) {
                    doSudoku(sb.toString())
                    cnt = 0
                    sb = StringBuilder()
                }
            }
        }
    }

    private fun doSudoku(eles: String) {
        val elements = SudokuUtils.string2Sudoku(eles)
        print("\nnew Sudoku:")
        SudokuUtils.print(elements)
        SudokuUtils.enableLog(false)
        val sudoku = Sudoku(elements)
        sudoku.start()
        val result = sudoku.result()
        print("\nresult:")
        SudokuUtils.print(result)
    }
}
