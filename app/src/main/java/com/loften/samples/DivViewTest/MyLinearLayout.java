package com.loften.samples.DivViewTest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loften.samples.R;

/**
 * Created by loften on 16/2/24.
 * 自定义LinearLayout
 */
public class MyLinearLayout extends LinearLayout{

    private String Text = "";

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        int resouceId = -1;
//        TextView tv = new TextView(context);
//        EditText et = new EditText(context);
//        this.setOrientation(LinearLayout.VERTICAL);
//        resouceId = attrs.getAttributeResourceValue(null,"Text",0);
//        if(resouceId > 0){
//            Text = context.getResources().getText(resouceId).toString();
//        }else{
//            Text = "";
//        }
//        tv.setText(Text);
//
//        addView(tv);
//        addView(et);

        int resouceId = -1;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
        TextView tv = new TextView(context);
        EditText et = new EditText(context);
        int N = typedArray.getIndexCount();
        for(int i = 0; i < N; i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.MyLinearLayout_Oriental:
                    resouceId = typedArray.getInt(R.styleable.MyLinearLayout_Oriental,0);
                    this.setOrientation(resouceId == 1?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);
                    break;
                case R.styleable.MyLinearLayout_Text:
                    resouceId = typedArray.getResourceId(R.styleable.MyLinearLayout_Text,0);
                    tv.setText(resouceId>0?typedArray.getResources().getText(resouceId):
                            typedArray.getString(R.styleable.MyLinearLayout_Text));
                    break;
            }
        }
        addView(tv);
        addView(et);
        typedArray.recycle();
    }

}
