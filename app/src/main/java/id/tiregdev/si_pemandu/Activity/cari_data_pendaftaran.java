package id.tiregdev.si_pemandu.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.tiregdev.si_pemandu.R;

public class cari_data_pendaftaran extends AppCompatActivity {

    Button next;
    Button tgl;
    private SimpleDateFormat sdf;
    private Calendar dateAndTime;
    DatePickerDialog.OnDateSetListener d;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_data_pendaftaran);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        next = findViewById(R.id.cari);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cari_data_pendaftaran.this, data_pendaftaran.class);
                i.putExtra("tgls",tgl.getText().toString().trim());
                startActivity(i);
            }
        });
    }


    private void setInit(){
        tgl = findViewById(R.id.tgl);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateAndTime = Calendar.getInstance();
        tgl.setOnClickListener(new View.OnClickListener() {
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
        tgl.setText(
                sdf.format(dateAndTime.getTime()));
    }
    private void settingTanggal(){
        new DatePickerDialog(cari_data_pendaftaran.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                cari_data_pendaftaran.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
