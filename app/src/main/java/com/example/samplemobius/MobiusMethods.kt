package com.example.samplemobius


import com.spotify.mobius.Init
import com.spotify.mobius.Update
import com.spotify.mobius.functions.Consumer
import io.reactivex.ObservableTransformer

interface MobiusMethods<M, E, F, V> {
    fun initialModel(): M

    fun update(): Update<M, E, F>

    /**
     * Handle effects
     * Fragment view not initialized yet; do not access fragment view here
     */
    fun effectHandler(consumer: Consumer<V>): ObservableTransformer<F, E>

    /**
     * Initialization function, needs initial model and initial effects.
     */
    fun init(): Init<M, F>
}
