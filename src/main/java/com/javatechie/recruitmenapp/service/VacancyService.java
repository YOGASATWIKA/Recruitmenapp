package com.javatechie.recruitmenapp.service;


import com.javatechie.recruitmenapp.model.Vacancy;
import com.javatechie.recruitmenapp.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository repository;
    @Autowired
    private VacancyRepository vacancyRepository;

    //Create Vacancy
    public Vacancy addVacancy(Vacancy vacancy) {
        vacancy.setVacancyId(UUID.randomUUID().toString().toString().split("-")[0]);
        return repository.save(vacancy);
    }

    public List<Vacancy> findAllVacancy() {
        return repository.findAll();
    }

    public Vacancy getVacancyById(String vacancyId) {
        return repository.findById(vacancyId).get();
    }

    public List<Vacancy> getActiveVacancy(){
        return vacancyRepository.findActiveVacancy(LocalDate.now());
    }

    public Vacancy updateVacancy(Vacancy vacancyRequest) {
        Vacancy existingVacancy = repository.findById(vacancyRequest.getVacancyId()).get();
        existingVacancy.setVacancy(vacancyRequest.getVacancy());
        existingVacancy.setVacancyName(vacancyRequest.getVacancyName());
        existingVacancy.setDescription(vacancyRequest.getDescription());
        existingVacancy.setMaximumAge(vacancyRequest.getMaximumAge());
        existingVacancy.setMinimumExperience(vacancyRequest.getMinimumExperience());
        existingVacancy.setSalary(vacancyRequest.getSalary());
        existingVacancy.setPublishDate(vacancyRequest.getPublishDate());
        existingVacancy.setExpiredDate(vacancyRequest.getExpiredDate());
        return repository.save(existingVacancy);
    }

    public String deleteVacancy(String vacancyId){
        repository.deleteById(vacancyId);
        return vacancyId + "Vacancy deleted";
    }
}