package xyz.eveneer.sadmansakib.kuul;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.eveneer.sadmansakib.kuul.Data.KuulServerClient;

import static xyz.eveneer.sadmansakib.kuul.Constants.oAuth.BASE_URL;

public class Kuul extends Application {
    private static KuulServerClient client;

    public Kuul(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();

        client = retrofit.create(KuulServerClient.class);
    }
    public static KuulServerClient getClient() {
        return client;
    }
}
