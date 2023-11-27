package usecases.login;

import dao.LoginDao;
import domain.ErrorApp;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class LoginUseCase {
    private final LoginDao loginDao;
    @Inject
    public LoginUseCase(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    public Single<Either<ErrorApp, String>> execute(String user, String password) {
        return loginDao.getLogin(user, password);
    }
}
