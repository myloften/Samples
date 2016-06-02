package com.loften.samples.CombinationImageView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loften.samples.R;

import java.util.Vector;

public class CombinationImageActivity extends AppCompatActivity {

    CombinationImageView civ;
    Vector<String> v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_image);

        civ = (CombinationImageView)findViewById(R.id.civ);
        v = new Vector<String>();
        v.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        v.add("http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        v.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        v.add("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        try {
            civ.loadImg(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
