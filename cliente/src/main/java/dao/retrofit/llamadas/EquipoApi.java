package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Equipo;
import retrofit2.http.*;
import io.reactivex.rxjava3.core.Single;

import java.util.List;
import java.util.UUID;

public interface EquipoApi {

    @GET(Constantes.EQUIPOS)
    Single<List<Equipo>> getAll();

    @GET(Constantes.EQUIPOS_ID)
    Single<Equipo> get(@Path(Constantes.ID) UUID id);

}
