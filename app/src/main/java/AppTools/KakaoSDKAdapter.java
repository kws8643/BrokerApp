package AppTools;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.brokerapp.GlobalApplication;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;

public class KakaoSDKAdapter extends KakaoAdapter {

    // 아답터의 역할 1) Application 정보 연결할 준비.
    @Override
    public IApplicationConfig getApplicationConfig() {
        return new IApplicationConfig() {
            @Override
            public Context getApplicationContext() {
                return GlobalApplication.getGlobalApplicationContext();
            }
        };
    }


    // 2) Session 을 어떻게 구성할 건지 조립.
    @Override
    public ISessionConfig getSessionConfig() {
        return new ISessionConfig() {
            @Override
            public AuthType[] getAuthTypes() {
                return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
            }

            @Override
            public boolean isUsingWebviewTimer() {
                return false;
            }


            // 로그인 시 토큰을 저장할 떄 암호화 해서 저장할 것인지?
            @Override
            public boolean isSecureMode() {
                return false;
            }

            @Nullable
            @Override
            public ApprovalType getApprovalType() {
                return ApprovalType.INDIVIDUAL;
            }


            //이메일 입력폼에서 데이터를 저장할 건지 결정. default = true;
            @Override
            public boolean isSaveFormData() {
                return true;
            }
        };
    }

}
