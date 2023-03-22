package com.example.samplemobius.RecyclerView.repository

import com.example.samplemobius.RecyclerView.schema.CryptoEntity
import com.example.samplemobius.RecyclerView.utils.NetworkUtils
import com.example.samplemobius.RecyclerView.api.CryptoDataApi
import android.content.Context
import android.util.Log
import com.example.samplemobius.RecyclerView.schema.CryptoDao


class CryptoRepository(
    private val cryptoService: CryptoDataApi,
    private val cryptoDao: CryptoDao,
    private val applicationContext: Context
) {


   suspend  fun getCryptoData(): List<CryptoEntity> {
        try {
            if (NetworkUtils.isInternetAvailable(applicationContext)) {
                val response = cryptoService.getAssets()
                if (response.body() != null) {
                    response.body()?.let {
                        insertAll(it.data)


                    }
                    return response.body()!!.data
                }
                return emptyList()
            } else {
                val data=getAll()
//                cryptoLiveData.postValue(data)
                return data

            }
        } catch (e: Exception) {
            Log.d("getCryptoData", "Error: ${e.message}")
            return getAll()
        }

    }

    private suspend fun insertAll(cryptoData: List<CryptoEntity>) {
        try {
            cryptoDao.insertAll(cryptoData)
        } catch (e: Exception) {
            Log.d("insertAll", "Error: ${e.message}")
        }
    }
    fun getAll(): List<CryptoEntity> {
        return try {
            cryptoDao.getAll()
        } catch (e: Exception) {
            Log.d("getAll", "Error: ${e.message}")
            emptyList()
        }

    }
}
