package com.example.samplemobius.RecyclerView

import android.os.Parcelable
import com.example.samplemobius.RecyclerView.Adapter.ViewItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecyclerModel(
    val items: List<ViewItem>
) : Parcelable{
    companion object {
        fun initialModel() = RecyclerModel(
            emptyList()
        )
    }

    val item:List<ViewItem>
        get() = items

//    fun updateView(): RecyclerModel = copy(listOf(
//        ViewItem("Near", "NEAR", "1"),
//        ViewItem("USD", "US Dollar", "2"),
//        ViewItem("MATIC", "Matic", "3")
//    ))
}