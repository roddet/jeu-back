package dev.entites.social;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ListeAmis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
}
