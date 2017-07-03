package com.uzias.runrunpocket.core.domain

interface UseCase<T> {
    fun run(): T
}