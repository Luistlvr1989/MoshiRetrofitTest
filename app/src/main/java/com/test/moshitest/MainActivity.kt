package com.test.moshitest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.moshitest.requests.AuthRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bTest.setOnClickListener {
            ApiClient.execute(AuthRequest())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userDto ->
                    Log.e("Auth", userDto.toString())
                }, { error ->
                    Log.e("Auth", "Exception", error)
                })
        }
    }
}
