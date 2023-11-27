package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.EquipoDao;
import dao.retrofit.llamadas.EquipoApi;
import domain.ErrorApp;
import domain.modelo.Equipo;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class EquipoDaoImpl extends DaoGenerics implements EquipoDao {
    private final EquipoApi equipoApi;

    @Inject
    public EquipoDaoImpl(EquipoApi equipoApi, Gson gson) {
        super(gson);
        this.equipoApi = equipoApi;
    }


    @Override
    public Single<Either<ErrorApp, List<Equipo>>> getAll() {
        return safeAPICall(equipoApi.getAll());
    }

    @Override
    public Single<Either<ErrorApp, Equipo>> get(UUID id) {
        return safeAPICall(equipoApi.get(id));
    }
}
