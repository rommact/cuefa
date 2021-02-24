package com.rommac.mvp

import androidx.annotation.StringRes
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.rommac.core_api.Event

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val TAG: String = this::class.qualifiedName ?: "Unknown Presenter"
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected val event:Event<Unit>
    get() = Event(Unit)

    protected fun fire(liveData: MutableLiveData<Event<Unit>>){
        liveData.value = event
    }

    abstract fun viewIsReady()


    protected fun disposable(disposable: Disposable): Disposable {
        compositeDisposable.add(disposable)
        return disposable
    }

//    protected fun showError(@StringRes textId: Int) {
//        view?.showError(textId)
//    }
//
//    protected fun showError() {
//        showError(R.string.any_error)
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }




}
