package com.example.samplemobius.RecyclerView

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplemobius.BaseActivity
import com.example.samplemobius.R
import com.example.samplemobius.RecyclerView.Adapter.CryptoAdaptor

import com.example.samplemobius.RecyclerView.Adapter.ViewItem
import com.example.samplemobius.RecyclerView.api.CryptoDataApi
import com.example.samplemobius.RecyclerView.api.RetrofitHelper
import com.example.samplemobius.RecyclerView.repository.CryptoRepository
import com.example.samplemobius.RecyclerView.schema.CryptoEntity
import com.example.samplemobius.RecyclerView.schema.database.CryptoDatabaseHelper
import com.spotify.mobius.Init
import com.spotify.mobius.Update
import com.spotify.mobius.functions.Consumer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope

class RecyclerView : BaseActivity<RecyclerModel, ViewEvent, RecyclerEffect, Any>() {
    private var adapter=CryptoAdaptor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        item_list.adapter = adapter
        item_list.layoutManager = LinearLayoutManager(this)
        button.setOnClickListener {
            postEvent(ClickEvent)
        }
    }

    override fun setup() {
    }
    override fun setupViewBinding() {
        setContentView(R.layout.activity_main)
    }

    override fun parseExtras() {
    }

    override fun init(): Init<RecyclerModel, RecyclerEffect> {
        return InitLogic()
    }



    override fun initialModel(): RecyclerModel = RecyclerModel.initialModel()

    override fun update(): Update<RecyclerModel, ViewEvent, RecyclerEffect> = RecyclerLogic()

    override fun render(model: RecyclerModel){
        adapter.submitList(model.items)

    }

    override fun effectHandler(consumer: Consumer<Any>): ObservableTransformer<RecyclerEffect, ViewEvent> {
  return RecyclerEffectHandler().createEffectHandler(CryptoRepository(RetrofitHelper.cryptoApi,CryptoDatabaseHelper.getDatabase(this).cryptoDao(),applicationContext), scope = GlobalScope)
    }

//    override fun showData(data: List<ViewItem>) {
//        adapter.submitList(data)
//    }
}