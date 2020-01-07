package com.homework.request;

import java.util.Map;

public interface Request {

    enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    Request.Method getMethod();

    int getTimeoutMs();

    String getUrl();

    Map<String, String> getHeaders();

    String getBody();

    String getContentType();

    void onRequestSuccess(String response);

    void onRequestFail(RequestException exception);

    interface RequestListener<E> {

        void onRequestSuccess(E response);

        void onRequestFail(RequestException exception);
    }

}
