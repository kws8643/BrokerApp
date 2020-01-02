package com.example.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import LoginFragments.EmailFragment;
import LoginFragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

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

        if (index == 0) { // 다시 로그인 종류 화면으로

            transaction.setCustomAnimations(R.anim.page_slide_from_left, R.anim.page_slide_to_right)
                    .replace(R.id.frame_login, loginFragment).commit();

        } else if (index == 1) { // 이메일로 로그인 & 회원가입 화면으로

            transaction.setCustomAnimations(R.anim.page_slide_from_right, R.anim.page_slide_to_left)
                    .replace(R.id.frame_login, emailFragment).commit();

        }

    }
}
