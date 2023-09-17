package com.example.connectsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.resultTextView);
        new GetDataTask(this).execute();
    }

}