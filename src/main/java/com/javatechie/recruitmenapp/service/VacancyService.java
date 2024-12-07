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

    // Add Vacancy
    public Vacancy addVacancy(Vacancy vacancy) {
        vacancy.setVacancyId(UUID.randomUUID().toString().toString().split("-")[0]);
        return repository.save(vacancy);
    }
    // Find all Vacancy
    public List<Vacancy> findAllVacancy() {
        return repository.findAll();
    }
    // Find Active Vacancy
    public List<Vacancy> getActiveVacancy(){
        return vacancyRepository.findActiveVacancy(LocalDate.now());
    }
    // Update Vacancy
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
    //Delete Vacancy
    public String deleteVacancy(String vacancyId){
        repository.deleteById(vacancyId);
        return vacancyId + "Vacancy deleted";
    }
}