package com.example.samplemobius.RecyclerView.schema.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samplemobius.RecyclerView.schema.CryptoDao
import com.example.samplemobius.RecyclerView.schema.CryptoEntity

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

}
