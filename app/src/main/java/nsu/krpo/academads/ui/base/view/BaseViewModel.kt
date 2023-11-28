package nsu.krpo.academads.ui.base.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update

abstract class BaseViewModel : ViewModel() {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    @Suppress("PropertyName")
    protected val _showErrorDialog = SingleLiveEvent<Throwable>()
    val showErrorDialog: LiveData<Throwable> = _showErrorDialog

    @Suppress("PropertyName")
    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    protected inline fun <T : Any> Single<T>.setupDefaultSchedulers(): Single<T> =
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    protected inline fun Completable.setupDefaultSchedulers(): Completable =
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    protected inline fun Completable.bindLoading(): Completable =
        this.doOnComplete {
            _isLoading.update { false }
        }.doOnError {
            _isLoading.update { false }
        }.doOnSubscribe {
            _isLoading.update { true }
        }

    protected inline fun <T : Any> Single<T>.bindLoading(): Single<T> =
        this.doOnSuccess {
            _isLoading.update { false }
        }.doOnError {
            _isLoading.update { false }
        }.doOnSubscribe {
            _isLoading.update { true }
        }

    protected fun Disposable.unsubscribeOnCleared() {
        subscriptions.add(this)
    }

    fun onError(e: Throwable) {
        _isLoading.update { false }
        _showErrorDialog.update { e }
        Log.e("BaseViewModelErrorHandler", e.stackTraceToString())
    }

    fun onDismiss() = subscriptions.clear()

}