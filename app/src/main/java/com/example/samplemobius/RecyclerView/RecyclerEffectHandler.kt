package com.example.samplemobius.RecyclerView

import com.example.samplemobius.RecyclerView.Adapter.ViewItem
import com.example.samplemobius.RecyclerView.repository.CryptoRepository
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RecyclerEffectHandler {

    fun createEffectHandler(

        cryptoRepository: CryptoRepository,
        scope: CoroutineScope
    ): ObservableTransformer<RecyclerEffect, ViewEvent> {

        return RxMobius.subtypeEffectHandler<RecyclerEffect, ViewEvent>()
            .addTransformer(
                FetchDataEffect::class.java,
                getData(cryptoRepository, scope)
            ).build()

    }

    private fun getData(
        cryptoRepository: CryptoRepository,
        coroutineScope: CoroutineScope
    ): ObservableTransformer<FetchDataEffect, ViewEvent>? {
        return ObservableTransformer { effect ->
            effect.flatMap {
                ObservableSource { emitter ->
                    coroutineScope.launch {
                        val response = cryptoRepository.getCryptoData()
                        val viewItems= response.map {
                            ViewItem(it.name, it.priceUsd, it.symbol)
                        }
                        emitter.onNext(DataEvent(viewItems))
                    }
                }
            }
        }
    }
}