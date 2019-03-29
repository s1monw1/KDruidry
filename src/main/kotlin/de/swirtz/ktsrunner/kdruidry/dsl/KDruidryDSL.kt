package de.swirtz.ktsrunner.kdruidry.dsl

@DslMarker
annotation class KDruidryDSLMarker

@KDruidryDSLMarker
interface KDruidryDSL {
    fun Any?.asUnit() = Unit
}

fun <R> KDruidryDSL.buildNPEAware(block: () -> R): R {
    try {
        return block()
    } catch (npe: NullPointerException) {
        throw IllegalStateException("Property not initialized: ${npe.message}", npe)
    }
}