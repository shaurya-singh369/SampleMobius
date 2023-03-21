package com.example.samplemobius.RecylerView

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplemobius.BaseActivity
import com.example.samplemobius.R
import com.example.samplemobius.RecylerView.Adapter.ProgrammingAdaptor

import com.example.samplemobius.RecylerView.Adapter.ViewItem
import com.spotify.mobius.Init
import com.spotify.mobius.Update
import com.spotify.mobius.functions.Consumer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_main.*

class RecyclerView : BaseActivity<ViewModel, ViewEvent, ViewEffect, Any>() {
    private var adapter=ProgrammingAdaptor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        item_list.adapter = adapter
        item_list.layoutManager = LinearLayoutManager(this)
        val items= listOf(
            ViewItem("one",1),
            ViewItem("two",2),
            ViewItem("three",3),
            ViewItem("four",4),
        )
        adapter.submitList(items)

    }

    override fun setup() {
        TODO("Not yet implemented")
    }
    override fun setupViewBinding() {
        setContentView(R.layout.activity_main)
    }

    override fun parseExtras() {
        TODO("Not yet implemented")
    }

    override fun init(): Init<ViewModel, ViewEffect> {
        TODO("Not yet implemented")
    }

    override fun initialModel(): ViewModel = ViewModel.initialModel()

    override fun update(): Update<ViewModel, ViewEvent, ViewEffect> = RecyclerLogic()

    override fun render(model: ViewModel){
        adapter.submitList(model.items)

    }

    override fun effectHandler(consumer: Consumer<Any>): ObservableTransformer<ViewEffect, ViewEvent> {
        return ObservableTransformer { effects ->
            effects.flatMap { effect ->
                when (effect) {
                    is ViewEffect -> {
                        Observable.empty()
                    }
                    else -> {
                        Observable.empty()}
                }
            }
        }
    }
}