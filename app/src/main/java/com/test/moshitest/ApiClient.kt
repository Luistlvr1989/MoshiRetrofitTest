package com.test.moshitest

import android.util.Log
import com.test.moshitest.requests.Request
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

object ApiClient : Serializable {
    private val endpoint: ApiInterface = RetrofitFactory.endpoint

    internal fun <T> execute(request: Request<T>): Observable<T> {
        return request.execute(endpoint)
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext { throwable: Throwable ->
                Log.e("ApiClient", "Exception", throwable)
                Observable.error(throwable)
            }
            .replay(1)
            .autoConnect(1)
    }
}