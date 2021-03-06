package dev.services.joueur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;

import dev.controller.dto.joueur.ArmeeDto;
import dev.controller.dto.joueur.ArmeeJoueurCreationDto;
import dev.controller.dto.unitee.UniteeDto;
import dev.entites.joueur.Armee;
import dev.entites.joueur.Joueur;
import dev.entites.unitee.Unitee;
import dev.exceptions.RessourceManquanteException;
import dev.repository.JoueurRepo;
import dev.repository.joueur.ArmeeRepo;
import dev.repository.unitee.UniteeRepo;
import dev.services.unitee.UniteeService;

@Service
public class ArmeeService {

	JoueurService joueurService;
	UniteeService uniteeService;
	ArmeeRepo armeeRepo;
	JoueurRepo joueurRepo;
	
	/**
	 * @param joueurService
	 * @param armeeRepo
	 */
	public ArmeeService(JoueurService joueurService, ArmeeRepo armeeRepo, UniteeService uniteeService, JoueurRepo joueurRepo) {
		super();
		this.joueurService = joueurService;
		this.armeeRepo = armeeRepo;
		this.uniteeService = uniteeService;
		this.joueurRepo = joueurRepo;
	}

	/**
	 * CREATION ARMEE DU JOUEUR (Nouvelles unitées)
	 * */
	public ArmeeJoueurCreationDto produireUnitee(ArmeeJoueurCreationDto armeeJoueurCreationDto) {
		// RÉCUPÉRATION DU JOUEUR CONNECTÉ
		Joueur jou = this.joueurService.recuperationJoueur();
		
		// INITIALISATIONS
		Boolean flag = false;
		Integer idArmee = 0;
		Integer quantitee = 0;
		Integer quantiteePierreManquante;
		Integer quantiteeBoisManquant;
		Integer quantiteeOrManquant;
		Integer quantiteeNourritureManquante;
		Long tempsDebutFormation = 0L;
		Long tempsFinFormation = 0L;
		Long finProduction = 0L;
		
		// INFORMATIONS SUR L'UNITÉE QUE LE JOUEUR VEUT CRÉER
		UniteeDto un = this.uniteeService.detailsUnitee(armeeJoueurCreationDto.getIdUnitee());
		Unitee uni = new Unitee();
		uni.setId(un.getId());
		uni.setIdTypeUnitee(un.getIdTypeUnitee());
		uni.setIdBatimentProvenance(un.getIdBatimentProvenance());
		uni.setIcone(un.getIcone());
		uni.setLibelle(un.getLibelle());
		uni.setDescriptif(un.getDescriptif());
		uni.setCoutPierreFormation(un.getCoutPierreFormation());
		uni.setCoutBoisFormation(un.getCoutBoisFormation());
		uni.setCoutOrFormation(un.getCoutOrFormation());
		uni.setCoutNourritureFormation(un.getCoutNourritureFormation());
		uni.setCoutHumain(un.getCoutHumain());
		uni.setTempsFormation(un.getTempsFormation());
		uni.setVie(un.getVie());
		uni.setAttaque(un.getAttaque());
		uni.setPortee(un.getPortee());
		uni.setArmure(un.getArmure());
		uni.setNiveauBatimentNecessaireFormation(un.getNiveauBatimentNecessaireFormation());
		uni.setApportRessourcePierreHeure(un.getApportRessourcePierreHeure());
		uni.setApportRessourceBoisHeure(un.getApportRessourceBoisHeure());
		uni.setApportRessourceOrHeure(un.getApportRessourceOrHeure());
		uni.setApportRessourceNourritureHeure(un.getApportRessourceNourritureHeure());

		// INITIALISATIONS DES COÛTS DE FORMATIONS (COÛT UNITÉE RESSOURCE X QUANTITÉE)
		Integer coutPierreOperation = uni.getCoutPierreFormation() * armeeJoueurCreationDto.getQuantitee();
		Integer coutBoisOperation = uni.getCoutBoisFormation() * armeeJoueurCreationDto.getQuantitee();
		Integer coutOrOperation = uni.getCoutOrFormation() * armeeJoueurCreationDto.getQuantitee();
		Integer coutNourritureOperation = uni.getCoutNourritureFormation() * armeeJoueurCreationDto.getQuantitee();

		/* VÉRIFICATIONS : LE JOUEUR À T'IL ASSEZ DE RESSOURCES ?? */
		// - SI RESSOURCES INSUFFISANTES 
		// -- Pierre manquante :
		if(jou.getPierrePossession() < coutPierreOperation) {
			quantiteePierreManquante = coutPierreOperation-jou.getPierrePossession();
			throw new RessourceManquanteException("Il vous manque "+quantiteePierreManquante+" de pierre pour lancer la production.");
		}		
		// -- Bois manquant :
		else if(jou.getBoisPossession() < coutBoisOperation) {
			quantiteeBoisManquant = coutBoisOperation-jou.getBoisPossession();
			throw new RessourceManquanteException("Il vous manque "+quantiteeBoisManquant+" de bois pour lancer la production.");
		}
		// -- Or manquant :
		else if(jou.getOrPossession() < coutOrOperation) {
			quantiteeOrManquant = coutOrOperation-jou.getOrPossession();
			throw new RessourceManquanteException("Il vous manque "+quantiteeOrManquant+" d'or pour lancer la production.");
		}
		// -- Nourriture manquante :
		else if(jou.getNourriturePossession() < coutNourritureOperation) {
			quantiteeNourritureManquante = coutNourritureOperation-jou.getNourriturePossession();
			throw new RessourceManquanteException("Il vous manque "+quantiteeNourritureManquante+" de nourriture pour lancer la production.");
		}
		
		// PARCOURIR LES ARMÉES QUE POSSÈDE DÉJÀ LE JOUEUR
		for (Armee arme : armeeRepo.findByJoueur(jou)) {
			// - S'il possède déjà un type d'unitée, récupération des données (L'id de l'armée, et la quantitée d'unitées)
			if(arme.getUnitee().getId()==armeeJoueurCreationDto.getIdUnitee()) {
				flag = true;
				idArmee = arme.getId();
				quantitee = arme.getQuantitee();
				tempsDebutFormation = arme.getDateDebutProduction();
				tempsFinFormation = arme.getDateFinProduction();
			}
		}
		
		// MOMENT "T" DE LANCEMENT DE LE FORMATION DES UNITEES
		
		System.out.println("------------------------------------------");
		// MOMENT "T" DE LANCEMENT DE LE FORMATION DES UNITEES + TEMPS DE FORMATIONS * NOMBRE D'UNITTES

		
		Armee armee = new Armee();
		// SI LE JOUEUR POSSÈDE DÉJÀ CE TYPE D'UNITÉE, ALORS ON ADITIONNE LES QUANTITÉES (NOUVELLE + ANCIENNE) + ÉCRASE L'ARMÉE DÉJÀ EXISTANTE
		if(flag==true) {
			armee.setId(idArmee);
			armee.setQuantitee(quantitee + armeeJoueurCreationDto.getQuantitee());
		}
		else // SINON, CRÉATION D'UNE NOUVELLE ARMÉE, POUR CE GENRE D'UNITÉE
		{
			armee.setQuantitee(armeeJoueurCreationDto.getQuantitee());
		}
		
		System.out.println(armee.toString());
		// Nouvelle production = Nouvelle date de début. 
		if(tempsFinFormation<new Date().getTime()) {
			System.out.println("Nouveau type d'unitée créé");
			
			tempsDebutFormation = new Date().getTime();
			finProduction = new Date().getTime()+((uni.getTempsFormation()*armeeJoueurCreationDto.getQuantitee())*1000);
			
			armee.setDateDebutProduction(tempsDebutFormation);

		} 
		else // Sinon, date de début inchangée, production toujours en cours. Ajout de temps
		{
			System.out.println("Type unitée déjà en cours de formation : ajout à la file d'attente  :"+tempsDebutFormation);
			finProduction = tempsFinFormation+((uni.getTempsFormation()*armeeJoueurCreationDto.getQuantitee())*1000);
			armee.setDateDebutProduction(tempsDebutFormation);
		}
		armee.setDateFinProduction(finProduction);

		// MISE A JOUR DU JOUEUR
		// - Retrait de ressources
		jou.setPierrePossession(jou.getPierrePossession()-coutPierreOperation);
		jou.setBoisPossession(jou.getBoisPossession()-coutBoisOperation);
		jou.setOrPossession(jou.getOrPossession()-coutOrOperation);
		jou.setNourriturePossession(jou.getNourriturePossession()-coutNourritureOperation);
		
		Joueur joueur = new Joueur(jou.getArmee(),jou.getIcone(),jou.getPseudo(),jou.getEmail(),jou.getMotDePasse(),jou.getDescriptif(),jou.getNiveau(),jou.getExperience(),jou.getPierrePossession(),jou.getBoisPossession(),jou.getOrPossession(),jou.getNourriturePossession(),jou.getGemmePossession(),jou.getPierreMaximum(),jou.getBoisMaximum(),jou.getOrMaximum(),jou.getNourritureMaximum(),jou.getPierreBoostProduction(),jou.getBoisBoostProduction(),jou.getOrBoostProduction(),jou.getNourritureBoostProduction(),jou.getTempsDeJeu(),jou.getRoles(), jou.getDerniereConnexion());
		joueur.setId(jou.getId());
		
		// MISE A JOUR DE L'ARMEE
		armee.setJoueur(jou);
		armee.setUnitee(uni);
		
		// SAUVEGARDES
		this.joueurRepo.save(joueur);
		this.armeeRepo.save(armee);
		
		// RETOUR
		return new ArmeeJoueurCreationDto(armee.getUnitee().getId(), armee.getQuantitee());
	}
	
	/**
	 * LISTER LES ARMEES DU JOUEURS
	 * */
	public List<ArmeeDto> listerArmeesDuJoueur() {
		// RÉCUPÉRATION DU JOUEUR CONNECTÉ
		Joueur jou = this.joueurService.recuperationJoueur();
		
		List<ArmeeDto> listeArmeesDuJoueur = new ArrayList<>();
		for (Armee armee : armeeRepo.findByJoueur(jou)) {
			ArmeeDto armeeDto = new ArmeeDto();
			armeeDto.setId(armee.getId());
			armeeDto.setJoueur(armee.getJoueur());
			armeeDto.setUnitee(armee.getUnitee());
			armeeDto.setQuantitee(armee.getQuantitee());
			armeeDto.setDateDebutProduction(armee.getDateDebutProduction());
			armeeDto.setDateFinProduction(armee.getDateFinProduction());
			
			listeArmeesDuJoueur.add(armeeDto);
		}
		
		// RETOUR
		return listeArmeesDuJoueur;
	}
}
