package com.example.samplemobius.RecyclerView

import com.spotify.mobius.Update
import com.spotify.mobius.Next
import com.spotify.mobius.Next.*

class RecyclerLogic :Update<RecyclerModel, ViewEvent, RecyclerEffect> {

        override fun update(
            model: RecyclerModel,
            event: ViewEvent
        ): Next<RecyclerModel, RecyclerEffect> {
            return when (event) {
                is ClickEvent -> {
                    dispatch(setOf(FetchDataEffect as RecyclerEffect))
                }
                is DataEvent -> {
                    next(model.copy(items = event.data))
                }
                else -> {
                    noChange()
                }
            }

}
}