package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.LoginDao;
import dao.retrofit.llamadas.LoginApi;
import domain.ErrorApp;
import domain.modelo.Credentials;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class LoginDaoImpl extends DaoGenerics implements LoginDao {
private final LoginApi loginApi;
    @Inject
    public LoginDaoImpl(LoginApi loginApi, Gson gson) {
        super(gson);
        this.loginApi = loginApi;
    }

    @Override
    public Single<Either<ErrorApp, String>> getLogin(String user, String password) {
        return safeSingleVoidApicall(loginApi.getLogin(user, password));
    }


    public Single<Either<ErrorApp, Credentials>> addCredentials(Credentials credentials) {
        return safeAPICall(loginApi.addCredentials(credentials));
    }
}
