package dao.retrofit.llamadas;

import domain.modelo.Credentials;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {
    @GET("login")
    Single<Response<Void>> getLogin(@Query("user") String user, @Query("password") String password);

    @POST("login")
    Single<Credentials> addCredentials(@Body Credentials credentials);

}
