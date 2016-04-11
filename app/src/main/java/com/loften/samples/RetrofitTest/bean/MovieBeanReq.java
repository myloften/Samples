package com.loften.samples.RetrofitTest.bean;

import java.io.Serializable;

/**
 * Created by loften on 16/4/3.
 */
public class MovieBeanReq  implements Serializable {
    public int start;
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
