package de.swirtz.ktsrunner.kdruidry

import `in`.zapr.druid.druidry.aggregator.DoubleSumAggregator
import `in`.zapr.druid.druidry.aggregator.LongSumAggregator
import `in`.zapr.druid.druidry.dimension.SimpleDimension
import `in`.zapr.druid.druidry.filter.SelectorFilter
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.postAggregator.ArithmeticFunction
import `in`.zapr.druid.druidry.postAggregator.ArithmeticOrdering
import `in`.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.swirtz.ktsrunner.kdruidry.dsl.arithmeticPostAggregator
import de.swirtz.ktsrunner.kdruidry.dsl.topNQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class DruidTopNQueryBuilderDSLTests {

    @Test
    fun `topNQuery can be generated`() {
        val query = topNQuery {
            dataSource("sample_data")
            dimension(SimpleDimension("sample_dim"))
            threshold(5)
            simpleTopNMetric("count")
            simpleGranularity(PredefinedGranularity.ALL)
            andFilter(
                SelectorFilter("dim1", "some_value"),
                SelectorFilter("dim2", "some_other_val")
            )
            aggregators(
                LongSumAggregator("count", "count"),
                DoubleSumAggregator("some_metric", "some_metric")
            )
            postAggregators(
                arithmeticPostAggregator {
                    name("sample_divide")
                    function(ArithmeticFunction.DIVIDE)
                    fields(
                        FieldAccessPostAggregator("some_metric", "some_metric"),
                        FieldAccessPostAggregator("count", "count")
                    )
                    ordering(ArithmeticOrdering.NUMERIC_FIRST)
                }
            )
            intervals(
                intervalBetween(
                    ZonedDateTime.of(2013, 8, 31, 0, 0, 0, 0, ZoneId.systemDefault()),
                    ZonedDateTime.of(2013, 9, 3, 0, 0, 0, 0, ZoneId.systemDefault())
                )
            )
        }

        val mapper = jacksonObjectMapper()
        val requiredJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(query)
        println(requiredJson)
        assertThat(requiredJson.isNotBlank())
    }
}