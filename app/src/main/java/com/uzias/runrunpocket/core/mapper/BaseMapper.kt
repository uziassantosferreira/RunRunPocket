package com.uzias.runrunpocket.core.mapper

abstract class BaseMapper<T, K> {

    abstract fun transformFrom(source: K, disambiguate  : Boolean = false): T

    abstract fun transformFrom(source: T): K

    fun transformFromMutableList(source: MutableList<K>): MutableList<T> {
        return source.map { src -> transformFrom(src) }.toMutableList()
    }
    fun transformFromMutableList(source: MutableList<T>, disambiguate  : Boolean = false): MutableList<K> {
        return source.map { src -> transformFrom(src) }.toMutableList()
    }

}
