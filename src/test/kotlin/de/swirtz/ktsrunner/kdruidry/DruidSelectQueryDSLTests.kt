package de.swirtz.ktsrunner.kdruidry

import `in`.zapr.druid.druidry.filter.SelectorFilter
import `in`.zapr.druid.druidry.granularity.PredefinedGranularity
import `in`.zapr.druid.druidry.query.select.PagingSpec
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.swirtz.ktsrunner.kdruidry.dsl.query.selectQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class DruidSelectQueryDSLTests {

    @Test
    fun `select can be generated`() {
        val query = selectQuery("sample_data") {
            descending = true
            pagingSpec(PagingSpec(1, true, mapOf()))
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