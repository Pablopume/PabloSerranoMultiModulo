package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.UUID;

public interface ProyectoApi {

    @GET(Constantes.PROYECTOS)
    Single<List<Proyecto>> getAll();

    @GET(Constantes.PROYECTOS_ID)
    Single<Proyecto> get(@Path(Constantes.ID) UUID id);

}
