package LoginFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.brokerapp.LoginActivity;
import com.example.brokerapp.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import AppNetworking.NaverOAuthClient;


public class LoginFragment extends Fragment {

    public static final int FRAGMENT_EMAIL_INDEX = 0;

    LoginActivity loginActivity;

    // 유저가 누르는 버튼들
    private LinearLayout btnKakao, btnNaver, btnEmail;

    // Kakao OAuth Variables
    private LoginButton btnAccessKakao;
    private Session mKakaoSession;
    private SessionCallback mSessionCallback;

    // Naver OAuth Variables
    private OAuthLoginButton btnAccessNaver;
    private OAuthLogin mNaverSession;
    private OAuthLoginHandler mOAuthLoginHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login_login, container, false);

        initView(view);

        initParams();


        btnKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mKakaoSession = Session.getCurrentSession();
                mSessionCallback = new SessionCallback();

                mKakaoSession.addCallback(mSessionCallback);
                mKakaoSession.close();

                btnAccessKakao.performClick();

            }
        });


        btnNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initNaverSession();

                btnAccessNaver.setOAuthLoginHandler(mOAuthLoginHandler);

                btnAccessNaver.performClick();

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity.changeFragment(EmailFragment.FRAGMENT_EMAIL_INDEX);
            }
        });

        return view;
    }


    private void initView(View view) {

        btnKakao = view.findViewById(R.id.btnKakao);
        btnNaver = view.findViewById(R.id.btnNaver);
        btnEmail = view.findViewById(R.id.btnEmail);

        btnAccessKakao = view.findViewById(R.id.btnAccessKakao);
        btnAccessNaver = view.findViewById(R.id.btnAccessNaver);

    }

    private void initParams() {

        loginActivity = (LoginActivity) getActivity();

        initNaverOAuthHandler(); // OAuth 구분을 위해 메소드로 지정

    }


    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------Kakao OAuth
    //--------------------------------------------------------------------------------------------

    // 카카오 세션 형성 후에 콜백되는 메소드
    public class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() { // 세션이 열림

            String accToken = mKakaoSession.getAccessToken();

            Toast.makeText(loginActivity.getApplicationContext(), "액세스 토큰 부여: " + accToken, Toast.LENGTH_SHORT).show();

            // 열렸으니 데이터 파싱 가능하게끔 세팅

            return;

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) { // 세션 오픈 실패

            Log.w(LoginActivity.LOGIN_TAG, "SessionCallback:: " + exception.getMessage());

            Toast.makeText(loginActivity.getApplicationContext(), "카카오 로그인 실패", Toast.LENGTH_SHORT).show();

            return;
        }
    }

    @Override
    public void onDestroy() { // 세션 누적때문에 콜백을 제거해준다
        super.onDestroy();

        if (mSessionCallback != null) {
            mKakaoSession.removeCallback(mSessionCallback);
        }
    }


    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------Naver OAuth
    //--------------------------------------------------------------------------------------------

    private void initNaverSession() {

        mNaverSession = OAuthLogin.getInstance();
        mNaverSession.init(loginActivity.getApplicationContext()
                , NaverOAuthClient.getNaverOauthClientId()
                , NaverOAuthClient.getNaverOauthClientSecret()
                , NaverOAuthClient.getNaverOauthClientName());


    }

    private void initNaverOAuthHandler() {

        mOAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {

                    String accToken = mNaverSession.getAccessToken(loginActivity.getApplicationContext());

                    Toast.makeText(loginActivity.getApplicationContext(), "액세스 토큰 부여: " + accToken, Toast.LENGTH_SHORT).show();

                    return;

                } else {

                    String errorCode = mNaverSession.getLastErrorCode(loginActivity.getApplicationContext()).getCode();
                    String errorDesc = mNaverSession.getLastErrorDesc(loginActivity.getApplicationContext());

                    Toast.makeText(loginActivity.getApplicationContext(), "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();

                    return;
                }
            }
        };

    }

}
