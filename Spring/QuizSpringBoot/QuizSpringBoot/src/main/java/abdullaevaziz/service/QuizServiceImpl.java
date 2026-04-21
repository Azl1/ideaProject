package abdullaevaziz.service;

import abdullaevaziz.model.Quiz;
import abdullaevaziz.model.Result;
import abdullaevaziz.model.User;
import abdullaevaziz.model.UserType;
import abdullaevaziz.repository.QuizRepository;
import abdullaevaziz.repository.UserRepository;
import abdullaevaziz.securety.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private ResultService resultService;

    private UserRepository userRepository;


    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${users.url}")
    private String url;


    //https://opentdb.com/api.php?amount=10&category=21&difficulty=easy
    @Override
    public Quiz get(Authentication authentication, int amount, int category, String difficulty) {
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User getUser = this.userRepository.findById(autUserId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        Quiz quiz = this.restTemplate.exchange(this.url + "?amount={amount}&category={category}&difficulty={difficulty}", HttpMethod.GET, null,
                new ParameterizedTypeReference<Quiz>() {
                }, amount, category, difficulty).getBody();

        quiz.setUser(getUser);

        //TODO занести квиз в базу
        this.quizRepository.save(quiz);

        for (Result result : quiz.getResults()) {
            result.setQuiz(quiz);

            result.setQuestion(StringEscapeUtils.unescapeHtml4(result.getQuestion()));
            result.setCorrectAnswer(StringEscapeUtils.unescapeHtml4(result.getCorrectAnswer()));
            List<String> incorrectAnswers = result.getIncorrectAnswers();
            for (int i = 0; i < incorrectAnswers.size(); i++) {
                incorrectAnswers.set(i, StringEscapeUtils.unescapeHtml4(incorrectAnswers.get(i)));
            }
            result.setIncorrectAnswers(incorrectAnswers);

            //TODO через результ сервис надо сделать добалвение результа в базу
            this.resultService.add(result);
        }
        return quiz;
    }

    public List<Quiz> getListQuiz(long idUser, Authentication authentication) {
        long idOwner = ((JwtUser) authentication.getPrincipal()).getId();
        User owner = this.userRepository.findById(idOwner).
                orElseThrow(() -> new IllegalArgumentException("Auth user not found"));

        if (owner.getUserType() != UserType.ADMIN && idUser != idOwner) {
            throw new IllegalArgumentException("Нет доступа!");
        }
        return this.quizRepository.findAllByUserId(idUser);
    }

    @Override
    public Quiz add(Authentication authentication, Quiz quiz) {
        long idOwner = ((JwtUser) authentication.getPrincipal()).getId();

        User owner = this.userRepository.findById(idOwner).
                orElseThrow(() -> new IllegalArgumentException("Auth user not found"));
        try {
            for (Result result : quiz.getResults()) {
                result.setQuiz(quiz);
            }
            return this.quizRepository.save(quiz);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Quiz has already added!");
        }
    }
}
