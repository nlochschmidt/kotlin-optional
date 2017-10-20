# kotlin-optional [![Build Status](https://travis-ci.org/nlochschmidt/kotlin-optional.svg?branch=master)](https://travis-ci.org/nlochschmidt/kotlin-optional)

Small collection of useful extension functions to make Java 8 `Optional`s behave a little more like Scala `Option`s, but in Kotlin.

## Features

### Chain Optionals with `orElse`

```kotlin
fun locale(): Optional<Locale> {
  val localeInRequestOpt = ...
  val localeFromUserProfileOpt = ...
  return localeInRequestOpt orElse localeFromUserProfileOpt
}
```

it is also possible to evaluate the second option lazily:

```kotlin
fun locale(): Optional<Locale> {
  val localeInRequest = ...
  return localeInRequest orElse { getLocaleFromUserProfile() }
}
```

### Use Optionals in for-loops

```kotlin
val optional = Optional.of("Example")
for (element in optional) {
  println(element) // prints "Example"
}
```

Alternatively you can use the `forEach` function as well:

```kotlin
val optional = Optional.of("Example")
optional.forEach { element ->
  println(element) // prints "Example"
}
```

### Check for emptiness

You now have the option to use `isEmpty()` instead of (`isPresent().not()`)

```kotlin
if (optional.isEmpty()) {
  // Do something when the optional is not set
}
```

### Check content of the Optional

Whether it satisfies a certain condition

```kotlin
if (passwordOpt.exists(it.length < 8)) {
  // There is no password or it is smaller than 8 characters in length
}
```

There is also a shortcut to check if it contains a specific element

```kotlin
if (passwordOpt.contains("12345678")) {
  // There is no password or it is to easy
}
```
