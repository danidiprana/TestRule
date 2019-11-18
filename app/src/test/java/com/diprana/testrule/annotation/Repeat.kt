package com.diprana.testrule.annotation

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.TYPE

@Retention(RUNTIME)
@Target(FUNCTION, TYPE)
annotation class Repeat(val value: Int = 1)
