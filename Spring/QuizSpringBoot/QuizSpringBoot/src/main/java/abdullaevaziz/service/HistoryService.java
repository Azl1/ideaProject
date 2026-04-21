package abdullaevaziz.service;

import abdullaevaziz.model.History;
import abdullaevaziz.model.Quiz;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface HistoryService {

    void addHistory(Authentication authentication, History history);
    List<History> getHistoryList(long idUser, Authentication authentication);
    History getAnswer(Authentication authentication, long resultId, String answer);
    List<History> getHistoryQuiz(Authentication authentication, long idQuiz);
}
