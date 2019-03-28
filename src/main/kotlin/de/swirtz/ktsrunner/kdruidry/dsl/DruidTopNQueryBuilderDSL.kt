package de.swirtz.ktsrunner.kdruidry.dsl

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

@KDruidryDSL
class DruidTopNQueryBuilderDSL {
    private val builder = DruidTopNQuery.builder()

    fun simpleDimension(dim: String) = dimension(SimpleDimension(dim))
    fun dimension(dim: DruidDimension) = builder.dimension(dim)
    fun dataSource(ds: String) = builder.dataSource(ds)
    fun threshold(th: Int) = builder.threshold(th)
    fun topNMetric(m: TopNMetric) = builder.topNMetric(m)
    fun simpleTopNMetric(m: String) = topNMetric(SimpleMetric(m))
    fun granularity(g: Granularity) = builder.granularity(g)
    fun simpleGranularity(g: PredefinedGranularity) = granularity(SimpleGranularity(g))
    fun filter(df: DruidFilter) = builder.filter(df)
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList()))
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList()))
    fun aggregators(vararg agg: DruidAggregator) = builder.aggregators(agg.toList())
    fun postAggregators(vararg pa: DruidPostAggregator) = builder.postAggregators(pa.toList())
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList())

    fun build() = builder.build()
}



