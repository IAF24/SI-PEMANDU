package id.tiregdev.si_pemandu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.tiregdev.si_pemandu.Activity.user_profile;
import id.tiregdev.si_pemandu.R;

/**
 * Created by Muhammad63 on 3/16/2018.
 */

public class user extends Fragment {

    View v;
    Button edit;

    public static user newInstance(){
        user fragment = new user();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user, container, false);
        edit = v.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), user_profile.class);
                startActivity(i);
            }
        });
        return v;
    }
}
