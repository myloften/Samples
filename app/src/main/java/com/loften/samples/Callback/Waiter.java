package com.loften.samples.Callback;

/**
 * Created by loften on 16/5/2.
 * 回调方法的调用类
 */
public class Waiter {
    private CallbackListener callbackListener;

    public void setCallbackListener(CallbackListener callbackListener){
        this.callbackListener = callbackListener;
    }

    public void use(){
        callbackListener.method();
    }
}
