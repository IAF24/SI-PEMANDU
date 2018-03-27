package id.tiregdev.si_pemandu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import id.tiregdev.si_pemandu.Activity.user_profile;
import id.tiregdev.si_pemandu.R;
import id.tiregdev.si_pemandu.utils.SQLiteHandler;
/**
 * Created by Muhammad63 on 3/16/2018.
 */

public class user extends Fragment {

    View v;
    Button edit;

    public static user newInstance() {
        user fragment = new user();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                setViews();
                findViews();
            }
        });


        return v;
    }

    private TextView user;

    private TextView jointgl;
    private TextView bio;
    private TextView email;
    private TextView alamat;
    private TextView tlp;
    private TextView ttl;
    private SQLiteHandler db;

    private void findViews() {

        user = (TextView)v.findViewById( R.id.user );
        jointgl = (TextView)v.findViewById( R.id.jointgl );
        bio = (TextView)v.findViewById( R.id.bio );
        email = (TextView)v.findViewById( R.id.email );
        alamat = (TextView)v.findViewById( R.id.alamat );
        tlp = (TextView)v.findViewById( R.id.tlp );
        ttl = (TextView)v.findViewById( R.id.ttl );

        db = new SQLiteHandler(getContext());


    }

    private void setViews(){
        user.setText(db.getUserDetails().get("nama_admin"));
        email.setText(db.getUserDetails().get("email"));
        alamat.setText(db.getUserDetails().get("alamat"));
        tlp.setText(db.getUserDetails().get("no_hp"));
        ttl.setText(db.getUserDetails().get("tgl_lahir"));
        bio.setText(db.getUserDetails().get("bio"));
        jointgl.setText(db.getUserDetails().get("join_tgl"));


    }
}

