package de.swirtz.ktsrunner.kdruidry.dsl.postaggregator

import `in`.zapr.druid.druidry.postAggregator.ArithmeticFunction
import `in`.zapr.druid.druidry.postAggregator.ArithmeticOrdering
import `in`.zapr.druid.druidry.postAggregator.ArithmeticPostAggregator
import `in`.zapr.druid.druidry.postAggregator.DruidPostAggregator
import de.swirtz.ktsrunner.kdruidry.dsl.KDruidryDSL

fun arithmeticPostAggregator(config: ArithmeticPostAggregatorDSL.() -> Unit): ArithmeticPostAggregator {
    return ArithmeticPostAggregatorDSL().apply(config).build()
}

class ArithmeticPostAggregatorDSL : KDruidryDSL {
    private val builder: ArithmeticPostAggregator.ArithmeticPostAggregatorBuilder = ArithmeticPostAggregator.builder()

    fun name(name: String) = builder.name(name).asUnit()
    fun function(function: ArithmeticFunction) = builder.function(function).asUnit()
    fun fields(vararg field: DruidPostAggregator) = builder.fields(field.toList()).asUnit()
    fun ordering(ordering: ArithmeticOrdering) = builder.ordering(ordering).asUnit()

    fun build(): ArithmeticPostAggregator = builder.build()
}