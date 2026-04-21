package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFileRepository extends JpaRepository<UserFile, Long> {
    List<UserFile> findByUser(User user);

    List<UserFile> findAllByUserId(long userId);
    Optional<UserFile> findByUserIdAndFilename(long userId, String filename);


}
