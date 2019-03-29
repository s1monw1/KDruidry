package de.swirtz.ktsrunner.kdruidry.dsl.query

import `in`.zapr.druid.druidry.Context
import `in`.zapr.druid.druidry.Interval
import `in`.zapr.druid.druidry.filter.AndFilter
import `in`.zapr.druid.druidry.filter.DruidFilter
import `in`.zapr.druid.druidry.filter.OrFilter
import `in`.zapr.druid.druidry.query.scan.DruidScanQuery
import `in`.zapr.druid.druidry.query.scan.ResultFormat
import de.swirtz.ktsrunner.kdruidry.dsl.KDruidryDSL
import de.swirtz.ktsrunner.kdruidry.dsl.buildNPEAware

fun scanQuery(dataSource: String, config: DruidScanQueryDSL.() -> Unit): DruidScanQuery {
    return DruidScanQueryDSL().apply {
        config()
        dataSource(dataSource)
    }.build()
}

class DruidScanQueryDSL : KDruidryDSL {
    private val builder: DruidScanQuery.DruidScanQueryBuilder = DruidScanQuery.builder()

    var legacy: Boolean = false
        set(value) = builder.legacy(value).asUnit()

    fun filter(df: DruidFilter) = builder.filter(df).asUnit()
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList())).asUnit()
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList())).asUnit()
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList()).asUnit()
    fun batchSize(bs: Int) = builder.batchSize(bs).asUnit()
    fun resultFormat(f: ResultFormat) = builder.resultFormat(f).asUnit()
    fun columns(vararg col: String) = builder.columns(col.toList()).asUnit()
    fun limit(l: Long) = builder.limit(l).asUnit()

    fun dataSource(ds: String) = builder.dataSource(ds).asUnit()
    fun context(context: Context) = builder.context(context).asUnit()

    fun build(): DruidScanQuery = buildNPEAware { builder.build() }
}



