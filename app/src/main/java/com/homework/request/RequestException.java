package com.homework.request;

import android.util.Log;

import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;


public class RequestException extends Exception {

    private static final long serialVersionUID = 6597650915042705283L;
    private int statusCode;
    private String responseBody;
    private String requestBody;

    public RequestException(String errorMessage) {
        super(errorMessage);
        this.statusCode = 400;
    }

    public RequestException(String errorMessage, int statusCode) {
        this(errorMessage);
        this.statusCode = statusCode;
    }

    public RequestException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = 400;
        this.statusCode = statusCode;
    }

    public RequestException(VolleyError error) {
        super(error);
        this.statusCode = 400;
        if(error == null) {
            Log.d("ServiceError", "VolleyError = null");
        } else if(error.networkResponse == null) {
            Log.d("ServiceError", "VolleyError.networkResponse = null_error = " + error);
        } else {
            this.statusCode = error.networkResponse.statusCode;

            try {
                this.responseBody = new String(error.networkResponse.data, "utf-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }

        }
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return this.statusCode + " : " + this.getCause() + " : " + this.getLocalizedMessage();
    }

}
