package com.itangcent

import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer
import kotlin.collections.ArrayList

/**
 * Created by TangMing on 2017/4/8.
 */
object SudokuUtils {
    private val SINGLES = ArrayList<Int>()
    private val UNIQUES = ArrayList<Int>()
    var ALL = 511

    private val mask = 15

    private var enableLog = false

    init {
        for (i in 0..8) {
            val box = 1 shl i
            SINGLES.add(box)
            UNIQUES.add(ALL and box.inv())
        }
    }

    fun all(i: Int): Boolean {
        return ALL == i
    }

    fun box(i: Int): Int {
        if (i < 0 || i > 9) {
            throw IllegalArgumentException("unexpected val:$i")
        }
        return 1 shl i - 1
    }

    fun unbox(i: Int): Int {
        return SINGLES.indexOf(i) + 1
    }

    fun unique(i: Int): Boolean {
        return UNIQUES.contains(i)
    }

    fun single(i: Int): Boolean {
        return SINGLES.contains(i)
    }

    fun uniqueIndex(i: Int): Int {
        return UNIQUES[i]
    }

    fun unique2Single(i: Int): Int {
        return 1 shl UNIQUES[i]
    }

    fun toZ(coordinate: Coordinate): Int {
        return toZ(coordinate.x, coordinate.y)
    }

    fun toZOrder(coordinate: Coordinate): Int {
        val z = toZ(coordinate)
        return (coordinate.x - z / 3 * 3) * 3 + (coordinate.y - z % 3 * 3)
    }

    fun toZ(x: Int, y: Int): Int {
        return x / 3 * 3 + y / 3
    }

    fun z2XY(z: Int, order: Int): Coordinate {
        return Coordinate.instance(z / 3 * 3 + order / 3, z % 3 * 3 + order % 3)
    }

    fun forZ(z: Int, consumer: BiConsumer<Coordinate, Int>) {
        val xShift = z / 3 * 3
        val yShift = z % 3 * 3
        var order = 0
        for (x in 0..2) {
            for (y in 0..2) {
                consumer.accept(Coordinate.instance(x + xShift, y + yShift), order++)
            }
        }
    }

    fun toIndex(coordinate: Coordinate): Int {
        return toIndex(coordinate.x, coordinate.y)
    }

    fun toIndex(x: Int, y: Int): Int {
        return (x shl 4) + y
    }

    fun index2XY(index: Int): Coordinate {
        return Coordinate.instance(index shr 4 and mask, index and mask)
    }

    fun xOfIndex(index: Int): Int {
        return index shr 4 and mask
    }

    fun yOfIndex(index: Int): Int {
        return index and mask
    }

    fun shift(num: Int, shift: Int): Int {
        val fixNum = num + shift
        return when {
            fixNum > 8 -> fixNum - 9
            else -> fixNum
        }
    }

    fun inversion(num: Int): Int {
        return ALL and num.inv()
    }

    fun oneBits(num: Int): Int {
        var cNum = num
        var count = 0
        while (cNum != 0) {
            cNum = cNum - 1 and cNum
            count++
        }
        return count
    }

    fun forBit(num: Int, consumer: Consumer<Int>) {
        for (i in 0..8) {
            if (SINGLES[i] and num != 0) {
                consumer.accept(i)
            }
        }
    }

    fun string2Sudoku(eles: String): Array<IntArray> {
        val elements = Array(9) { IntArray(9) }
        each(elements) { _, x, y -> char2Num(eles[x * 9 + y]) }
        return elements
    }

    private fun char2Num(ch: Char): Int {
        return ch - '0'
    }

    fun sibling(coordinate: Coordinate, consumer: Consumer<Coordinate>) {
        sibling(coordinate.x, coordinate.y, consumer)
    }

    fun sibling(x: Int, y: Int, consumer: Consumer<Coordinate>) {
        val cache = HashSet<Int>()
        cache(cache, SudokuUtils.toIndex(x, y))
        for (i in 0..8) {
            if (i != x && cache(cache, SudokuUtils.toIndex(i, y))) {
                consumer.accept(Coordinate.instance(i, y))
            }
            if (i != y && cache(cache, SudokuUtils.toIndex(x, i))) {
                consumer.accept(Coordinate.instance(x, i))
            }
        }
        SudokuUtils.forZ(SudokuUtils.toZ(x, y), BiConsumer { coordinate, order ->
            if (coordinate.not(x, y) && cache(cache, SudokuUtils.toIndex(coordinate.x, coordinate.y))) {
                consumer.accept(coordinate)
            }
        })
    }

    fun cache(cache: MutableSet<Int>, value: Int): Boolean {
        if (cache.contains(value)) {
            return false
        } else {
            cache.add(value)
            return true
        }
    }

    fun each(elements: Array<IntArray>, eleCall: EleCall) {
        for (x in elements.indices) {
            for (y in 0 until elements[x].size) {
                elements[x][y] = eleCall(elements[x][y], x, y)
            }
        }
    }

//    interface EleCall {
//        fun process(value: Int, x: Int, y: Int): Int
//    }

    fun sameRow(one: Coordinate, another: Coordinate): Boolean {
        return one.x == another.x
    }

    fun sameColumn(one: Coordinate, another: Coordinate): Boolean {
        return one.y == another.y
    }

    fun sameBlock(one: Coordinate, another: Coordinate): Boolean {
        return toZ(one) == toZ(another)
    }

    fun enableLog(enableLog: Boolean) {
        SudokuUtils.enableLog = enableLog
    }

    fun printMsg(msg: String) {
        if (enableLog) {
            print(msg)
        }
    }

    fun print(result: Array<IntArray>) {
        for (x in 0..8) {
            if (x % 3 == 0) {
                print("\n")
            }
            print("\n")
            for (y in 0..8) {
                if (y % 3 == 0) {
                    print("\t")
                }
                print(result[x][y])
            }
        }
    }

    fun entireRow(row: Int): Array<Coordinate> {
        val result = ArrayList<Coordinate>(9)
        for (i in 0..8) {
            result.add(Coordinate.instance(row, i))
        }
        return result.toTypedArray()
    }

    fun entireColumn(column: Int): Array<Coordinate> {
        val result = ArrayList<Coordinate>(9)
        for (i in 0..8) {
            result.add(Coordinate.instance(i, column))
        }
        return result.toTypedArray()
    }

    fun entireBlock(z: Int): Array<Coordinate> {
        val result = ArrayList<Coordinate>(9)
        for (i in 0..8) {
            result.add(z2XY(z, i))
        }
        return result.toTypedArray()
    }
}

typealias EleCall = (Int, Int, Int) -> Int