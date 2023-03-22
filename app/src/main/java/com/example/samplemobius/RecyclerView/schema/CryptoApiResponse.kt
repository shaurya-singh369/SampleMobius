package com.example.samplemobius.RecyclerView.schema

data class CryptoApiResponse(
    val timestamp: Long,
    val data: List<CryptoEntity>,
)
data class SingleCryptoApiResponse(
    val timestamp: Long,
    val data: CryptoEntity,
)