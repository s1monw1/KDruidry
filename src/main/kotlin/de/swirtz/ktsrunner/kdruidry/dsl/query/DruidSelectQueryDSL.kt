package de.swirtz.ktsrunner.kdruidry.dsl.query

import `in`.zapr.druid.druidry.Context
import `in`.zapr.druid.druidry.Interval
import `in`.zapr.druid.druidry.filter.AndFilter
import `in`.zapr.druid.druidry.filter.DruidFilter
import `in`.zapr.druid.druidry.filter.OrFilter
import `in`.zapr.druid.druidry.granularity.Granularity
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.granularity.SimpleGranularity
import `in`.zapr.druid.druidry.query.select.DruidSelectQuery
import `in`.zapr.druid.druidry.query.select.PagingSpec
import de.swirtz.ktsrunner.kdruidry.dsl.KDruidryDSL
import de.swirtz.ktsrunner.kdruidry.dsl.buildNPEAware

fun selectQuery(dataSource: String, config: DruidSelectQueryDSL.() -> Unit): DruidSelectQuery {
    return DruidSelectQueryDSL().apply {
        config()
        dataSource(dataSource)
    }.build()
}

class DruidSelectQueryDSL : KDruidryDSL {
    private val builder: DruidSelectQuery.DruidSelectQueryBuilder = DruidSelectQuery.builder()

    var descending: Boolean = false
        set(value) = builder.descending(value).asUnit()

    fun granularity(g: Granularity) = builder.granularity(g).asUnit()
    fun simpleGranularity(g: PredefinedGranularity) = granularity(SimpleGranularity(g)).asUnit()
    fun filter(df: DruidFilter) = builder.filter(df).asUnit()
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList())).asUnit()
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList())).asUnit()
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList()).asUnit()
    fun metrics(vararg metric: String) = builder.metrics(metric.toList()).asUnit()
    fun pagingSpec(pageSpec: PagingSpec) = builder.pagingSpec(pageSpec).asUnit()

    fun dataSource(ds: String) = builder.dataSource(ds).asUnit()
    fun context(context: Context) = builder.context(context).asUnit()

    fun build(): DruidSelectQuery = buildNPEAware { builder.build() }
}



