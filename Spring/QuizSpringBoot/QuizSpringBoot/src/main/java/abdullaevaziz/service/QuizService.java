package abdullaevaziz.service;


import abdullaevaziz.model.Quiz;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface QuizService {

    Quiz get(Authentication authentication, int numberOfQuestions, int category, String difficulty);

    List<Quiz> getListQuiz(long id, Authentication authentication);

    Quiz add(Authentication authentication, Quiz quiz);
}
