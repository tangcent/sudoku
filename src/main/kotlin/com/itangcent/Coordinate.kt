package com.itangcent

import java.util.*

/**
 * Created by TangMing on 2017/4/9.
 */
class Coordinate private constructor(val x: Int, val y: Int) {

    fun not(x: Int, y: Int): Boolean {
        return x != this.x || y != this.y
    }

    override fun toString(): String {
        return "[" +
                "x=" + x +
                ",y=" + y +
                ']'.toString()
    }

    companion object {

        private val cache = HashMap<Int, Coordinate>()

        fun instance(x: Int, y: Int): Coordinate {
            var coordinate: Coordinate? = cache[SudokuUtils.toIndex(x, y)]
            if (coordinate == null) {
                coordinate = newInstance(x, y)
            }
            return coordinate
        }

        @Synchronized
        private fun newInstance(x: Int, y: Int): Coordinate {
            val index = SudokuUtils.toIndex(x, y)
            var coordinate: Coordinate? = cache[index]
            if (coordinate == null) {
                coordinate = Coordinate(x, y)
                cache[index] = coordinate
            }
            return coordinate
        }
    }
}
