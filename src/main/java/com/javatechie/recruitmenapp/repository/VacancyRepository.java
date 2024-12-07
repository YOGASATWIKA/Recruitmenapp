package com.javatechie.recruitmenapp.repository;

import com.javatechie.recruitmenapp.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacancyRepository extends MongoRepository<Vacancy,String> {
    // Query untuk mengambil lowongan aktif
    @Query("{ 'expiredDate' : { $gte: ?0 } }")
    List<Vacancy> findActiveVacancy(LocalDate currentDate);
}
