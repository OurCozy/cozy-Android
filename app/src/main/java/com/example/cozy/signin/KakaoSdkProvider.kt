package com.example.cozy.signin

import android.app.Application
import com.kakao.auth.KakaoSDK

class KakaoSdkProvider : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        KakaoSDK.init(KakaoSdkAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    fun getGlobalApplicationContext(): KakaoSdkProvider {
        checkNotNull(instance) { "this application does not inherit com.kakao.KakaoSdkProvider" }
        return instance!!
    }

    companion object {
        var instance: KakaoSdkProvider? = null
    }
}