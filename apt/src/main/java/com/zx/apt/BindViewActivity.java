package com.zx.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zx.annotations.BindView;

import org.w3c.dom.Text;

public class BindViewActivity extends AppCompatActivity {

    @BindView
    TextView textView;

    @BindView
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_view);
    }
}