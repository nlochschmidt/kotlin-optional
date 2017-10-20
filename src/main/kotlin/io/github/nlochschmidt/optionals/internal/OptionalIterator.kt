package io.github.nlochschmidt.optionals.internal

import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

internal class OptionalIterator<T>(private val optional: Optional<T>) : Iterator<T> {
    var hasNextElement = AtomicBoolean(optional.isPresent)
    override fun hasNext(): Boolean = hasNextElement.get()
    override fun next(): T {
        val hadElement = hasNextElement.getAndSet(false)
        if (hadElement) {
            return optional.get()
        } else {
            throw NoSuchElementException("No value present")
        }
    }
}