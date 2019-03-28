package de.swirtz.ktsrunner.kdruidry

import `in`.zapr.druid.druidry.Interval
import java.time.ZonedDateTime

fun intervalBetween(from: ZonedDateTime, to: ZonedDateTime) =
    Interval(from.toLocalDateJodaTime(), to.toLocalDateJodaTime())

fun ZonedDateTime.toLocalDateJodaTime() = org.joda.time.DateTime(year, monthValue, dayOfMonth, hour, minute, second)
