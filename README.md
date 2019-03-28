# KDruiry

Wrapper around [Druidry](https://github.com/zapr-oss/druidry) for Kotlin.

# Top N Query

```kotlin

topNQuery {
    dataSource("sample_data")
    dimension(SimpleDimension("sample_dim"))
    threshold(5)
    simpleTopNMetric("count")
    simpleGranularity(PredefinedGranularity.ALL)
    andFilter(SelectorFilter("dim1", "some_value"), SelectorFilter("dim2", "some_other_val"))
    aggregators(
        LongSumAggregator("count", "count"),
        DoubleSumAggregator("some_metric", "some_metric")
    )
    postAggregators(
        arithmeticPostAggregator {
            name("sample_divide")
            function(ArithmeticFunction.DIVIDE)
            fields(
                listOf(
                    FieldAccessPostAggregator("some_metric", "some_metric"),
                    FieldAccessPostAggregator("count", "count")
                )
            )
        }
    )
    intervals(
        intervalBetween(
            ZonedDateTime.of(2013, 8, 31, 0, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2013, 9, 3, 0, 0, 0, 0, ZoneId.systemDefault())
        )
    )
}
        
```