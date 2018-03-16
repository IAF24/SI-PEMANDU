package id.tiregdev.si_pemandu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.tiregdev.si_pemandu.Activity.nfc;
import id.tiregdev.si_pemandu.R;

/**
 * Created by Muhammad63 on 3/16/2018.
 */

public class home extends Fragment {

    View v;
    Button scan;

    public static home newInstance(){
        home fragment = new home();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        findId();
        return v;
    }

    public void findId(){
        scan = v.findViewById(R.id.scan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), nfc.class);
                startActivity(i);
            }
        });

    }
}
