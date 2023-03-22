package com.example.samplemobius

import android.os.Parcelable
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.AndroidLogger
import com.spotify.mobius.android.MobiusLoopViewModel
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxConnectables

abstract class BaseMobiusVVMProviderFragment<M : Parcelable, E, F, V>: BaseMobiusVVMFragment<M, E, F, V>(), MobiusMethods<M, E, F, V> {

    override fun createViewModel(): MobiusLoopViewModel<M, E, F, V> = MobiusLoopViewModel.create(
        { consumer: Consumer<V> ->
            var mobius = Mobius
                .loop(update(), RxConnectables.fromTransformer(effectHandler(consumer)))

            if (BuildConfig.DEBUG) {
                mobius = mobius.logger(AndroidLogger(logTag()))
            }

            mobius
        },
        initialModel(),
        {
            init().init(it)
        }
    ) as MobiusLoopViewModel<M, E, F, V>

    fun getViewModel() : M {
        return viewModel.model
    }

    abstract fun logTag(): String
}

