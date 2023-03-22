package com.example.samplemobius.RecyclerView

import com.spotify.mobius.First
import com.spotify.mobius.Init

class InitLogic: Init<RecyclerModel, RecyclerEffect> {
    override fun init(model: RecyclerModel): First<RecyclerModel, RecyclerEffect> {
        return First.first(model)
    }
}