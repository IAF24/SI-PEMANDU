package id.tiregdev.si_pemandu.Activity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.tiregdev.si_pemandu.R;
import id.tiregdev.si_pemandu.utils.SQLiteHandler;
import id.tiregdev.si_pemandu.utils.AppConfig;
import  id.tiregdev.si_pemandu.utils.AppControl;


public class user_profile extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = user_profile.class.getSimpleName();

    private TextView bio;
    private TextView email;
    private TextView alamat;
    private TextView no_hp;
    private TextView tgl_lahir;
    private TextView username;
    private TextView user;
    private Button save;
    private ProgressDialog pDialog;
    private SQLiteHandler db;

    SimpleDateFormat sdf;
    Calendar dateAndTime;
    DatePickerDialog.OnDateSetListener d;
    EditText ttl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findViews();
        setViews();
        setInit();
    }

   /* @Override
   public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                user_profile.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
   private void findViews() {
       user = (TextView) findViewById(R.id.namakader) ;
       username = (TextView) findViewById(R.id.username);
       email = (TextView) findViewById(R.id.email);
       alamat = (TextView) findViewById(R.id.alamat);
       no_hp = (TextView) findViewById(R.id.noHP);
       tgl_lahir = (TextView) findViewById(R.id.tglLahir);
       bio = (TextView) findViewById(R.id.bio);
       save = (Button) findViewById(R.id.saveChanges);
       pDialog = new ProgressDialog(this);
       db = new SQLiteHandler(getApplicationContext());
       sdf = new SimpleDateFormat("dd-MM-yyyy");
   }

    private void setViews() {
        user.setText(db.getUserDetails().get("nama_admin"));
        username.setText(db.getUserDetails().get("username"));
        email.setText(db.getUserDetails().get("email"));
        alamat.setText(db.getUserDetails().get("alamat"));
        no_hp.setText(db.getUserDetails().get("no_hp"));
        tgl_lahir.setText(db.getUserDetails().get("tgl_lahir"));
        bio.setText(db.getUserDetails().get("bio"));
        pDialog.setCancelable(false);
        tgl_lahir.setOnClickListener( this );
        save.setOnClickListener( this );
    }

    private void setInit(){
        ttl = (EditText)findViewById(R.id.tglLahir);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateAndTime = Calendar.getInstance();
        ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingTanggal();
            }
        });
        d = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day){
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, month);
                dateAndTime.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
    }
    private void updateLabel(){
        ttl.setText(
                sdf.format(dateAndTime.getTime()));
    }
    private void settingTanggal(){
        new DatePickerDialog(user_profile.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void sendData(){
        final String id_kaders = db.getUserDetails().get("id_kader");
        final String namakader = user.getText().toString().trim();
        final String namaUser= username.getText().toString().trim();
        final String emailUser = email.getText().toString().trim();
        final String alamatUser = alamat.getText().toString().trim();
        final String noTelpUser = no_hp.getText().toString().trim();
        final String tglLahirUser = tgl_lahir.getText().toString().trim();
        final String bioUser = bio.getText().toString().trim();

        String tag_string_req = "req_edit";

        pDialog.setMessage("Saving Changes ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_UPDATE, new Response.Listener<String>() {

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
                        String id_kader = jObj.getString("id_kader");

                        JSONObject user = jObj.getJSONObject("kaders");
                        String nama_admin= user.getString("nams_admin");
                        String username = user.getString("username");
                        String email = user.getString("email");
                        String alamat = user.getString("alamat");
                        String no_telp = user.getString("no_hp");
                        String tanggal_lahir = user.getString("tgl_lahir");
                        String bio = user.getString("bio");

                        // Inserting row in users table
                        db.updateUser(id_kader, nama_admin,username, email, alamat, no_telp, tanggal_lahir, bio);

                        Toast.makeText(getApplicationContext(), "Data changed successfully!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                user_profile.this,
                                main_utama.class);
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
                params.put("uid", id_kaders);
                params.put("nama_admin", namakader);
                params.put("username", namaUser);
                params.put("email", emailUser);
                params.put("alamat", alamatUser);
                params.put("no_telp", noTelpUser);
                params.put("tgl_lahir", tglLahirUser);
                params.put("bio", bioUser);


                return params;
            }

        };

// Adding request to request queue
        AppControl.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-12 14:45:19 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == tgl_lahir ) {
            // Handle clicks for tglLahir
            settingTanggal();
        }else if( v == save) {
            if(user.getText().toString().isEmpty()|| username.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                    alamat.getText().toString().isEmpty() || no_hp.getText().toString().isEmpty() ||
                    tgl_lahir.getText().toString().isEmpty() || bio.getText().toString().isEmpty() ){
                Toast.makeText(this, "Error harap isi semua opsi!", Toast.LENGTH_SHORT).show();
            }else{
                sendData();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                user_profile.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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



