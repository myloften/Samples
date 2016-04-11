package com.loften.samples.RetrofitTest.bean;

import java.io.Serializable;

/**
 * Created by loften on 16/4/3.
 */
public class RegistBeanReq  implements Serializable {
    private String email;
    private String passwd;
    private String repasswd;
    private String head;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRepasswd() {
        return repasswd;
    }

    public void setRepasswd(String repasswd) {
        this.repasswd = repasswd;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
