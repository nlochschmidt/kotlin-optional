package io.github.nlochschmidt.optionals

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.throws
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.util.*

class OptionalExtensionsTest {

    private val emptyOpt = Optional.empty<Any>()

    @Test
    fun `isEmpty`() {
        assertThat(emptyOpt.isEmpty(), equalTo(true))
        assertThat(Optional.of("").isEmpty(), equalTo(false))
    }

    @Test
    fun `iterator`() {
        assertThat(emptyOpt.iterator().hasNext(), equalTo(false))
        assertThat( { emptyOpt.iterator().next() }, throws<NoSuchElementException>())

        val optionalAsIterator = Optional.of("Test").iterator()
        assertThat(optionalAsIterator.hasNext(), equalTo(true))
        assertThat(optionalAsIterator.next(), equalTo("Test"))
        assertThat(optionalAsIterator.hasNext(), equalTo(false))
        assertThat( { optionalAsIterator.next() }, throws<NoSuchElementException>())
    }

    @Test
    fun `asIterable`() {
        assertThat(emptyOpt.asIterable().count(), equalTo(0))
        assertThat(Optional.of("").asIterable().count(), equalTo(1))
    }

    @Test
    fun `for`() {
        for (element in emptyOpt) {
            fail<Unit>("There shouldn't be any element in Optional.empty()")
        }

        var invocations = 0
        for (element in Optional.of("Test")) {
            invocations++
            assertThat(element, equalTo("Test"))
        }
        assertThat(invocations, equalTo(1))
    }

    @Test
    fun `forEach`() {
        emptyOpt.forEach {
            fail<Unit>("There shouldn't be any element in Optional.empty()")
        }

        var invocations = 0
        Optional.of("Test").forEach { element ->
            invocations++
            assertThat(element, equalTo("Test"))
        }
        assertThat(invocations, equalTo(1))
    }

    @Test
    fun `exists`() {
        emptyOpt.exists {
            fail<Boolean>("There shouldn't be any element in Optional.empty()")
        }

        val optionalOfFive = Optional.of(5)
        assertThat(optionalOfFive.exists { it < 1 }, equalTo(false))
        assertThat(optionalOfFive.exists { it >= 5 }, equalTo(true))
    }

    @Test
    fun `orElse`() {
        assertThat(emptyOpt orElse emptyOpt, equalTo(emptyOpt))

        val exampleStringOpt = Optional.of("Test")
        assertThat(exampleStringOpt.orElse(Optional.empty()), equalTo(exampleStringOpt))
        assertThat(Optional.empty<String>() orElse exampleStringOpt, equalTo(exampleStringOpt))
        assertThat(Optional.empty<String>() orElse { exampleStringOpt }, equalTo(exampleStringOpt))

        val exampleIntOpt = Optional.of(10)
        assertThat(exampleIntOpt orElse exampleStringOpt, equalTo(exampleIntOpt as Optional<Any>))
        assertThat(exampleStringOpt orElse exampleIntOpt, equalTo(exampleStringOpt as Optional<Any>))
    }
}


