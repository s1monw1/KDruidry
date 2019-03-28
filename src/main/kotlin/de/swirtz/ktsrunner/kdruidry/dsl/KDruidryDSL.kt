package de.swirtz.ktsrunner.kdruidry.dsl

@DslMarker
annotation class KDruidryDSLMarker

@KDruidryDSLMarker
interface KDruidryDSL {
    fun Any?.asUnit() = Unit
}