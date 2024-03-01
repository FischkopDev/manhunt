package de.home_skrobanek.manhunt.backend.manager.http;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import de.home_skrobanek.manhunt.backend.manager.http.dao.Message;
import de.home_skrobanek.manhunt.backend.manager.http.dao.Player;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPRequest {

    private static HTTPRequest singleton;
    private static final String server = "http://10.0.2.2:8080/";//10.0.2.2

    private Retrofit retrofit;
    private ApiService apiService;

    private HTTPRequest() {
        // Create a new object from HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add Interceptor to HttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(server)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static HTTPRequest getInstance() {
        if (singleton == null) {
            singleton = new HTTPRequest();
        }
        return singleton;
    }

    public void login(Context context) {
        Player player = new Player("test", "test", "testes");
        Call<Message> call = apiService.loginUser(player);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Message responseBody = response.body();
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(context, "Failed " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
