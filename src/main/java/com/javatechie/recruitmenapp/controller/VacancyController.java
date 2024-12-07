package com.javatechie.recruitmenapp.controller;


import com.javatechie.recruitmenapp.model.Vacancy;
import com.javatechie.recruitmenapp.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    @Autowired
    private VacancyService service;
    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vacancy createvacancys(@RequestBody Vacancy vacancys){
        return service.addVacancy(vacancys);
    }

    @GetMapping
    public List<Vacancy> getAllVacancies() {
        return service.findAllVacancy();
    }

    @GetMapping("/{vacancyId}")
    public Vacancy getVacancy(@PathVariable String vacancyId) {
        return service.getVacancyById(vacancyId);
    }

    @GetMapping("/active")
    public List<Vacancy> getActiveVacancies() {
        List<Vacancy> activeVacancy = vacancyService.getActiveVacancy();
        return ResponseEntity.ok(activeVacancy).getBody();
    }


    @PutMapping
    public Vacancy modifyVacancy(@RequestBody Vacancy vacancy){
        return service.updateVacancy(vacancy);
    }

    @DeleteMapping("/{vacancyId}")
    public String deleteVacancy(@PathVariable String vacancyId){
        return service.deleteVacancy(vacancyId);
    }
}
