package de.swirtz.ktsrunner.kdruidry.dsl.query

import `in`.zapr.druid.druidry.Context
import `in`.zapr.druid.druidry.Interval
import `in`.zapr.druid.druidry.aggregator.DruidAggregator
import `in`.zapr.druid.druidry.filter.AndFilter
import `in`.zapr.druid.druidry.filter.DruidFilter
import `in`.zapr.druid.druidry.filter.OrFilter
import `in`.zapr.druid.druidry.granularity.Granularity
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.granularity.SimpleGranularity
import `in`.zapr.druid.druidry.postAggregator.DruidPostAggregator
import `in`.zapr.druid.druidry.query.aggregation.DruidTimeSeriesQuery
import de.swirtz.ktsrunner.kdruidry.dsl.KDruidryDSL
import de.swirtz.ktsrunner.kdruidry.dsl.buildNPEAware

fun timeseries(dataSource: String, config: DruidTimeSeriesQueryDSL.() -> Unit): DruidTimeSeriesQuery {
    return DruidTimeSeriesQueryDSL().apply {
        config()
        dataSource(dataSource)
    }.build()
}

class DruidTimeSeriesQueryDSL : KDruidryDSL {
    private val builder: DruidTimeSeriesQuery.DruidTimeSeriesQueryBuilder = DruidTimeSeriesQuery.builder()

    var descending: Boolean = false
        set(value) = builder.descending(value).asUnit()

    fun granularity(g: Granularity) = builder.granularity(g).asUnit()
    fun simpleGranularity(g: PredefinedGranularity) = granularity(SimpleGranularity(g)).asUnit()
    fun filter(df: DruidFilter) = builder.filter(df).asUnit()
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList())).asUnit()
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList())).asUnit()
    fun aggregators(vararg agg: DruidAggregator) = builder.aggregators(agg.toList()).asUnit()
    fun postAggregators(vararg pa: DruidPostAggregator) = builder.postAggregators(pa.toList()).asUnit()
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList()).asUnit()

    fun dataSource(ds: String) = builder.dataSource(ds).asUnit()
    fun context(context: Context) = builder.context(context).asUnit()

    fun build(): DruidTimeSeriesQuery = buildNPEAware { builder.build() }
}



