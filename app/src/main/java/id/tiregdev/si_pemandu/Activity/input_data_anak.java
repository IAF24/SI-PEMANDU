package id.tiregdev.si_pemandu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.tiregdev.si_pemandu.R;
import id.tiregdev.si_pemandu.utils.SQLiteHandler;
import id.tiregdev.si_pemandu.utils.AppConfig;
import id.tiregdev.si_pemandu.utils.AppControl;

public class input_data_anak extends AppCompatActivity implements View.OnClickListener {


    private TextView no_nfc;
    private TextView nik;
    private TextView nama_anak;
    private RadioGroup jenis_kelamin;
    private TextView ttl;
    private TextView nama_ibu;
    private TextView nama_ayah;
    private TextView anak_ke;
    private TextView jml_saudara;
    private TextView alamat;
    private TextView no_hp;
    private Button save;
    private ProgressDialog pDialog;
    private static final String TAG = input_data_anak.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_anak);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViews();
    }

    private void findViews() {
        no_nfc = (TextView) findViewById( R.id.noNFC );
        nik = (TextView)findViewById( R.id.nik );
        nama_anak = (TextView)findViewById( R.id.namaAnak );
        jenis_kelamin = (RadioGroup) findViewById( R.id.jk );
        ttl = (TextView)findViewById( R.id.ttl );
        nama_ibu = (TextView)findViewById( R.id.namaIbu );
        nama_ayah = (TextView)findViewById( R.id.namaAyah );
        anak_ke = (TextView)findViewById( R.id.anakKe );
        jml_saudara = (TextView)findViewById( R.id.jumlahSaudara );
        alamat = (TextView)findViewById( R.id.alamat );
        no_hp = (TextView)findViewById( R.id.noHp );
        save = (Button) findViewById( R.id.save );
        pDialog = new ProgressDialog(this);
        save.setOnClickListener( this );
        no_nfc.setText(getIntent().getExtras().getString("reversedhex"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                input_data_anak.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendData(){

        final String nomor_nfc = no_nfc.getText().toString().trim();
        final String _nik = nik.getText().toString().trim();
        final String _nama_anak = nama_anak.getText().toString().trim();
        final String _jenis_kelamain = ((RadioButton)findViewById(jenis_kelamin.getCheckedRadioButtonId())).getText().toString();
        final String _ttl = ttl.getText().toString().trim();
        final String _nama_ibu = nama_ibu.getText().toString().trim();
        final String _nama_ayah = nama_ayah.getText().toString().trim();
        final String _anak_ke = anak_ke.getText().toString().trim();
        final String _jml_saudara = nama_ibu.getText().toString().trim();
        final String _alamat = alamat.getText().toString().trim();
        final String _no_hp  = no_hp.getText().toString().trim();

        String tag_string_req = "req_data_anak";

        pDialog.setMessage("Mengirim Permintaan ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.REG_TAMBAH_DATA, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
//                        String uid = jObj.getString("uid");
//
//                        JSONObject user = jObj.getJSONObject("user");
//                        String name = user.getString("name");
//                        String email = user.getString("email");
//                        String alamat = user.getString("alamat");
//                        String no_telp = user.getString("no_telp");
//                        String tanggal_lahir = user.getString("tanggal_lahir");
//                        String bio = user.getString("bio");
//                        String foto_user = user.getString("foto");
//
//                        // Inserting row in users table
//                        db.updateUser(name, email, uid, alamat, no_telp, tanggal_lahir, bio, foto_user);

                        Toast.makeText(getApplicationContext(), "Data berhasil terkirim!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(getBaseContext(),
                                data_anak.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
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
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_nfc", nomor_nfc);
                params.put("nik", _nik);
                params.put("nama_anak", _nama_anak);
                params.put("jenis_kelamin", _jenis_kelamain);
                params.put("ttl", _ttl);
                params.put("nama_ibu", _nama_ibu);
                params.put("nama_ayah", _nama_ayah);
                params.put("anak_ke", _anak_ke);
                params.put("jml_saudara", _jml_saudara);
                params.put("alamat", _alamat);
                params.put("no_hp", _no_hp);
                return params;
            }

        };

// Adding request to request queue
        AppControl.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-17 13:34:08 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == save ) {
            // Handle clicks for kirim
            sendData();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
