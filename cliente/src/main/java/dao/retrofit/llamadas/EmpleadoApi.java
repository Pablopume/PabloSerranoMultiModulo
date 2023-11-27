package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;
import java.util.UUID;

public interface EmpleadoApi {

    @GET(Constantes.EMPLEADOS)
    Single<List<Empleado>> getAll();

    @GET(Constantes.EMPLEADOS_ID)
    Single<Empleado> get(@Path(Constantes.ID) String id);

    @POST(Constantes.EMPLEADOS)
    Single<Empleado> addEmpleado(@Body Empleado empleado);

    @GET(Constantes.EMPLEADOS_EQUIPO_ID)
    Single<List<Empleado>> getAllByEquipo(@Path(Constantes.ID)UUID equipoId);
    @PUT(Constantes.EMPLEADOS)
    Single<Empleado> updateEmpleado(@Body Empleado empleado);

    @DELETE(Constantes.EMPLEADOS_ID)
    Single<Response<Void>> deleteEmpleado(@Path(Constantes.ID) UUID id);

    @DELETE(Constantes.EMPLEADOS_EQUIPO_ID)
    Single<Response<Void>> deleteEmpleadoEquipo(@Path(Constantes.ID) UUID id);

    @POST(Constantes.ALL_SELECTED)
    Single<Response<Void>> deleteEmpleados(@Body List<UUID> listaId);

}
