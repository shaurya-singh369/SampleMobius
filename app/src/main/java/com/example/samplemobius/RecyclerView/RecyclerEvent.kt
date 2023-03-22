package com.example.samplemobius.RecyclerView

import com.example.samplemobius.RecyclerView.Adapter.ViewItem
import com.example.samplemobius.RecyclerView.schema.CryptoEntity


sealed class ViewEvent
object ClickEvent : ViewEvent()
data class DataEvent(val data:List<ViewItem>):ViewEvent()

