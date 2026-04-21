package abdullaevaziz.controllers;

import abdullaevaziz.dto.ResponseResult;
import abdullaevaziz.model.History;
import abdullaevaziz.model.Quiz;
import abdullaevaziz.securety.jwt.JwtUser;
import abdullaevaziz.service.HistoryService;
import abdullaevaziz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;
    private HistoryService historyService;

    @Autowired
    public void setHistoryService(HistoryService historyService){
        this.historyService = historyService;
    }

    @Autowired
    public void setQuizResultService(QuizService quizService){
        this.quizService = quizService;
    }


    @GetMapping("/questions")
    public ResponseEntity<ResponseResult<Quiz>> get(Authentication authentication,
                                                    @RequestParam int amount,
                                                    @RequestParam int category,
                                                    @RequestParam String difficulty) {
        try {
            Quiz quiz = this.quizService.get(authentication, amount, category, difficulty);
            return new ResponseEntity<>(new ResponseResult<>(null, quiz),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/answer/{resultId}")
    public ResponseEntity<ResponseResult<History>> getAnswer(Authentication authentication,
                                                             @PathVariable long resultId,
                                                             @RequestParam String answer) {
        try {
            History historyGetAnswer = this.historyService.getAnswer(authentication, resultId, answer);
            return new ResponseEntity<>(new ResponseResult<>(null, historyGetAnswer),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/historyList/{idUser}")
    public ResponseEntity<ResponseResult<List<History>>> getAnswer(@PathVariable long idUser,
                                                                   Authentication authentication) {
        try {
            List<History> historyGetAnswer = this.historyService.getHistoryList(idUser, authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, historyGetAnswer),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/answer/{idQuiz}")
    public ResponseEntity<ResponseResult<List<History>>> getHistoryQuiz(Authentication authentication,
                                                                        @PathVariable long idQuiz) {
        try {
            List<History> historyGetAnswer = this.historyService.getHistoryQuiz(authentication, idQuiz);
            return new ResponseEntity<>(new ResponseResult<>(null, historyGetAnswer),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{idUser}")
    public ResponseEntity<ResponseResult<List<Quiz>>> getListQuiz(@PathVariable long idUser, Authentication authentication) {
        try {
            List<Quiz> quizList = this.quizService.getListQuiz(idUser, authentication);
            return new ResponseEntity<>(new ResponseResult<>(null, quizList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/history")
    public ResponseEntity<ResponseResult<History>> postHistory(Authentication authentication, @RequestBody History history) {
        try {
            this.historyService.addHistory(authentication, history);
            return new ResponseEntity<>(new ResponseResult<>(null, history), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Quiz>> add(Authentication authentication, @RequestBody Quiz quiz) {
        try {
            Quiz addQuiz = this.quizService.add(authentication, quiz);
            return new ResponseEntity<>(new ResponseResult<>(null, addQuiz),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
