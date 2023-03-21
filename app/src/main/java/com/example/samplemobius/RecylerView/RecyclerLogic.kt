package com.example.samplemobius.RecylerView

import com.spotify.mobius.Update
import com.spotify.mobius.Next
import com.spotify.mobius.Next.*

class RecyclerLogic :Update<ViewModel, ViewEvent, ViewEffect> {

        override fun update(
            model: ViewModel,
            event: ViewEvent
        ): Next<ViewModel, ViewEffect> {
//            return if (event is ViewEvent.ClickEvent) {
//                next(model.updateView())
//            } else {
//                noChange()
//            }
            return noChange()
}
}