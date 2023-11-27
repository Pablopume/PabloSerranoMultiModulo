package dao;

import com.google.gson.Gson;
import dao.common.Constantes;
import domain.ErrorApp;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import io.vavr.control.Either;
import java.io.IOException;
import io.reactivex.rxjava3.core.Single;
import java.util.Objects;

@Log4j2
public abstract class DaoGenerics {
    public static final String APPLICATION_JSON = "application/json";
    private final Gson gson;

    @Inject
    public DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<ErrorApp, T>> safeAPICall(Single<T> apiCall) {
        return apiCall
                .map(Either::<ErrorApp, T>right)
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof HttpException) {
                        // Si es una excepción HTTP, intenta obtener el mensaje del cuerpo de la respuesta
                        HttpException httpException = (HttpException) throwable;
                        String errorMessage = getErrorMessage(httpException.response());
                        return Single.just(Either.left(new ErrorApp(errorMessage)));
                    } else {
                        // Para otras excepciones, utiliza el mensaje de la excepción original
                        return Single.just(Either.left(new ErrorApp(throwable.getMessage())));
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private String getErrorMessage(Response<?> response) {
        // Lógica para extraer el mensaje de error del cuerpo de la respuesta
        // Puedes adaptar esto según la estructura de tu respuesta HTTP
        if (response.errorBody() != null) {
            try {
                return response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Error desconocido";
    }



    public Single<Either<ErrorApp, String>> safeSingleVoidApicall(Single<Response<Void>> call) {
        return call.map(response -> {
                    Either<ErrorApp,String> retorno = Either.right("OK");
                    if (!response.isSuccessful()) {
                        retorno = Either.left(new ErrorApp(response.message()));
                    }
                    return retorno;
                })
                .subscribeOn(Schedulers.io());
    }




}
