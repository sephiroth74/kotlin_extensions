package it.sephiroth.android.library.kotlin_extensions.io.reactivex

import androidx.annotation.Keep
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class AutoDisposable : DefaultLifecycleObserver {
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

    @Keep
    override fun onDestroy(owner: LifecycleOwner) {
        if (!compositeDisposable.isDisposed) {
            Timber.v("disposing: $compositeDisposable")
            compositeDisposable.clear()
        }
    }
}
