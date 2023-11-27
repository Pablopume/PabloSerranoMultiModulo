package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.EmpleadoDao;
import dao.retrofit.llamadas.EmpleadoApi;
import domain.ErrorApp;
import domain.modelo.Empleado;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class EmpleadoDaoImpl extends DaoGenerics implements EmpleadoDao {
    private final EmpleadoApi empleadoApi;

    @Inject
    EmpleadoDaoImpl(EmpleadoApi empleadoApi, Gson gson) {
        super(gson);
        this.empleadoApi = empleadoApi;
    }


    @Override
    public Single<Either<ErrorApp, List<Empleado>>> getAll() {
        return safeAPICall(empleadoApi.getAll());
    }

    @Override
    public Single<Either<ErrorApp, Empleado>> get(String id) {
        return safeAPICall(empleadoApi.get(id));
    }

    @Override
    public Single<Either<ErrorApp, Empleado>> add(Empleado empleado) {
        return safeAPICall(empleadoApi.addEmpleado(empleado));
    }

    @Override
    public Single<Either<ErrorApp, Empleado>> update(Empleado empleado) {
        return safeAPICall(empleadoApi.updateEmpleado(empleado));
    }

    @Override
    public Single<Either<ErrorApp, String>> delete(UUID id) {
       return safeSingleVoidApicall(empleadoApi.deleteEmpleado(id));
    }

    @Override
    public Single<Either<ErrorApp, List<Empleado>>> getAll(UUID equipoId) {
        return safeAPICall(empleadoApi.getAllByEquipo(equipoId));
    }

    @Override
    public Single<Either<ErrorApp, String>> deleteByEquipo(UUID equipoId) {
        return safeSingleVoidApicall(empleadoApi.deleteEmpleadoEquipo(equipoId));
    }
}
