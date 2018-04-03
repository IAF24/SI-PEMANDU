package id.tiregdev.si_pemandu.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.tiregdev.si_pemandu.R;
import id.tiregdev.si_pemandu.utils.AppConfig;
import id.tiregdev.si_pemandu.utils.AppControl;
import id.tiregdev.si_pemandu.utils.adapter_anak;
import id.tiregdev.si_pemandu.utils.itemObject_anak;

import static java.security.AccessController.getContext;

public class nfcreader extends AppCompatActivity {

    RecyclerView rView;
    LinearLayoutManager lLayout;
    private static final String TAG = nfcreader.class.getSimpleName();
    itemObject_anak DataAnak;
    adapter_anak rcAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcreader);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        displayData();
        Button ok = findViewById(R.id.oke);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Intent i = new Intent(nfcreader.this, data_anak.class);
                LayoutInflater factory = LayoutInflater.from(nfcreader.this);
                final View exitDialogView = factory.inflate(R.layout.dialgog_anak, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(nfcreader.this).create();
                final LinearLayoutManager lLayoutCube;
                final RecyclerView rViewCube;
                lLayoutCube = new LinearLayoutManager(exitDialogView.getContext());
                rViewCube = exitDialogView.findViewById(R.id.rView);
                rViewCube.setLayoutManager(lLayoutCube);
                rViewCube.setAdapter(rcAdapter);
                exitDialog.setView(exitDialogView);
                exitDialog.show();
//                i.putExtra("reversedhex",reversed.getText().toString());
//                i.putExtra("nik",reversed.getText().toString());
//                startActivity(i);
            }


        });

        Button tmbhData = findViewById(R.id.tambahData);
        tmbhData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(nfcreader.this, input_data_anak.class);
                i.putExtra("reversedhex",reversed.getText().toString());
                startActivity(i);
            }
        });
    }

    private TextView tanggal;
    private TextView time;
    private TextView hex;
    private TextView reversed;
    private TextView dec;
    private TextView reversedDec;
    private TextView teknologi;
    JSONObject jObj;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-03-19 13:46:36 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tanggal = (TextView)findViewById( R.id.tanggal );
        time = (TextView)findViewById( R.id.time );
        hex = (TextView)findViewById( R.id.hex );
        reversed = (TextView)findViewById( R.id.reversed );
        dec = (TextView)findViewById( R.id.dec );
        reversedDec = (TextView)findViewById( R.id.reversedDec );
        teknologi = (TextView)findViewById( R.id.teknologi );

        DateFormat df = new SimpleDateFormat("HH:mm"); //format time
        String timeNow = df.format(Calendar.getInstance().getTime());

        DateFormat df1=new SimpleDateFormat("dd/MM/yyyy");//foramt date
        String date=df1.format(Calendar.getInstance().getTime());

        hex.setText(getIntent().getExtras().getString("IDHex"));
        reversed.setText(getIntent().getExtras().getString("IDReversedHex"));
        dec.setText(String.valueOf(getIntent().getExtras().getLong("IDDec")));
        reversedDec.setText(String.valueOf(getIntent().getExtras().getLong("IDReversedDec")));
        tanggal.setText(date);
        time.setText(timeNow);
    }

    private void displayData(){


        final String no_nfcs = getIntent().getExtras().getString("IDReversedHex");
        String tag_string_req = "show_nama_anak";
//        Toast.makeText(this, no_nfcs, Toast.LENGTH_SHORT).show();
        JsonArrayRequest strReq = new JsonArrayRequest(
                AppConfig.LIST_DATA + "?no_nfc=" + no_nfcs, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "Register Response: " + response.toString());
                List<itemObject_anak> dialog = new ArrayList<>();

                for(int i = 0;i<response.length();i++){
                    DataAnak = new itemObject_anak();
                    jObj = null;
                    try {

                        jObj = response.getJSONObject(i);

                        String id_anaks = jObj.getString("id_anak");
                        String nama_anak = jObj.getString("nama_anak");
                        dialog.add(new itemObject_anak(
                               nama_anak, id_anaks
                        ));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rcAdapter = new adapter_anak(getApplicationContext(), dialog);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppControl.getInstance().addToRequestQueue(strReq);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                nfcreader.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
