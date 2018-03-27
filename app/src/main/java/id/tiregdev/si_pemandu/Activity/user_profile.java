package id.tiregdev.si_pemandu.Activity;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.tiregdev.si_pemandu.R;


public class user_profile extends AppCompatActivity {

    SimpleDateFormat sdf;
    Calendar dateAndTime;
    DatePickerDialog.OnDateSetListener d;
    EditText ttl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
}
