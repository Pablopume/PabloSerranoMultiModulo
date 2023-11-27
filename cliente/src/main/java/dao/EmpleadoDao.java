package dao;


import domain.ErrorApp;
import domain.modelo.Empleado;
import domain.modelo.Equipo;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface EmpleadoDao {
    Single<Either<ErrorApp, List<Empleado>>> getAll();
    Single<Either<ErrorApp, Empleado>> get(String id);
    Single<Either<ErrorApp, Empleado>> add(Empleado empleado);
    Single<Either<ErrorApp, Empleado>> update(Empleado empleado);
    Single<Either<ErrorApp, String>> delete(String id);
    Single<Either<ErrorApp, List<Empleado>>> getAll(String equipoId);
Single<Either<ErrorApp, String>> deleteByEquipo(String equipoId);
}
