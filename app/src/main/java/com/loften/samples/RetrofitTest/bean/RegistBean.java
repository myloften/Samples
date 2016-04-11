package com.loften.samples.RetrofitTest.bean;

/**
 * Created by loften on 16/4/3.
 */
public class RegistBean {

    /**
     * message : 恭喜您，注册成功
     * code : 00000
     * oauth_token : b2878fe32d619e3d45b50d35f6e29747
     * oauth_token_secret : fa9f191a56ec3ff223c1555030890763
     * uid : 229
     */

    private String message;
    private String code;
    private String oauth_token;
    private String oauth_token_secret;
    private String uid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_token_secret() {
        return oauth_token_secret;
    }

    public void setOauth_token_secret(String oauth_token_secret) {
        this.oauth_token_secret = oauth_token_secret;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
