package dev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.controller.dto.BatimentDto;
import dev.controller.dto.BatimentJoueurDto;
import dev.entites.batiment.Batiment;
import dev.entites.joueur.BatimentJoueur;
import dev.repository.batiment.BatimentRepo;

@Service
public class BatimentService {
	
	private BatimentRepo batimentRepo;

	/**
	 * @param batimentRepo
	 */
	public BatimentService(BatimentRepo batimentRepo) {
		this.batimentRepo = batimentRepo;
	}
	
	public List<BatimentDto> listerBatiments() {
		List<BatimentDto> listeBatiments = new ArrayList<>();

		for (Batiment batiment : batimentRepo.findAll()) {

			BatimentDto batimentDto = new BatimentDto();
			batimentDto.setIdTypeBatiment(batiment.getIdTypeBatiment());
			batimentDto.setIcone(batiment.getIcone());
			batimentDto.setLibelle(batiment.getLibelle());
			batimentDto.setDescriptif(batiment.getDescriptif());
			batimentDto.setOuvrierNecessaireConstruction(batiment.getOuvrierNecessaireConstruction());
			batimentDto.setTempsDeConstruction(batiment.getTempsDeConstruction());
			batimentDto.setCoutPierreConstruction(batiment.getCoutPierreConstruction());
			batimentDto.setCoutBoisConstruction(batiment.getCoutBoisConstruction());
			batimentDto.setCoutOrConstruction(batiment.getCoutOrConstruction());
			batimentDto.setCoutNourritureConstruction(batiment.getCoutNourritureConstruction());
			batimentDto.setNiveauHotelDeVilleNecessaireConstruction(batiment.getNiveauHotelDeVilleNecessaireConstruction());

			listeBatiments.add(batimentDto);

		}
		return listeBatiments;
	}
	
	public BatimentDto detailsBatiment(Integer idTypeBatiment) {
		Batiment batiment =  batimentRepo.findByIdTypeBatiment(idTypeBatiment);
		BatimentDto bat = new BatimentDto(batiment.getIdTypeBatiment());
		return bat;
	}

}
