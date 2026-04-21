package abdullaevaziz.service;

import abdullaevaziz.model.Result;
import abdullaevaziz.repository.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResultServiceImpl implements ResultService{

    private ResultRepository resultRepository;

    @Autowired
    public void setRepository(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    @Override
    public Result get(long id) {
        return this.resultRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Result not found"));
    }

    @Override
    public List<Result> getQuiz(long idQuiz){
        return this.resultRepository.findAllByQuizId(idQuiz);
    }


    @Override
    public void add(Result result) {
        try {
           this.resultRepository.save(result);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Result has already added!");
        }
    }
}
