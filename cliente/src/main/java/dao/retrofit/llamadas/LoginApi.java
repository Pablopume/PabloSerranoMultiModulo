package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Credentials;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @GET(Constantes.LOGIN)
    Single<Response<Void>> getLogin(@Query(Constantes.USER) String user, @Query(Constantes.PASSWORD) String password);

    @POST(Constantes.LOGIN)
    Single<Credentials> addCredentials(@Body Credentials credentials);

}
