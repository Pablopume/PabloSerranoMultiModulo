package dao;


import domain.ErrorApp;
import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface ProyectoDao {
    Single<Either<ErrorApp, List<Proyecto>>> getAll();

    Single<Either<ErrorApp, Proyecto>> get(UUID id);


}
