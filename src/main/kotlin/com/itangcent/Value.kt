package com.itangcent

/**
 * Created by TangMing on 2017/4/8.
 */
class Value {
    var num: Int = 0
    var x: Int = 0
    var y: Int = 0

    constructor(num: Int, x: Int, y: Int) {
        this.num = num
        this.x = x
        this.y = y
    }

    constructor(num: Int, coordinate: Coordinate) {
        this.num = num
        this.x = coordinate.x
        this.y = coordinate.y
    }

    override fun toString(): String {
        return "[" +
                "num=" + num +
                ",x=" + x +
                ",y=" + y +
                ']'.toString()
    }
}
