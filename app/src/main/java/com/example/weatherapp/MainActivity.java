package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.base.BaseActivity;
import com.example.weatherapp.page.WeatherDailyActivity;
import com.example.weatherapp.utils.LocalPref;
import com.example.weatherapp.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.username_id)
    EditText etUsername;
    @BindView(R.id.postal_code_id)
    EditText etPostalCode;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        username = localPref.getString(LocalPref.Key.USERNAME, "");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (username.equals("")) {
            etUsername.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_search)
    public void searchData() {
        Log.d("ini dia","ini kode pos: "+etPostalCode.getText().toString());
        if (etUsername.getText().toString().length() > 0) {
            localPref.put(LocalPref.Key.USERNAME, etUsername.getText().toString());
        }

        if (etPostalCode.getText().toString().length() > 0) {
            Intent intent = new Intent(this, WeatherDailyActivity.class);
//            intent.putExtra("username", etUsername.getText().toString());
            intent.putExtra("postcode", etPostalCode.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.text_edittext_not_empty), Toast.LENGTH_SHORT).show();
        }

    }
}
