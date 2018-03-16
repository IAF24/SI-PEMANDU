package id.tiregdev.si_pemandu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.tiregdev.si_pemandu.Activity.cari_data_pendaftaran;
import id.tiregdev.si_pemandu.Activity.data_sasaran;
import id.tiregdev.si_pemandu.R;

/**
 * Created by Muhammad63 on 3/16/2018.
 */

public class laporan extends Fragment {

    View v;
    Button sasaran, pendaftaran;

    public static laporan newInstance(){
        laporan fragment = new laporan();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_laporan, container, false);
        findID();
        return v;
    }

    public void findID(){
        sasaran = v.findViewById(R.id.sasaran);
        pendaftaran = v.findViewById(R.id.pendaftaran);

        sasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), data_sasaran.class);
                startActivity(i);
            }
        });

        pendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), cari_data_pendaftaran.class);
                startActivity(i);
            }
        });
    }
}
