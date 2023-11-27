package dao.retrofit;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import dao.common.Constantes;
import dao.retrofit.llamadas.LoginApi;
import dao.retrofit.llamadas.ProyectoApi;
import dao.retrofit.llamadas.EquipoApi;
import dao.retrofit.llamadas.EmpleadoApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(Gson gson, OkHttpClient clientOK) {
        clientOK.newBuilder()
                .connectionPool(new okhttp3.ConnectionPool(1, 2, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/server-1.0-SNAPSHOT/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }

    @Produces
    @Singleton
    public OkHttpClient getOkHttpClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        return new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                //.addInterceptor(new AuthorizationInterceptor(cache))
                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                // necesario para la sesion
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();
    }

    @Produces
    public EquipoApi getEquipoApi(Retrofit retrofit) {
        return retrofit.create(EquipoApi.class);
    }

    @Produces
    public ProyectoApi getProyectoApi(Retrofit retrofit) {
        return retrofit.create(ProyectoApi.class);
    }

    @Produces
    public LoginApi getLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

    @Produces
    public EmpleadoApi getEmpleadoApi(Retrofit retrofit) {
        return retrofit.create(EmpleadoApi.class);
    }
}
