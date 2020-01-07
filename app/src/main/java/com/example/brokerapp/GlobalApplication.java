package com.example.brokerapp;

import android.app.Application;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.kakao.util.helper.CommonProtocol;
import com.kakao.util.helper.Utility;

import AppTools.KakaoSDKAdapter;

//Kakao 어플이라는 Context를 부여하는 클래 //초기화시 실행된다.
public class GlobalApplication extends Application {

    private static volatile GlobalApplication instance = null;

    // 어플의 Context(Not Activity)
    public static GlobalApplication getGlobalApplicationContext() {

        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }


    // Application 을 Extend 하므로 onCreate 을 통해서 실행은 된다.
    // Manifest 에서의 지정 떄문에 가장 먼저 초기화가 실현된다.
    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;

        String s = Utility.getMetadata(this, CommonProtocol.APP_KEY_PROPERTY);

        Log.i(LoginActivity.LOGIN_TAG, "rolly rolly:" + s);

        // 어플 실행 시 카카오 아답터 준비시켜 놓기. (사용하든 안하든)
        KakaoSDK.init(new KakaoSDKAdapter());

    }
}
