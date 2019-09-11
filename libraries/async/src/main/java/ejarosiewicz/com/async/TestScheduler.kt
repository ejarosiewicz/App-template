package ejarosiewicz.com.async

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TestScheduler : Scheduler {

    @SuppressLint("CheckResult")
    override fun <T> schedule(
        subscriber: String,
        source: Single<T>,
        onComplete: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        source
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .subscribe({ data -> onComplete(data) }
                , { throwable -> onError(throwable) })
    }

    override fun dispose(subscriber: String) {
    }
}