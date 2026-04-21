package abdullaevaziz.repository;

import abdullaevaziz.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History , Long> {

    List<History> findAllByUserId(long idUser);
    List<History> findAllByResultQuizId(long idQuiz);

}
