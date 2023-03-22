package com.example.samplemobius.RecyclerView.Adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewItem (val name:String,val count:Int ): Parcelable