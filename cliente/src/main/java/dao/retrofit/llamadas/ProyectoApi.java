package dao.retrofit.llamadas;

import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ProyectoApi {

    @GET("proyectos")
    Single<List<Proyecto>> getAll();

    @GET("proyectos/{id}")
    Single<Proyecto> get(@Path("id") String id);

}
