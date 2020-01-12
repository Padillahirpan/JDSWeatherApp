package com.example.weatherapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.network.NetworkClient;
import com.example.weatherapp.network.NetworkService;
import com.example.weatherapp.utils.LocalPref;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {
    public NetworkService networkService;
    public ProgressDialog progressDialog;
    protected LocalPref localPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNetworkClient();
        setupProgressDialog();

        localPref = LocalPref.getInstance(this);

        //Calligraphy
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void setupNetworkClient() {
        networkService = NetworkClient.getRetrofit().create(NetworkService.class);
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public void setupProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    public void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }
}
