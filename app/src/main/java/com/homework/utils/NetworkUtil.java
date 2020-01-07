package com.homework.utils;


import com.homework.request.Request;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class NetworkUtil {
    public static final String HEADER_VALUE_ACCEPT_ENCODING = "gzip";
    public static final String HEADER_KEY_ACCEPT_ENCODING = "Accept-Encoding";

    public NetworkUtil() {
    }

    public static String parseNetworkResponse(Request request, byte[] responseData) throws IOException {
        return parseNetworkResponse(request, new ByteArrayInputStream(responseData));
    }

    public static String parseNetworkResponse(Request request, InputStream responseInputStream) throws IOException {
        boolean isServiceEncodeGzip = isServiceEncodeGzip(request.getHeaders());
        return isServiceEncodeGzip?parseResponseByGzip(responseInputStream):parseResponse(responseInputStream);
    }

    private static String parseResponseByGzip(InputStream responseInputStream) throws IOException {
        GZIPInputStream is = null;
        InputStreamReader reader = null;
        BufferedReader in = null;
        byte[] response = parseResponseByteArray(responseInputStream);

        try {
            String read;
            try {
                is = new GZIPInputStream(new ByteArrayInputStream(response));
                String e = "";
                reader = new InputStreamReader(is);

                String e1 = e;
                return e1;
            } catch (IOException var18) {
                if(var18 != null && var18.getMessage() != null && var18.getMessage().indexOf("unknown format") >= 0) {
                    read = new String(response);
                    return read;
                } else {
                    read = "";
                    return read;
                }
            }
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }

                if(in != null) {
                    in.close();
                }

                if(is != null) {
                    is.close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }
    }

    private static byte[] parseResponseByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[16384];

        int nRead;
        while((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }

    private static String parseResponse(InputStream inputStream) {
        StringBuffer responseBuffer = new StringBuffer();

        try {
            Charset e = Charset.forName("UTF8");
            InputStreamReader stream = new InputStreamReader(inputStream, e);
            BufferedReader reader = new BufferedReader(stream);
            String read;

            while((read = reader.readLine()) != null) {
                responseBuffer.append(read).append("\n");
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return responseBuffer.toString();
    }

    private static boolean isServiceEncodeGzip(Map<String, String> headers) {
        return headers.containsKey(HEADER_KEY_ACCEPT_ENCODING) && headers.get(HEADER_KEY_ACCEPT_ENCODING) != null && (headers.get(HEADER_KEY_ACCEPT_ENCODING)).equalsIgnoreCase(HEADER_VALUE_ACCEPT_ENCODING);
    }
}
