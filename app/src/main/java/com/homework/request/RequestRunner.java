package com.homework.request;

import java.util.Map;

import javax.net.SocketFactory;

public interface RequestRunner {
    void run(Request request);

    void run(Request request, Object tag);

    void cancelRequest(Object tag);
}
