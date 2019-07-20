package it.sephiroth.android.library.kotlin_extensions.io.reactivex

import androidx.annotation.Keep
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class AutoDisposable : LifecycleObserver {
    private lateinit var compositeDisposable: CompositeDisposable

    constructor(owner: LifecycleOwner) : this(owner.lifecycle)

    constructor(lifecycle: Lifecycle) {
        bindTo(lifecycle)
    }

    private fun bindTo(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
        compositeDisposable = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.add(disposable)
        } else {
            throw NotImplementedError("must bind AutoDisposable to a Lifecycle first")
        }
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Keep
    fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            Timber.v("disposing: $compositeDisposable")
            compositeDisposable.dispose()
        }
    }
}