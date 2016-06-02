package com.loften.samples.Callback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loften.samples.R;

public class CallbackActivity extends AppCompatActivity implements CallbackListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);
        Waiter waiter = new Waiter();
        waiter.setCallbackListener(new CallbackListener() {
            @Override
            public void method() {

            }
        });
        waiter.use();
    }

    @Override
    public void method() {

    }
}
