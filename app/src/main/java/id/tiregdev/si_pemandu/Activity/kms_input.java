package id.tiregdev.si_pemandu.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.tiregdev.si_pemandu.R;

public class kms_input extends AppCompatActivity implements View.OnClickListener {


    private Button tgl_penimbangan;
    private SimpleDateFormat sdf;
    private ProgressDialog pDialog;
    private Calendar dateAndTime;
    private Button save;

    private void settingTanggal() {
        dateAndTime = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener d =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month,
                                          int day) {
                        dateAndTime.set(Calendar.YEAR, year);
                        dateAndTime.set(Calendar.MONTH, month);
                        dateAndTime.set(Calendar.DAY_OF_MONTH, day);

                        tgl_penimbangan.setText(sdf.format(dateAndTime.getTime()));
                    }
                };

        new DatePickerDialog(kms_input.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kms_input);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        setViews();

    }


    private void findViews() {
        tgl_penimbangan = findViewById(R.id.tanggal);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        pDialog = new ProgressDialog(this);
        save.setOnClickListener(this);
    }

    private void setViews() {
        tgl_penimbangan.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                kms_input.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        if (v == tgl_penimbangan) {
            settingTanggal();
        }
    }
        private void showDialog () {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog () {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

}
