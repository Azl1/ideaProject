package com.kirillkotov.repository;

import com.kirillkotov.model.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TVRepository extends JpaRepository<TV, Long> {
    Optional<TV> findByBrandAndModel(String brand, String model); //на возврат либо List, либо Optional, либо объект

    List<TV> deleteByBrandAndColor(String brand, String color); //на возврат либо void, int, List

    List<TV> findAllByBrandIn(List<String> brands);

    //Custom query
    @Modifying
    @Query("delete from TV t where t.brand=:brand or t.model=:model")
    //@Query("delete from TV tv where tv.brand=?1 or tv.model=?2") // @param не нужен
    void deleteTvs(@Param("brand") String brand, @Param("model") String model); //на возврат только void или int

    @Modifying
    @Query("update TV t set t.color=:color where t.brand=:brand")
    int updateColor(@Param("brand") String brand, @Param("color") String color);

    /*@Modifying
    @Query("SELECT tv from TV tv JOIN tv.user user WHERE user.firstName=:firstName")
    List<TV> findAllByUserFirstName(@Param("firstName") String firstName);*/

    List<TV> findAllByUserFirstName(String firstName);
}
