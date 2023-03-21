package com.example.samplemobius

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusLoopViewModel
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxConnectables

abstract class BaseActivity<M : Parcelable, E, F, V>: AppCompatActivity(), BaseMobiusProvider<M, E, F, V>, MobiusMethods<M, E, F, V>, EventDispatcher<E> {

    lateinit var viewModel: MobiusLoopViewModel<M, E, F, V>

    protected val eventSource by lazy(LazyThreadSafetyMode.NONE) {
        DeferredEventSource<E>()
    }

    /**
     * Adding setup before super.onCreate to use this function as to initialize anything
     * that is required before it. Like dependency injection. since in all other places
     * setup are no-op only being used in checkout activity do not anything other things here.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        setup()
        super.onCreate(savedInstanceState)
        setupViewBinding()
        parseExtras()
        viewModel = MobiusLoopViewModel.create(
            { consumer: Consumer<V> ->
                Mobius
                    .loop(update(), RxConnectables.fromTransformer(effectHandler(consumer)))
            },
            initialModel(),
            {
                init().init(it)
            }
        ) as MobiusLoopViewModel<M, E, F, V>

        viewModel.models.observe(this, Observer { model ->
            if (model == null) {
                // This code is unreachable
                throw Exception("Model cannot be null")
            }
            render(model)
        })
    }

    /**
     * Use this function to post an event into the mobius loop.
     */
    override fun postEvent(event: E) {
        viewModel.dispatchEvent(event)
    }

    override fun mobiusViewModel(): MobiusLoopViewModel<M, E, F, V> = viewModel

    open fun getViewModel() : M = viewModel.model

    abstract fun setup()

    abstract fun setupViewBinding()

    /**
     * Render UI as per model
     */
    abstract fun render(model: M)

    abstract fun parseExtras()

}
