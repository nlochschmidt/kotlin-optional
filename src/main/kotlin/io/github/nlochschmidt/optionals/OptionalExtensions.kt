package io.github.nlochschmidt.optionals

import io.github.nlochschmidt.optionals.internal.OptionalIterator
import java.util.*

fun <T> Optional<T>.asIterable(): Iterable<T> = Iterable { this.iterator() }

operator fun <T> Optional<T>.iterator(): Iterator<T> = OptionalIterator(this)

fun <T> Optional<T>.isEmpty(): Boolean = !this.isPresent

fun <T> Optional<T>.forEach( action: (T) -> Unit) = this.ifPresent(action)

fun <T> Optional<T>.exists(predicate: (T) -> Boolean): Boolean = map(predicate).orElse(false)

fun <T> Optional<T>.contains(value: Any?): Boolean = this.exists { it == value }

infix fun <T, R, X> Optional<T>.orElse(fallBack: Optional<R>): Optional<X> where T : X, R : X {
    return (if (this.isPresent) this else fallBack) as Optional<X>
}

infix fun <T, R, X> Optional<T>.orElse(fallBack: () -> Optional<R>): Optional<X> where T : X, R : X {
    return (if (this.isPresent) this else fallBack()) as Optional<X>
}