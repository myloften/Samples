package com.loften.samples.VerificationCode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loften.samples.R;

public class VerificationActivity extends AppCompatActivity {

    private CheckView cv;
    private TextView tv;
    //验证码
    private int[] checkNum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        cv = (CheckView)findViewById(R.id.cv);
        tv = (TextView)findViewById(R.id.tv);
        initCheckNum();

    }

    private void initCheckNum(){
        checkNum = CheckUtil.getCheckNum();
        cv.setCheckNum(checkNum);
        cv.invaliChenkNum();
        tv.setText(checkNum[0]+checkNum[1]+checkNum[2]+checkNum[3]+"");
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCheckNum();
            }
        });
    }
}
