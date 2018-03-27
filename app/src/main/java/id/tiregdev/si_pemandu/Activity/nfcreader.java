package id.tiregdev.si_pemandu.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.tiregdev.si_pemandu.R;

public class nfcreader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcreader);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        Button ok = findViewById(R.id.oke);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(nfcreader.this, data_anak.class);
                i.putExtra("reversedhex",reversed.getText().toString());
//                i.putExtra("nik",reversed.getText().toString());
                startActivity(i);
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
