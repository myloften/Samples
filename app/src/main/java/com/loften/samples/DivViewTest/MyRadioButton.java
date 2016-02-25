package com.loften.samples.DivViewTest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.loften.samples.R;

/**
 * Created by loften on 16/2/24.
 * 自定义RadioButton
 */
public class MyRadioButton extends RadioButton implements CompoundButton.OnCheckedChangeListener{

    private String mValue;

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        this.mValue = a.getString(R.styleable.MyRadioButton_value);
        invalidate();
        a.recycle();
        setOnCheckedChangeListener(this);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
