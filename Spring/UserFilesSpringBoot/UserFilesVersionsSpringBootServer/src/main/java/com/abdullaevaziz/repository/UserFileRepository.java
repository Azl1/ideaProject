package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFileRepository extends JpaRepository<UserFile, Long> {

    UserFile findByUserId(long userId);
    //List<UserFile> findByUserIdList(long userId);
    List<UserFile> findByUserIdAndFilename(long userId, String filename);
    List<UserFile> findFirstByUserIdAndFilename (long userId, String filename);
    //List<UserFile> findFirstByUserIdAndFilenameList (long userId, String filename);
    Optional<UserFile> findFirstByUserIdAndFilenameAndVersion (long userId, String fileName, Integer version);
    List<UserFile> findAllByUserId(long userId);
    List<UserFile> findAllByUserIdAndFilename(long userId, String filename);

}
