# KDruiry

Wrapper around [Druidry](https://github.com/zapr-oss/druidry) for Kotlin.

## Top N Query
http://druid.io/docs/latest/querying/topnquery.html

```kotlin
topNQuery("sample_data") {
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
```

## Timeseries Query

http://druid.io/docs/latest/querying/timeseriesquery.html

```kotlin
timeseries("sample_data") {
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
    descending = true
}
```
##Search Query
http://druid.io/docs/latest/querying/searchquery.html

```kotlin
searchQuery("sample_data") {
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
``` 

## Scan Query
http://druid.io/docs/latest/querying/scan-query.html

```kotlin
scanQuery("sample_data") {
    legacy = false
    limit(1000)
    batchSize(256)
    resultFormat(ResultFormat.LIST)
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
```
        
## Select Query

http://druid.io/docs/latest/querying/select-query.html

```kotlin
selectQuery("sample_data") {
    descending(true)
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
        
```