package com.learn.dagger2application;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etPhone;
    private Button btnSave, btnGet;
    private SharedPrefComponent component;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        component = DaggerSharedPrefComponent.builder().
                sharedPrefModule(new SharedPrefModule(this)).build();
        component.inject(this);
    }

    private void initViews() {
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        btnSave = findViewById(R.id.btn_save);
        btnGet = findViewById(R.id.btn_get);

        btnSave.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", etName.getText().toString().trim());
                editor.putString("phone", etPhone.getText().toString().trim());
                editor.apply();
                break;

            case R.id.btn_get:
                etName.setText(sharedPreferences.getString("name", "default"));
                etPhone.setText(sharedPreferences.getString("phone","123"));
                break;
        }
    }
}
