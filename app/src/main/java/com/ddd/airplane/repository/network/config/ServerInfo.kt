package com.ddd.airplane.repository.network.config

/**
 * @author jess
 */
object ServerInfo {

    const val image = "http://13.209.77.249"

    public enum class DOMAIN(val domain: String) {
        DEV("http://13.209.77.249"),
        REAL("http://13.209.77.249")
    }
}