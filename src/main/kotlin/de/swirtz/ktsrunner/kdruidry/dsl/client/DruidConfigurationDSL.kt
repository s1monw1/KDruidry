package de.swirtz.ktsrunner.kdruidry.dsl.client

import `in`.zapr.druid.druidry.client.DruidConfiguration

fun configuration(config: DruidConfiguration.DruidConfigurationBuilder.() -> Unit): DruidConfiguration {
    return DruidConfiguration.builder().apply(config).build()
}