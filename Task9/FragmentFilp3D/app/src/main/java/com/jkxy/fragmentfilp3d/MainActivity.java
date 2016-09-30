package com.jkxy.fragmentfilp3d;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, new ImgFrontFragment())
                .commit();
    }

    public static class ImgFrontFragment extends Fragment {
        private Button btnGoNext;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_imgfront, container, false);

            //Button: go next page
            rootView.findViewById(R.id.btn_main_gonext).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(
                                    R.animator.main_fliprightin, R.animator.main_fliprightout,
                                    R.animator.main_flipleftin, R.animator.main_flipleftout)
                            .replace(R.id.main_container, new ImgBackFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });

            return rootView;
        }
    }

    public static class ImgBackFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_imgback, container, false);

            //Button: go back
            rootView.findViewById(R.id.btn_main_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager()
                            .popBackStack();
                }
            });

            return rootView;
        }
    }
}


