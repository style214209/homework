package com.homework.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.homework.utils.NetworkUtil;

import java.io.IOException;
import java.util.Map;


public class VolleyRequestRunner implements RequestRunner  {

    private static RequestQueue mRequestQueue;

    public VolleyRequestRunner(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getVolleyRequestQueue() {
        return mRequestQueue;
    }


    @Override
    public void run(Request request) {
        this.run(request, null);
    }

    @Override
    public void run(Request request, Object tag) {
        com.android.volley.Request volleyRequest = this.generateRequest(request);
        volleyRequest.setShouldCache(false);
        volleyRequest.setTag(tag);
        mRequestQueue.add(volleyRequest);
    }

    @Override
    public void cancelRequest(Object tag) {

    }

    private com.android.volley.Request<?> generateRequest(Request request) {
        switch(request.getMethod()) {
            case GET:
                return this.createGetRequest(request);
            case POST:
                return this.createPostRequest(request);
            case PUT:
                return this.createPutRequest(request);
            case DELETE:
            default:
                throw new IllegalStateException("Known HTTP Method");
        }
    }

    private com.android.volley.Request<?> createGetRequest(final Request request) {
        StringRequest stringRequest = new StringRequest(request.getUrl(), this.generateSuccessListener(request), this.generateFailListener(request)) {
            public Map<String, String> getHeaders() {
                return request.getHeaders();
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return Response.success(
                        VolleyRequestRunner.this.parseNetworkResponseResultCustom(request, response),
                        HttpHeaderParser.parseCacheHeaders(response)
                );
            }

            public String getBodyContentType() {
                return request.getContentType();
            }
        };
        this.initRetryPolicy(stringRequest, request);
        return stringRequest;
    }

    private com.android.volley.Request<?> createPostRequest(final Request request) {
        final StringRequest stringRequest = new StringRequest(1, request.getUrl(), this.generateSuccessListener(request), this.generateFailListener(request)) {
            public byte[] getBody() throws AuthFailureError {
                return request.getBody() == null?new byte[0]:request.getBody().getBytes();
            }

            public Map<String, String> getHeaders() {
                return request.getHeaders();
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return Response.success(VolleyRequestRunner.this.parseNetworkResponseResultCustom(request, response), HttpHeaderParser.parseCacheHeaders(response));
            }

            public String getBodyContentType() {
                return request.getContentType();
            }
        };
        this.initRetryPolicy(stringRequest, request);
        return stringRequest;
    }

    private com.android.volley.Request<?> createPutRequest(final Request request) {
        StringRequest stringRequest = new StringRequest(2, request.getUrl(), this.generateSuccessListener(request), this.generateFailListener(request)) {
            public byte[] getBody() throws AuthFailureError {
                return request.getBody() == null?new byte[0]:request.getBody().getBytes();
            }

            public Map<String, String> getHeaders() {
                return request.getHeaders();
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return Response.success(VolleyRequestRunner.this.parseNetworkResponseResultCustom(request, response), HttpHeaderParser.parseCacheHeaders(response));
            }

            public String getBodyContentType() {
                return request.getContentType();
            }
        };
        this.initRetryPolicy(stringRequest, request);
        return stringRequest;
    }

    private Response.Listener<String> generateSuccessListener(final Request request) {
        return new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                request.onRequestSuccess(response.toString());
            }
        };
    }

    private Response.ErrorListener generateFailListener(final Request request) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                RequestException exception = new RequestException(error);
                exception.setRequestBody(request.getBody());
                request.onRequestFail(exception);
            }
        };
    }

    private String parseNetworkResponseResultCustom(Request request, NetworkResponse response) {
        String result = null;

        try {
            result = NetworkUtil.parseNetworkResponse(request, response.data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void initRetryPolicy(StringRequest stringRequest, final Request request) {
        stringRequest.setRetryPolicy(new RetryPolicy() {
            public void retry(VolleyError error) throws VolleyError {
                throw error;
            }

            public int getCurrentTimeout() {
                return request.getTimeoutMs();
            }

            public int getCurrentRetryCount() {
                return 2;
            }
        });
    }
}
