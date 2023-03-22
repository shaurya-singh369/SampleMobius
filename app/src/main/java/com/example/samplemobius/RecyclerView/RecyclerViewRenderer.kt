package com.example.samplemobius.RecyclerView

import com.example.samplemobius.RecyclerView.Adapter.ViewItem
import com.example.samplemobius.RecyclerView.schema.CryptoEntity

interface RecyclerViewRenderer {
    fun showData(data:List<ViewItem>)


}