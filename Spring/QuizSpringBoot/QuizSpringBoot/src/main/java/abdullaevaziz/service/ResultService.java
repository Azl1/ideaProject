package abdullaevaziz.service;

import abdullaevaziz.model.Result;
import abdullaevaziz.model.User;

import java.util.List;
import java.util.Optional;

public interface ResultService {

    Result get (long id);
    List<Result> getQuiz(long idQuiz);
    void add(Result result);

}
