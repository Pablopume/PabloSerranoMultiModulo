package dao.retrofit.llamadas;

import dao.common.Constantes;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface EmpleadoApi {

    @GET("empleados")
    Single<List<Empleado>> getAll();

    @GET("empleados/{id}")
    Single<Empleado> get(@Path(Constantes.ID) String id);

    @POST("empleados")
    Single<Empleado> addEmpleado(@Body Empleado empleado);

    @GET("empleados/equipo/{id}")
    Single<List<Empleado>> getAllByEquipo(@Path("id")String equipoId);
    @PUT("empleados")
    Single<Empleado> updateEmpleado(@Body Empleado empleado);

    @DELETE("empleados/{id}")
    Single<Response<Void>> deleteEmpleado(@Path(Constantes.ID) String id);

    @DELETE("empleados/equipo/{id}")
    Single<Response<Void>> deleteEmpleadoEquipo(@Path(Constantes.ID) String id);
}
