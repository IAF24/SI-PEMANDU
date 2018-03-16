package id.tiregdev.si_pemandu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.tiregdev.si_pemandu.Activity.data_anak;
import id.tiregdev.si_pemandu.R;

/**
 * Created by Muhammad63 on 3/16/2018.
 */

public class cari_data_anak extends Fragment {

    View v;
    Button cari;

    public static cari_data_anak newInstance(){
        cari_data_anak fragment = new cari_data_anak();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cari_data_anak, container, false);
        cari = v.findViewById(R.id.cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), data_anak.class);
                startActivity(i);
            }
        });
        return v;
    }
}
