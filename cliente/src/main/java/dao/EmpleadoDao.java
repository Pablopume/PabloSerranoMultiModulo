package dao;


import domain.ErrorApp;
import domain.modelo.Empleado;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface EmpleadoDao {
    Single<Either<ErrorApp, List<Empleado>>> getAll();
    Single<Either<ErrorApp, Empleado>> get(String id);
    Single<Either<ErrorApp, Empleado>> add(Empleado empleado);
    Single<Either<ErrorApp, Empleado>> update(Empleado empleado);
    Single<Either<ErrorApp, String>> delete(UUID id);
    Single<Either<ErrorApp, List<Empleado>>> getAll(UUID equipoId);
Single<Either<ErrorApp, String>> deleteByEquipo(UUID equipoId);
}
