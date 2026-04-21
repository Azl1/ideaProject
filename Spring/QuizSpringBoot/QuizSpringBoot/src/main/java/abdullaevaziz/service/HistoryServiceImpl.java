package abdullaevaziz.service;

import abdullaevaziz.model.*;
import abdullaevaziz.repository.HistoryRepository;
import abdullaevaziz.repository.UserRepository;
import abdullaevaziz.securety.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private ResultService resultService;

    private HistoryRepository historyRepository;

    private UserRepository userRepository;

    @Autowired
    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void addHistory(Authentication authentication, History history) {
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User getUser = this.userRepository.findById(autUserId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        history.setUser(getUser);
        try {
            this.historyRepository.save(history);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("History has already added!");
        }
    }

    @Override
    public History getAnswer(Authentication authentication, long resultId, String answer) {
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User getUser = this.userRepository.findById(autUserId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        Result result = this.resultService.get(resultId);
        boolean res = result.getCorrectAnswer().equals(answer);
        System.out.println("boolean:---------------------------------" +  res);
        History history = new History(result, answer, res);
        this.addHistory(authentication, history);

        return history;
    }

    /**
     * 4. Ведения и просмотра статистики всех ответов
     * на вопросы аутентифицированного пользователя
     */
    @Override
    public List<History> getHistoryList(long idUser, Authentication authentication) {
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User getUser = this.userRepository.findById(autUserId).
                orElseThrow(() -> new IllegalArgumentException("Auth user not found"));
        if (getUser.getUserType() != UserType.ADMIN && idUser != autUserId) {
            throw new IllegalArgumentException("Нет доступа!");
        }
        return this.historyRepository.findAllByUserId(idUser);
    }

    /**
     * 5. Ведения и просмотра статистики правильных и неправильных ответов
     * аутентифицированного пользователя в рамках конкретной викторины
     */
    @Override
    public List<History> getHistoryQuiz(Authentication authentication, long idQuiz){
        long idOwner = ((JwtUser) authentication.getPrincipal()).getId();
        User owner = this.userRepository.findById(idOwner).
                orElseThrow(()-> new IllegalArgumentException("Auth user not found"));
        if (owner.getUserType() != UserType.ADMIN && owner.getUserType() != UserType.USER) {
            throw new IllegalArgumentException("Нет доступа!");
        }
       // Result result = this.resultService.get(idQuiz);
        return this.historyRepository.findAllByResultQuizId(idQuiz);
    }

}
