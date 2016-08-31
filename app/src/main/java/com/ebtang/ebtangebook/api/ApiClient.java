package com.ebtang.ebtangebook.api;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public final class ApiClient {

    public static String USER_AGENT = "android-phone/2.4.0 (http://loopj.com/android-async-http)";
    public static final String API_HOST = "http://test.ebtang.com/api/";//172.16.200.91
    private static final int TIME_OUT = 12;
    private static ApiService apiService ;

    public static ApiService getClient() {
        if (apiService == null) {

            // Define the interceptor, add authentication headers
            Interceptor USER_AGENT_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder().addHeader("User-Agent",USER_AGENT).build();
                    return chain.proceed(newRequest);
                }
            };
            /** Dangerous interceptor that rewrites the server's cache-control header. */
            Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "max-age=60")
                            .build();
                }
            };
            // Cache cache = new Cache(new File(Environment.getExternalStorageDirectory().getPath()+"/dongao/cache"),TIME_CACHE);
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(USER_AGENT_INTERCEPTOR)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(new LoggingInterceptor())
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    //.cache(cache)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_HOST)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okClient)
                    //.addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService ;
    }
}
