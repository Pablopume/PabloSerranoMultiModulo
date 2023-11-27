package dao;

import com.google.gson.Gson;

import dao.common.Constantes;
import domain.ErrorApp;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import retrofit2.HttpException;
import retrofit2.Response;
import io.vavr.control.Either;
import java.io.IOException;
import java.util.Objects;

import io.reactivex.rxjava3.core.Single;


@Log4j2
public abstract class DaoGenerics {

    private final Gson gson;

    @Inject
    protected DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<ErrorApp, T>> safeAPICall(Single<T> apiCall) {
        return apiCall
                .map(Either::<ErrorApp, T>right)
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof HttpException httpException) {
                        String errorMessage = getErrorMessage(Objects.requireNonNull(httpException.response()));
                        return Single.just(Either.left(new ErrorApp(errorMessage)));
                    } else {
                        return Single.just(Either.left(new ErrorApp(throwable.getMessage())));
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private String getErrorMessage(Response<?> response) {
        if (response.errorBody() != null) {
            try {
                return response.errorBody().string();
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
        return Constantes.ERROR_DESCONOCIDO;
    }



    public Single<Either<ErrorApp, String>> safeSingleVoidApicall(Single<Response<Void>> call) {
        return call.map(response -> {
                    Either<ErrorApp,String> retorno = Either.right(Constantes.OK);
                    if (!response.isSuccessful()) {
                        retorno = Either.left(new ErrorApp(response.message()));
                    }
                    return retorno;
                })
                .subscribeOn(Schedulers.io());
    }




}
