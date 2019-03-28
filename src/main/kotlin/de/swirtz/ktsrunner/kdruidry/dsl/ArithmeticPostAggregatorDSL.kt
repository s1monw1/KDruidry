package de.swirtz.ktsrunner.kdruidry.dsl

import `in`.zapr.druid.druidry.postAggregator.ArithmeticPostAggregator

fun arithmeticPostAggregator(config: ArithmeticPostAggregator.ArithmeticPostAggregatorBuilder.() -> Unit): ArithmeticPostAggregator {
    return ArithmeticPostAggregator.builder().apply(config).build()
}