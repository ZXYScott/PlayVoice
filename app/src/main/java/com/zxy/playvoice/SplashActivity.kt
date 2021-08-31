package com.zxy.playvoice

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *  闪屏页
 */
class SplashActivity : AppCompatActivity() {

    var ivImage: ImageView? = null

    var tvSplashTitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }


    fun initView() {
        ivImage = findViewById(R.id.iv_image)

        tvSplashTitle = findViewById(R.id.tv_splash_title)

        ivImage?.setOnClickListener { print("this is Play Voice") }

        initObserverable()
    }


    fun initObserverable() {
        Observable.interval(1, TimeUnit.SECONDS)
            .take(3)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.equals(2)) {
                    if (ivImage != null) {
                        ivImage?.postDelayed(Runnable {
                            goMain()
                        }, 1000)
                    }
                }
            }
    }

    fun goMain() {
        val intnet = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
    }
}