package com.test.moshitest

import com.squareup.moshi.JsonQualifier
import kotlin.annotation.AnnotationTarget.*
import kotlin.annotation.AnnotationRetention.*

@Retention(RUNTIME)
@JsonQualifier
@Target(FUNCTION, CLASS)
internal annotation class Enveloped