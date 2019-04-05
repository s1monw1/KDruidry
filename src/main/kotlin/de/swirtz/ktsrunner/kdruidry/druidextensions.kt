package de.swirtz.ktsrunner.kdruidry

import `in`.zapr.druid.druidry.Interval
import java.time.ZonedDateTime
import org.joda.time.DateTime as JodaDateTime

fun intervalBetween(from: ZonedDateTime, to: ZonedDateTime) =
    Interval(from.toLocalDateJodaTime(), to.toLocalDateJodaTime())

fun ZonedDateTime.toLocalDateJodaTime() =
    JodaDateTime(year, monthValue, dayOfMonth, hour, minute, second)
