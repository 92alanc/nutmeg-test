package com.alancamargo.nutmegtest.core.extensions

import retrofit2.HttpException

fun HttpException.isServerError(): Boolean {
    val range = 500..599
    return range.contains(code())
}
