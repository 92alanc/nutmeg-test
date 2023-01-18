package com.alancamargo.nutmegtest.core.network

interface ApiProvider {

    fun <T> provideService(clazz: Class<T>): T
}
