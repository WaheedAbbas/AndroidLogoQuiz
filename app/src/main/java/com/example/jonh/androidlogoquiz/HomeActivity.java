package com.example.jonh.androidlogoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public Button home_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_btn = findViewById(R.id.home_btn);

        home_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent= null;
        switch (v.getId())
        {
            case R.id.home_btn:
                intent = new Intent(this,MainActivity.class);
                break;
        }
        if (intent != null)
        {
            startActivity(intent);
        }
    }
}
