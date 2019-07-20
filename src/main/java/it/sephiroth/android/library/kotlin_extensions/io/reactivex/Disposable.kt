package it.sephiroth.android.library.kotlin_extensions.io.reactivex

import io.reactivex.disposables.Disposable

fun Disposable.addTo(autoDisposable: AutoDisposable): Disposable {
    autoDisposable.add(this)
    return this
}