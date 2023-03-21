package com.example.samplemobius.RecylerView

import android.os.Parcelable
import com.example.samplemobius.RecylerView.Adapter.ViewItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewModel(
    val items: List<ViewItem>
) : Parcelable{
    companion object {
        fun initialModel() = ViewModel(
            items= listOf(
                ViewItem("one",1),
                ViewItem("two",2),
                ViewItem("three",3),
                ViewItem("four",4),
            )
        )
    }

    val item:List<ViewItem>
        get() = items

    fun updateView(): ViewModel = copy(listOf(
        ViewItem("five",5),
        ViewItem("six",6),
        ViewItem("seven",7),
    ))
}