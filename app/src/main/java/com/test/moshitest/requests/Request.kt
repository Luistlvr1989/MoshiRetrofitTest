package com.test.moshitest.requests

import com.test.moshitest.ApiInterface
import io.reactivex.Observable

interface Request<T> {
    fun execute(endpoint: ApiInterface): Observable<T>
}