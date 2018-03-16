package id.tiregdev.si_pemandu.Activity;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import id.tiregdev.si_pemandu.Fragment.cari_data_anak;
import id.tiregdev.si_pemandu.Fragment.home;
import id.tiregdev.si_pemandu.Fragment.laporan;
import id.tiregdev.si_pemandu.Fragment.user;
import id.tiregdev.si_pemandu.R;

public class main_utama extends AppCompatActivity {

    private boolean backPressedToExitOnce = false;
    BottomBar bottomBar;
    private Toast toast = null;
    int abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_utama);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int item) {
                Fragment selectedFragment = null;
                switch (item) {
                    case R.id.tab_home:
                        abc = 0;
                        selectedFragment = home.newInstance();
                        break;
                    case R.id.tab_data:
                        abc = 1;
                        selectedFragment = cari_data_anak.newInstance();
                        break;
                    case R.id.tab_report:
                        abc = 2;
                        selectedFragment = laporan.newInstance();
                        break;
                    case R.id.tab_profile:
                        abc = 3;
                        selectedFragment = user.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (bottomBar.getCurrentTabPosition() > abc) {
                    transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right);
                } else if (bottomBar.getCurrentTabPosition() < abc) {
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
                }
                transaction.replace(R.id.frameLayout, selectedFragment);
                transaction.commit();

            }
        });
        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, home.newInstance());
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        BottomBar bottomBar = findViewById(R.id.bottomBar);
        int selectedItemId = bottomBar.getCurrentTabId();
        if (R.id.tab_home != selectedItemId) {
            bottomBar.selectTabAtPosition(0, true);
        } else if (backPressedToExitOnce) {
            finishAffinity();
        } else {
            this.backPressedToExitOnce = true;
            showToast("Press again to exit");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressedToExitOnce = false;
                }
            }, 2000);
        }
    }

    private void showToast(String message) {
        if (this.toast == null) {
            // Create toast if found null, it would he the case of first call only
            this.toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        } else if (this.toast.getView() == null) {
            // Toast not showing, so create new one
            this.toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        } else {
            // Updating toast message is showing
            this.toast.setText(message);
        }
        // Showing toast finally
        this.toast.show();
    }

    private void killToast() {
        if (this.toast != null) {
            this.toast.cancel();
        }
    }

    @Override
    protected void onPause() {
        killToast();
        super.onPause();
    }
}
