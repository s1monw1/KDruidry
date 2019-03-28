package de.swirtz.ktsrunner.kdruidry.dsl

import `in`.zapr.druid.druidry.Context
import `in`.zapr.druid.druidry.Interval
import `in`.zapr.druid.druidry.aggregator.DruidAggregator
import `in`.zapr.druid.druidry.dimension.DruidDimension
import `in`.zapr.druid.druidry.dimension.SimpleDimension
import `in`.zapr.druid.druidry.filter.AndFilter
import `in`.zapr.druid.druidry.filter.DruidFilter
import `in`.zapr.druid.druidry.filter.OrFilter
import `in`.zapr.druid.druidry.granularity.Granularity
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.granularity.SimpleGranularity
import `in`.zapr.druid.druidry.postAggregator.DruidPostAggregator
import `in`.zapr.druid.druidry.query.aggregation.DruidTopNQuery
import `in`.zapr.druid.druidry.topNMetric.SimpleMetric
import `in`.zapr.druid.druidry.topNMetric.TopNMetric

fun topNQuery(config: DruidTopNQueryBuilderDSL.() -> Unit): DruidTopNQuery {
    return DruidTopNQueryBuilderDSL().apply(config).build()
}

class DruidTopNQueryBuilderDSL : KDruidryDSL {
    private val builder = DruidTopNQuery.builder()

    fun simpleDimension(dim: String) = dimension(SimpleDimension(dim)).asUnit()
    fun dimension(dim: DruidDimension) = builder.dimension(dim).asUnit()
    fun dataSource(ds: String) = builder.dataSource(ds).asUnit()
    fun threshold(th: Int) = builder.threshold(th).asUnit()
    fun topNMetric(m: TopNMetric) = builder.topNMetric(m).asUnit()
    fun simpleTopNMetric(m: String) = topNMetric(SimpleMetric(m)).asUnit()
    fun granularity(g: Granularity) = builder.granularity(g).asUnit()
    fun simpleGranularity(g: PredefinedGranularity) = granularity(SimpleGranularity(g)).asUnit()
    fun filter(df: DruidFilter) = builder.filter(df).asUnit()
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList())).asUnit()
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList())).asUnit()
    fun aggregators(vararg agg: DruidAggregator) = builder.aggregators(agg.toList()).asUnit()
    fun postAggregators(vararg pa: DruidPostAggregator) = builder.postAggregators(pa.toList()).asUnit()
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList()).asUnit()
    fun context(context: Context) = builder.context(context).asUnit()

    fun build() = builder.build()
}



