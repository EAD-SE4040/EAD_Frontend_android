package com.example.ead_frontend_android.api;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyNetworkInspector implements Interceptor {
    private OkHttpClient client;

    public MyNetworkInspector(OkHttpClient client) {
        this.client = client;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request= chain.request();
        Request newRequest=request.newBuilder()
                .header("Content-Type","application/json")
                .build();

        // Use the custom OkHttpClient to make the network call
        OkHttpClient customClient = client.newBuilder().build();
        return customClient.newCall(newRequest).execute();
    }


}
