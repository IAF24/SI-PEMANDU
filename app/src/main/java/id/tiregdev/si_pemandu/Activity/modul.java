package id.tiregdev.si_pemandu.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import id.tiregdev.si_pemandu.R;

public class modul extends AppCompatActivity {

    LinearLayout kms, vita, imunisasi, sdidtk, gizi, kbbl, kesAnak, kesimpulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findID();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                modul.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void findID(){
        kms = findViewById(R.id.kms);
        vita = findViewById(R.id.vita);
        imunisasi = findViewById(R.id.imunisasi);
        sdidtk = findViewById(R.id.sdidtk);
        gizi = findViewById(R.id.gizi);
        kbbl = findViewById(R.id.kbbl);
        kesAnak = findViewById(R.id.kesehatanAnak);
        kesimpulan = findViewById(R.id.kesimpulan);

        kms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, kms_history.class);
                startActivity(i);
            }
        });

        vita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, vita_history.class);
                startActivity(i);
            }
        });

        imunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, imunisasi_history.class);
                startActivity(i);
            }
        });

        sdidtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, sdidtk_history.class);
                startActivity(i);
            }
        });

        kbbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, kbbl_history.class);
                startActivity(i);
            }
        });

        gizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, gizi_history.class);
                startActivity(i);
            }
        });

        kesAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, data_kesehatan_anak.class);
                startActivity(i);
            }
        });

        kesimpulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(modul.this, kesimpulan.class);
                startActivity(i);
            }
        });
    }
}
