package dao.impl;

import com.google.gson.Gson;
import dao.ProyectoDao;
import dao.DaoGenerics;
import dao.retrofit.llamadas.ProyectoApi;

import domain.ErrorApp;
import domain.modelo.Proyecto;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class ProyectoDaoImpl extends DaoGenerics implements ProyectoDao {
    private final ProyectoApi proyectoApi;

    @Inject
    ProyectoDaoImpl(ProyectoApi proyectoApi, Gson gson) {
         super(gson);
         this.proyectoApi = proyectoApi;
    }


    @Override
    public Single<Either<ErrorApp, List<Proyecto>>> getAll() {
        return safeAPICall(proyectoApi.getAll());
    }

    @Override
    public Single<Either<ErrorApp, Proyecto>> get(UUID id) {
        return safeAPICall(proyectoApi.get(id));
    }
}
