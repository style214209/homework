package com.homework.request;

import java.util.Map;

/**
 * Created by Leo on 2017/8/26.
 */

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

    void request(Object tag);

    interface RequestListener<E> {

        void onRequestSuccess(E response);

        void onRequestFail(RequestException exception);
    }

}
