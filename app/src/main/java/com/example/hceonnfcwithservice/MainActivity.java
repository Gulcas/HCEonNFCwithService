package com.example.hceonnfcwithservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "HCEonNFC";
    private static Toast toast;
    private static boolean isToastVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkHCE(); //First check if HCE is supported on device
        isDefaultServiceForCategory(); //Check if this app is default service for category

    }

    /**
     * Checks if this app is default service for category and displays message on screen and in logcat
     */
    private void isDefaultServiceForCategory() {
        PackageManager packageManager = this.getPackageManager();
        boolean isDefault = packageManager.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
        if (!isDefault) {
            if (isToastVisible) {
                toast.cancel();
                Log.v(LOG_TAG, "MainActivity isDefaultServiceForCategory | CustomToast on screen, call cancel()");
            }
            customToast("This app is not default service for category");
        }
        Log.v(LOG_TAG, "MainActivity isDefaultServiceForCategory | " + isDefault);
    }

    /**
     * Checks if HCE is supported on device and displays message on screen and in logcat
     */
    private void checkHCE() {
        TextView textView = findViewById(R.id.hello_world);
        PackageManager packageManager = this.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
            // HCE is supported
            textView.setText(R.string.hce_is_supported);
            if (isToastVisible) {
                toast.cancel();
                Log.v(LOG_TAG, "MainActivity checkHCE | CustomToast on screen, call cancel()");
            }
            customToast("HCE is supported");
            Log.v(LOG_TAG, "MainActivity checkHCE | CustomToast: HCE is supported");
        } else {
            // HCE is not supported
            textView.setText(R.string.hce_is_not_supported);
            if (isToastVisible) {
                toast.cancel();
                Log.v(LOG_TAG, "MainActivity checkHCE | CustomToast on screen, call cancel()");
            }
            customToast("HCE is not supported");
            Log.v(LOG_TAG, "MainActivity checkHCE | CustomToast: HCE is not supported");
        }

    }

    /**
     * Custom toast, uses xml layout to display custom toast message on device screen
     */
    private void customToast(String txt) {
        toast = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
        TextView tv = layout.findViewById(R.id.txtvw);
        tv.setText(txt);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        isToastVisible = true;
    }
}