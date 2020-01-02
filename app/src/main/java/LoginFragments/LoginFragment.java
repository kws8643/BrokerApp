package LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.brokerapp.LoginActivity;
import com.example.brokerapp.R;


public class LoginFragment extends Fragment {

    LoginActivity activity;

    private LinearLayout btnKakao, btnNaver, btnEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login_login, container, false);

        initView(view);

        initParams();

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(1);
            }
        });


        return view;
    }


    private void initView(View view) {
        btnKakao = view.findViewById(R.id.btnKakao);
        btnNaver = view.findViewById(R.id.btnNaver);
        btnEmail = view.findViewById(R.id.btnEmail);
    }

    private void initParams() {

        activity = (LoginActivity) getActivity();

    }
}
