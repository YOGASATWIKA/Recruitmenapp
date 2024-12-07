package com.javatechie.recruitmenapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

    @Id
    private String vacancyId;
    private String vacancy;
    private String vacancyName;
    private String description;
    private int maximumAge;
    private int minimumExperience;
    private String salary;
    private LocalDate publishDate;
    private LocalDate expiredDate;

    public void setDates() {
        this.publishDate = LocalDate.now();
        this.expiredDate = this.publishDate.plusDays(30);
    }
}
