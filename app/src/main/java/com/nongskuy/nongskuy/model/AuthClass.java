
package com.nongskuy.nongskuy.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.AuthData;

public class AuthClass {

    @SerializedName("user")
    @Expose
    private AuthData authData;
    @SerializedName("message")
    @Expose
    private String message;

    public AuthData getUser() {
        return authData;
    }

    public void setUser(AuthData authData) {
        this.authData = authData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
