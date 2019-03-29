package de.swirtz.ktsrunner.kdruidry.dsl.query

import `in`.zapr.druid.druidry.Context
import `in`.zapr.druid.druidry.Interval
import `in`.zapr.druid.druidry.SortingOrder
import `in`.zapr.druid.druidry.dimension.DruidDimension
import `in`.zapr.druid.druidry.filter.AndFilter
import `in`.zapr.druid.druidry.filter.DruidFilter
import `in`.zapr.druid.druidry.filter.OrFilter
import `in`.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec
import `in`.zapr.druid.druidry.granularity.Granularity
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.granularity.SimpleGranularity
import `in`.zapr.druid.druidry.query.search.DruidSearchQuery
import de.swirtz.ktsrunner.kdruidry.dsl.KDruidryDSL
import de.swirtz.ktsrunner.kdruidry.dsl.buildNPEAware

fun searchQuery(dataSource: String, config: DruidSearchQueryDSL.() -> Unit): DruidSearchQuery {
    return DruidSearchQueryDSL().apply {
        config()
        dataSource(dataSource)
    }.build()
}

class DruidSearchQueryDSL : KDruidryDSL {
    private val builder: DruidSearchQuery.DruidSearchQueryBuilder = DruidSearchQuery.builder()

    fun granularity(g: Granularity) = builder.granularity(g).asUnit()
    fun simpleGranularity(g: PredefinedGranularity) = granularity(SimpleGranularity(g)).asUnit()
    fun filter(df: DruidFilter) = builder.filter(df).asUnit()
    fun andFilter(vararg filter: DruidFilter) = filter(AndFilter(filter.toList())).asUnit()
    fun orFilter(vararg filter: DruidFilter) = filter(OrFilter(filter.toList())).asUnit()
    fun intervals(vararg interval: Interval) = builder.intervals(interval.toList()).asUnit()
    fun limit(l: Long) = builder.limit(l.toInt()).asUnit()
    fun searchDimensions(vararg dim: DruidDimension) = builder.searchDimensions(dim.toList()).asUnit()
    fun query(q: SearchQuerySpec) = builder.query(q).asUnit()
    fun sort(s: SortingOrder) = builder.sort(s).asUnit()

    fun dataSource(ds: String) = builder.dataSource(ds).asUnit()
    fun context(context: Context) = builder.context(context).asUnit()

    fun build(): DruidSearchQuery = buildNPEAware { builder.build() }
}



