package com.miaoxing.nettank.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by WangYi
 *
 * @Date : 2018/8/27
 * @Desc :
 */
public class OkHttpClientProvider {
    private static OkHttpClient sClient;

    public static OkHttpClient createOkHttpClient() {
        if (sClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier());
            //debug情況下
            //builder.addInterceptor(new LoggerInterceptor());
            sClient = builder.build();
        }
        return sClient;
    }
}
