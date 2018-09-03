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
                SudokuFileTest::class.java.getResourceAsStream("sudoku.txt")
        )).use { reader ->
            var sb = StringBuilder()
            var cnt = 0
            var line: String? = reader.readLine()
            while (line != null) {
                line = line.trim { it <= ' ' }
                if (line.isEmpty() || line.startsWith("#"))
                    continue
                sb.append(line.trim { it <= ' ' })
                if (++cnt == 9) {
                    doSudoku(sb.toString())
                    cnt = 0
                    sb = StringBuilder()
                }
                line = reader.readLine()
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
