package com.javatechie.recruitmenapp.repository;

import com.javatechie.recruitmenapp.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacancyRepository extends MongoRepository<Vacancy,String> {
//    List<Vacancy> findByVacancyName(String title);
}
