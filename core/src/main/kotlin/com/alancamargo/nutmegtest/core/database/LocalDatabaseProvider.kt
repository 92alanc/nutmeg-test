package com.alancamargo.nutmegtest.core.database

import androidx.room.RoomDatabase
import kotlin.reflect.KClass

interface LocalDatabaseProvider {

    fun <T : RoomDatabase> provideDatabase(clazz: KClass<T>, databaseName: String): T
}
