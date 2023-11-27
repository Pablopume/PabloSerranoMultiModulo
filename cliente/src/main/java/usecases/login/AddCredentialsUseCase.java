package usecases.login;

import dao.LoginDao;
import domain.ErrorApp;
import domain.modelo.Credentials;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class AddCredentialsUseCase {
    private final LoginDao loginDao;
    @Inject
    public AddCredentialsUseCase(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

public Single<Either<ErrorApp, Credentials>> execute(Credentials credentials) {
        return loginDao.addCredentials(credentials);
    }
}
