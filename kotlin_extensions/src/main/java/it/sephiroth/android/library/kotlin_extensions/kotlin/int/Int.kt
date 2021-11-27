package it.sephiroth.android.library.kotlin_extensions.kotlin.int

infix fun Int.hasBits(value: Int): Boolean {
    return this and value == value
}
