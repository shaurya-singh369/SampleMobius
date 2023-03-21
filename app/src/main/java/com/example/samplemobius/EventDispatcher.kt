package com.example.samplemobius

interface EventDispatcher<E> {
    fun postEvent(event: E)
}
