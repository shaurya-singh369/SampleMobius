//package com.example.samplemobius
//
//import androidx.lifecycle.LiveData
//
//import com.example.samplemobius.BaseMobiusVVMProviderFragment
//import android.os.Bundle
//import android.os.Parcelable
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.LayoutRes
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import com.spotify.mobius.android.MobiusLoopViewModel
//import timber.log.Timber
//
//abstract class BaseMobiusVVMFragment<M : Parcelable, E, F, V>: Fragment(), EventDispatcher<E>, ViewProvider {
//
//    lateinit var viewModel: MobiusLoopViewModel<M, E, F, V>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (this is BaseMobiusVVMProviderFragment && isViewModelInitialized().not()){
//            viewModel = createViewModel()
//            Timber.d("View model created: $viewModel")
//        }
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        val activity = requireActivity()
//        if (activity is BaseMobiusProvider<*, *, *, *>) {
//            viewModel = activity.mobiusViewModel() as MobiusLoopViewModel<M, E, F, V>
//        }
//        if(::viewModel.isInitialized){
//            viewModel.models.toSingleEvent().observe(
//                viewLifecycleOwner,
//                Observer {
//                    render(it)
//                })
//        }else{
//            throw RuntimeException("ViewModel has not been initialized.")
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? = LayoutInflater.from(context).inflate(layoutResId(), container, false)
//
//    /**
//     * Use this function to post an event into the mobius loop.
//     */
//    override fun postEvent(event: E) {
//        if (::viewModel.isInitialized) {
//            viewModel.dispatchEvent(event)
//        } else {
//            Timber.e("View Model is not initialized, not posting event.")
//        }
//    }
//
//    /**
//     * View is null here; do not access fragment in this method
//     */
//    open fun createViewModel(): MobiusLoopViewModel<M, E, F, V> {
//        throw RuntimeException("Cannot attach base mobius fragment to non mobius base activity. Make sure to keep view model inside activity scope and not fragment.")
//    }
//    /**
//     * Provide resource to set as content view.
//     */
//    @LayoutRes
//    abstract fun layoutResId(): Int
//    /**
//     * Render UI as per model
//     */
//    abstract fun render(model: M)
//
//    /**
//     * check if [viewModel] is initialized
//     * this should be done here as we cannot access backing field in subclasses
//     * */
//    fun isViewModelInitialized() = ::viewModel.isInitialized
//
//    override fun provideView(): View? = view
//}
//
//interface BaseMobiusProvider<M: Parcelable, E, F, V> {
//    fun mobiusViewModel(): MobiusLoopViewModel<M, E, F, V>
//}
