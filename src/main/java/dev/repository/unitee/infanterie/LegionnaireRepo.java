package dev.repository.unitee.infanterie;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entites.unitee.infanterie.Legionnaire;


public interface LegionnaireRepo extends JpaRepository<Legionnaire, Integer> {

}