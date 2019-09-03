package ejarosiewicz.com.async

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class AndroidScheduler: Scheduler {

    private val disposables = HashMap<String, Disposable>()

    override fun <T> schedule(
        subscriber: String,
        source: Single<T>,
        onComplete: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dispose(subscriber)
        disposables[subscriber] = source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({data -> onComplete(data)}
                ,{throwable -> onError(throwable)})
    }

    override fun dispose(subscriber: String) {
        disposables[subscriber]?.dispose()
        disposables.remove(subscriber)
    }
}