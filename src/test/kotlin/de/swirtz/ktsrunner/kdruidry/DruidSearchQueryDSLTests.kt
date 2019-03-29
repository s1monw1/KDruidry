package de.swirtz.ktsrunner.kdruidry

import `in`.zapr.druid.druidry.SortingOrder
import `in`.zapr.druid.druidry.dimension.SimpleDimension
import `in`.zapr.druid.druidry.filter.SelectorFilter
import `in`.zapr.druid.druidry.filter.searchQuerySpec.ContainsSearchQuerySpec
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.swirtz.ktsrunner.kdruidry.dsl.query.searchQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class DruidSearchQueryDSLTests {

    @Test
    fun `select can be generated`() {
        val query = searchQuery("sample_data") {
            limit(1000)
            searchDimensions(SimpleDimension("sample_dim"))
            query(ContainsSearchQuerySpec("hello world"))
            sort(SortingOrder.ALPHANUMERIC)
            simpleGranularity(PredefinedGranularity.ALL)
            andFilter(
                SelectorFilter("dim1", "some_value"),
                SelectorFilter("dim2", "some_other_val")
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