package com.alancamargo.nutmegtest.core.log

interface Logger {

    fun debug(message: String)

    fun error(throwable: Throwable)
}
