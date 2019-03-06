package com.test.moshitest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    //@Enveloped
    @GET
    fun login(@Url url: String): Observable<UserDto>
}