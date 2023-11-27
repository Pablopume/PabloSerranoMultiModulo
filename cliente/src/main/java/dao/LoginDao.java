package dao;

import domain.ErrorApp;
import domain.modelo.Credentials;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface LoginDao {
    Single<Either<ErrorApp, String>> getLogin(String user, String password);
    Single<Either<ErrorApp, Credentials>> addCredentials(Credentials credentials);
}
