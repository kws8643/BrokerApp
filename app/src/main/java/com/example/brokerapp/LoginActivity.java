package com.example.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import LoginFragments.EmailFragment;
import LoginFragments.LoginFragment;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class LoginActivity extends AppCompatActivity {

    public final static String LOGIN_TAG = "LOGIN_TAG";

    FragmentManager manager;
    Fragment loginFragment, emailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initParams();

        manager.beginTransaction().add(R.id.frame_login, loginFragment).commit();

    }


    private void initParams() {

        manager = getSupportFragmentManager();

        loginFragment = new LoginFragment();
        emailFragment = new EmailFragment();

    }

    public void changeFragment(int index) {

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.addToBackStack(null);

        if (index == LoginFragment.FRAGMENT_EMAIL_INDEX) { // 다시 로그인 종류 화면으로

            transaction.setCustomAnimations(R.animator.page_slide_from_left, R.animator.page_slide_to_right)
                    .replace(R.id.frame_login, loginFragment).commit();

        } else if (index == EmailFragment.FRAGMENT_EMAIL_INDEX) { // 이메일로 로그인 & 회원가입 화면으로

            transaction.setCustomAnimations(R.animator.page_slide_from_right, R.animator.page_slide_to_left)
                    .replace(R.id.frame_login, emailFragment).commit();

        }

    }

}
