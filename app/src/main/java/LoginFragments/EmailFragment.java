package LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.brokerapp.LoginActivity;
import com.example.brokerapp.R;

public class EmailFragment extends Fragment {

    LoginActivity activity;

    private ImageView btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login_email, container, false);

        initView(view);

        initParams();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(0);
            }
        });

        return view;
    }


    private void initView(View view) {

        btnBack = view.findViewById(R.id.btnBack);

    }

    private void initParams() {
        activity = (LoginActivity) getActivity();
    }

}
