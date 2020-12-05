package me.mataha.misaki.runner

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class Result(val name: String, val result: Any, val duration: Duration)
