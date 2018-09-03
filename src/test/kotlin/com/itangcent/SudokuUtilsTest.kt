package com.itangcent

import java.util.function.BiConsumer

/**
 * Created by TangMing on 2017/4/9.
 */
object SudokuUtilsTest {
    @JvmStatic
    fun main(args: Array<String>) {
        for (x in 0..8) {
            for (y in 0..8) {
                val index = SudokuUtils.toIndex(x, y)
                System.out.printf("x:$x,y:$y->$index\n")
                System.out.printf("index:" + index + "->" + SudokuUtils.index2XY(index) + "\n")
            }
        }


        for (z in 0..8) {
            SudokuUtils.forZ(z, BiConsumer { xy, order ->
                System.out.printf("z:$z,order:$order->$xy\n")
                System.out.printf("z:" + z + ",order:" + order + "->" + SudokuUtils.z2XY(z, order!!) + "\n")
                System.out.printf("x:" + xy.x + ",y:" + xy.y + "->z:" + SudokuUtils.toZ(xy) + "\n")
                System.out.printf("x:" + xy.x + ",y:" + xy.y + "->order:" + SudokuUtils.toZOrder(xy) + "\n")
            })
        }
    }

}
