package id.tiregdev.si_pemandu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

import id.tiregdev.si_pemandu.R;
import id.tiregdev.si_pemandu.utils.AppConfig;
import id.tiregdev.si_pemandu.utils.AppControl;

public class data_anak extends AppCompatActivity  {


    private TextView nik;
    private TextView nama_ibu;
    private TextView nama_ayah;
    private TextView alamat;
    private TextView nama_anak;
    private TextView no_nfc;
    private Button ok;
    String ids;
    private static final String TAG = data_anak.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_anak);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        if (getIntent().getExtras().containsKey("_niks")){
           displayDataNik();
         }
        else if(getIntent().getExtras().containsKey("id_anak")) {
           displayData();
         }

       /* Button ok = findViewById(R.id.oke);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(data_anak.this, modul.class);
                startActivity(i);
            }
        });*/
        Button ok = findViewById(R.id.oke);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(data_anak.this, modul.class);
                i.putExtra("idanak",ids);
                startActivity(i);
            }});
    }


    private void displayData(){

        final String id_anak = (getIntent().getExtras().getString("id_anak"));
        String tag_string_req = "show_data_anak";
//        Toast.makeText(this, no_nfcs, Toast.LENGTH_SHORT).show();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.DATA_ANAK + "?id_anak=" + id_anak, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {

                    ids = jObj.getString("id_anak");
                    String niks = jObj.getString("nik");
                    String nama_ibus = jObj.getString("nama_ibu");
                    String nama_ayahs = jObj.getString("nama_ayah");
                    String alamats = jObj.getString("alamat");
                    String nama_anaks  = jObj.getString("nama_anak");
                    nik.setText(niks);
                    nama_ibu.setText(nama_ibus);
                    nama_ayah.setText(nama_ayahs);
                    alamat.setText(alamats);
                    nama_anak.setText(nama_anaks);



//                    } else {
//
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "Failed with error msg:\t" + error.getMessage());
                Log.d(TAG, "Error StackTrace: \t" + error.getStackTrace());
                // edited here
                try {
                    byte[] htmlBodyBytes = error.networkResponse.data;
                    Log.e(TAG, new String(htmlBodyBytes), error);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id_anak", id_anak);
                return params;
            }

        };

        AppControl.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    private void displayDataNik(){

        final String niks = (getIntent().getExtras().getString("_niks"));
        String tag_string_req = "show_data_anak";
//        Toast.makeText(this, no_nfcs, Toast.LENGTH_SHORT).show();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.DATA_ANAK + "?nik=" + niks, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {

                    ids = jObj.getString("id_anak");
                    String niks = jObj.getString("nik");
                    String nama_ibus = jObj.getString("nama_ibu");
                    String nama_ayahs = jObj.getString("nama_ayah");
                    String alamats = jObj.getString("alamat");
                    String nama_anaks  = jObj.getString("nama_anak");
                    nik.setText(niks);
                    nama_ibu.setText(nama_ibus);
                    nama_ayah.setText(nama_ayahs);
                    alamat.setText(alamats);
                    nama_anak.setText(nama_anaks);



//                    } else {
//
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "Failed with error msg:\t" + error.getMessage());
                Log.d(TAG, "Error StackTrace: \t" + error.getStackTrace());
                // edited here
                try {
                    byte[] htmlBodyBytes = error.networkResponse.data;
                    Log.e(TAG, new String(htmlBodyBytes), error);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("nik", niks);
                return params;
            }

        };

        AppControl.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    private void findViews() {
        nik = (TextView)findViewById( R.id.nik );
        nama_ibu = (TextView)findViewById( R.id.ibu );
        nama_ayah = (TextView)findViewById( R.id.ayah );
        alamat = (TextView)findViewById( R.id.alamat );
        ok = (Button) findViewById( R.id.oke );
        nama_anak = (TextView)findViewById( R.id.namaAnak );

//        nik.setText(getIntent().getExtras().getString("nik"));
//        nama_ibu.setText(getIntent().getExtras().getString("nama_ibu"));
//        nama_ayah.setText(getIntent().getExtras().getString("nama_ayah"));
//        alamat.setText(getIntent().getExtras().getString("alamat"));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                data_anak.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
