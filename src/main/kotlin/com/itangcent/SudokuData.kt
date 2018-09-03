package com.itangcent

import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.streams.toList

/**
 * Created by TangMing on 2017/4/8.
 */
class SudokuData {
    private val elements: Array<IntArray>

    //9*9
    constructor(elements: Array<IntArray>) {
        this.elements = elements
        this.rests = ArrayList<Int>()
        this.lock = ReentrantReadWriteLock()
        init()
    }

    @Volatile
    private var optionals: Array<IntArray>? = null//9*9
    @Volatile
    private lateinit var sx: IntArray//x1-9
    @Volatile
    private lateinit var sy: IntArray //y1-9
    @Volatile
    private lateinit var sz: IntArray//z1-9;

    @Volatile
    private var rests: ArrayList<Int>

    private var lock: ReentrantReadWriteLock

    private fun init() {
        sx = IntArray(10)
        Arrays.fill(sx, 0)
        sy = IntArray(10)
        Arrays.fill(sy, 0)
        sz = IntArray(10)
        Arrays.fill(sz, 0)
        optionals = Array(9) { IntArray(9) }
        SudokuUtils.each(optionals!!) { `val`, x, y ->
            rests.add(SudokuUtils.toIndex(x, y))
            SudokuUtils.ALL
        }
        SudokuUtils.each(elements) { `val`, x, y ->
            if (`val` != 0) {
                sure(`val`, x, y)
            }
            `val`
        }
    }

    fun sure(value: Value) {
        sure(value.num, value.x, value.y)
    }

    fun completed(): Boolean {
        lock.readLock().lock()
        try {
            return rests.isEmpty()
        } finally {
            lock.readLock().unlock()
        }
    }

    private fun sure(`val`: Int, x: Int, y: Int) {
        if (todo(x, y)) {
            lock.writeLock().lock()
            try {
                if (todo(x, y)) {
                    this.elements[x][y] = `val`
                    val box = SudokuUtils.box(`val`)
                    this.optionals!![x][y] = box
                    sx[x] = sx[x] or box
                    sy[y] = sy[y] or box
                    sz[SudokuUtils.toZ(x, y)] = sz[SudokuUtils.toZ(x, y)] or box
                    val complement = box.inv()
                    SudokuUtils.sibling(x, y, Consumer { coordinate -> this.optionals!![coordinate.x][coordinate.y] = this.optionals!![coordinate.x][coordinate.y] and complement })
                    rests.remove(SudokuUtils.toIndex(x, y))
                }
            } finally {
                lock.writeLock().unlock()
            }
        }
    }

    fun ensure(ensureEles: Array<Coordinate>, options: Int) {
        lock.writeLock().lock()
        try {
            for (ensureEle in ensureEles) {
                optionals!![ensureEle.x][ensureEle.y] = optionals!![ensureEle.x][ensureEle.y] and options.inv()
            }
        } finally {
            lock.writeLock().unlock()
        }
    }

    //确定此位置上不可使用此选项
    fun ensure(x: Int, y: Int, options: Int) {
        lock.writeLock().lock()
        try {
            optionals!![x][y] = optionals!![x][y] and options.inv()
        } finally {
            lock.writeLock().unlock()
        }
    }

    fun sx(x: Int): Int {
        lock.readLock().lock()
        try {
            return sx!![x]
        } finally {
            lock.readLock().unlock()
        }
    }

    fun sy(y: Int): Int {
        lock.readLock().lock()
        try {
            return sy!![y]
        } finally {
            lock.readLock().unlock()
        }
    }

    fun sz(z: Int): Int {
        lock.readLock().lock()
        try {
            return sz!![z]
        } finally {
            lock.readLock().unlock()
        }
    }

    fun element(x: Int, y: Int): Int {
        lock.readLock().lock()
        try {
            return elements[x][y]
        } finally {
            lock.readLock().unlock()
        }
    }

    fun optional(coordinate: Coordinate): Int {
        return optional(coordinate.x, coordinate.y)
    }

    fun optional(x: Int, y: Int): Int {
        lock.readLock().lock()
        try {
            return optionals!![x][y]
        } finally {
            lock.readLock().unlock()
        }
    }

    fun optionals(coordinates: Array<Coordinate>): Array<Int> {
        lock.readLock().lock()
        try {
            return Arrays.stream(coordinates)
                    .map<Int> { this.optional(it) }
                    .toList().toTypedArray()
        } finally {
            lock.readLock().unlock()
        }
    }

    //保留需要填的位置
    fun todos(coordinates: Array<Coordinate>): Array<Coordinate> {
        lock.readLock().lock()
        try {
            return Arrays.stream(coordinates)
                    .filter { coordinate -> rests.contains(SudokuUtils.toIndex(coordinate)) }
                    .toList().toTypedArray()
        } finally {
            lock.readLock().unlock()
        }
    }

    fun todo(x: Int, y: Int): Boolean {
        lock.readLock().lock()
        try {
            return rests.contains(SudokuUtils.toIndex(x, y))
        } finally {
            lock.readLock().unlock()
        }
    }

    fun rests(): List<Int> {
        lock.readLock().lock()
        try {
            return ArrayList(rests)
        } finally {
            lock.readLock().unlock()
        }
    }

    fun result(): Array<IntArray> {
        val copy = ArrayList<IntArray>(9)
        for (i in 0..8) {
            copy.add(Arrays.copyOf(elements[i], 9))
        }
        return copy.toTypedArray()
    }

}
