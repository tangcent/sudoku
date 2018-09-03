package com.itangcent

/**
 * Created by TangMing on 2017/4/9.
 */
class Holder<T> {
    var target: T? = null

    constructor() {}

    constructor(target: T) {
        this.target = target
    }
}
