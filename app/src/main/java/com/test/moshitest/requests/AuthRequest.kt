package com.test.moshitest.requests

import com.test.moshitest.ApiInterface
import com.test.moshitest.UserDto
import io.reactivex.Observable

class AuthRequest : Request<UserDto> {
    override fun execute(endpoint: ApiInterface): Observable<UserDto> {
        //return endpoint.login("http://www.mocky.io/v2/5c800c9a330000c339848535")
        return endpoint.login("http://www.mocky.io/v2/5c801afb3300009b31848571")
    }
}