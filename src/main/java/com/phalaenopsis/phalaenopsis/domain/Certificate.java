package com.phalaenopsis.phalaenopsis.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Certificate {
    @Id
    @GeneratedValue
    public Long id;
    public String username;
    public LocalDate dateIssued;
    public String name;
    public String surname;

    public Certificate(String username, LocalDate dateIssued) {
        this.username = username;
        this.dateIssued = dateIssued;
    }

    public Certificate(String username, LocalDate dateIssued, String name, String surname) {
        this.username = username;
        this.dateIssued = dateIssued;
        this.name = name;
        this.surname = surname;
    }

    public Certificate() {

    }

}
