package it.sephiroth.android.library.kotlin_extensions.io.reactivex

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.sephiroth.android.library.kotlin_extensions.lang.isMainThread
import java.util.concurrent.TimeUnit

fun <T> rxSingle(thread: Scheduler, func: () -> T): Single<T> {
    return Single.create<T> { emitter ->
        try {
            emitter.onSuccess(func.invoke())
        } catch (error: Throwable) {
            emitter.onError(error)
        }
    }.subscribeOn(thread)
}

fun <T> rxMaybe(thread: Scheduler, func: () -> T?): Maybe<T> {
    return Maybe.create<T> { emitter ->
        try {
            val result = func.invoke()
            if (!emitter.isDisposed) {
                result?.let { emitter.onSuccess(result) } ?: run { emitter.onComplete() }
            }
        } catch (error: Throwable) {
            if (!emitter.isDisposed) emitter.onError(error)
        }
    }.subscribeOn(thread)
}

fun rxCompletable(thread: Scheduler, func: () -> Unit): Completable {
    return Completable.create { emitter ->
        try {
            func.invoke()
            emitter.onComplete()
        } catch (error: Throwable) {
            emitter.onError(error)
        }
    }.subscribeOn(thread)
}

fun rxTimer(
    oldTimer: Disposable?,
    time: Long,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    thread: Scheduler = Schedulers.computation(),
    observerThread: Scheduler = AndroidSchedulers.mainThread(), action: ((Long) -> Unit)
): Disposable? {
    oldTimer?.dispose()
    return Observable
        .timer(time, unit, thread)
        .observeOn(observerThread)
        .subscribe {
            action.invoke(it)
        }
}

fun doOnScheduler(scheduler: Scheduler, func: () -> Unit): Disposable {
    return scheduler.scheduleDirect(func)
}

fun doOnMainThread(func: () -> Unit): Disposable? {
    if (isMainThread()) {
        func.invoke()
        return null
    }
    return doOnScheduler(AndroidSchedulers.mainThread(), func)
}

fun doOnIOThread(func: () -> Unit): Disposable {
    return doOnScheduler(Schedulers.io(), func)
}

fun doOnComputation(func: () -> Unit): Disposable {
    return doOnScheduler(Schedulers.computation(), func)
}

