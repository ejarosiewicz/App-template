package ejarosiewicz.com.async

import io.reactivex.Single

interface Scheduler {

    fun <T> schedule(
        subscriber: String,
        source: Single<T>,
        onComplete: (T) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun dispose(subscriber: String)

}