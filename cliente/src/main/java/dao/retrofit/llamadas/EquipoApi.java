package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Equipo;
import retrofit2.http.*;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface EquipoApi {
    @GET("equipos")
    Single<List<Equipo>> getAll();

    @GET("equipos/{id}")
    Single<Equipo> get(@Path(Constantes.ID) String id);

}
