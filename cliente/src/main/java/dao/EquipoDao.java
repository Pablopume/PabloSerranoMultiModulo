package dao;

import domain.ErrorApp;
import domain.modelo.Equipo;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface EquipoDao {
    Single<Either<ErrorApp, List<Equipo>>> getAll();
    Single<Either<ErrorApp, Equipo>> get(UUID id);
}
