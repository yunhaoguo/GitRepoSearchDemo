package com.example.gitreposearchdemo

import java.lang.IndexOutOfBoundsException

fun <T> copyIn(dest: Array<T>, src: Array<out T>) {
    if (dest.size < src.size) {
        throw IndexOutOfBoundsException()
    } else {
        src.forEachIndexed {index, value -> dest[index] = value}
    }
}

fun main() {
    var dest = arrayOfNulls<Number>(3)
    var src = arrayOf<Double>(1.0, 2.0, 3.0)
    copyIn(dest, src)
    dest[0] = 2.0
    src[2] = 1.3
}