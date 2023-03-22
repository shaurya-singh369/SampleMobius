package com.example.samplemobius.RecyclerView.schema

import androidx.room.*

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptoData: List<CryptoEntity>)
    @Delete
    suspend fun deleteAll(cryptoData: List<CryptoEntity>)
    @Update
    suspend fun updateAll(cryptoData: List<CryptoEntity>)
    @Query("SELECT * FROM crypto_table")
    fun getAll(): List<CryptoEntity>

}