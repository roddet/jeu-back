package dev.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Joueur {

	// Déclarations
    /** id du joueur **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** icone du joueur **/
    private String icone;

    /** pseudo du joueur **/
    private String pseudo;
    
    /** email du joueur **/
    private String email;
    
	/** mot de passe du joueur **/
    private String motDePasse;
    
    /** descriptif fiche perso du joueur **/
    private String descriptif;
    
    /** niveau du joueur **/
    private Integer niveau;
    
    /** experience du joueur **/
    private Integer experience;
    
    /** ressources de pierre que possède le joueur **/
    private Integer pierrePossession;

    /** ressources de bois que possède le joueur **/
    private Integer boisPossession;

    /** ressources d'or que possède le joueur **/
    private Integer orPossession;

    /** ressources de nourriture que possède le joueur **/
    private Integer nourriturePossession;

    /** ressources de gemme que possède le joueur **/
    private Integer gemmePossession;

    /** montant maximum de pierre que peut posséder le joueur **/
    private Integer pierreMaximum;
    
    /** montant maximum de bois que peut posséder le joueur **/
    private Integer boisMaximum;
    
    /** montant maximum d'or que peut posséder le joueur **/
    private Integer orMaximum;
    
    /** montant maximum de nourriture que peut posséder le joueur **/
    private Integer nourritureMaximum;
    
    /** montant du boost de production de pierre pour le joueur **/
    private Integer pierreBoostProduction;

    /** montant du boost de production de bois pour le joueur **/
    private Integer boisBoostProduction;

    /** montant du boost de production d'or pour le joueur **/
    private Integer orBoostProduction;

    /** montant du boost de production de nourriture pour le joueur **/
    private Integer nourritureBoostProduction;

    /** temps de connexion (en secondes) du joueur **/
    private Integer tempsDeJeu;
    

    /** roles du joueur **/
    @OneToMany(mappedBy = "joueur", cascade = CascadeType.PERSIST)
    private List<RoleJoueur> roles;

    
    public Joueur() {
    	
    }

	/**
	 * @param id
	 * @param icone
	 * @param pseudo
	 * @param email
	 * @param motDePasse
	 * @param descriptif
	 * @param niveau
	 * @param experience
	 * @param pierrePossession
	 * @param boisPossession
	 * @param orPossession
	 * @param nourriturePossession
	 * @param gemmePossession
	 * @param pierreMaximum
	 * @param boisMaximum
	 * @param orMaximum
	 * @param nourritureMaximum
	 * @param pierreBoostProduction
	 * @param boisBoostProduction
	 * @param orBoostProduction
	 * @param nourritureBoostProduction
	 * @param tempsDeJeu
	 * @param roles
	 */
	public Joueur(Integer id, String icone, String pseudo, String email, String motDePasse, String descriptif,
			Integer niveau, Integer experience, Integer pierrePossession, Integer boisPossession, Integer orPossession,
			Integer nourriturePossession, Integer gemmePossession, Integer pierreMaximum, Integer boisMaximum,
			Integer orMaximum, Integer nourritureMaximum, Integer pierreBoostProduction, Integer boisBoostProduction,
			Integer orBoostProduction, Integer nourritureBoostProduction, Integer tempsDeJeu, List<RoleJoueur> roles) {
		super();
		this.id = id;
		this.icone = icone;
		this.pseudo = pseudo;
		this.email = email;
		this.motDePasse = motDePasse;
		this.descriptif = descriptif;
		this.niveau = niveau;
		this.experience = experience;
		this.pierrePossession = pierrePossession;
		this.boisPossession = boisPossession;
		this.orPossession = orPossession;
		this.nourriturePossession = nourriturePossession;
		this.gemmePossession = gemmePossession;
		this.pierreMaximum = pierreMaximum;
		this.boisMaximum = boisMaximum;
		this.orMaximum = orMaximum;
		this.nourritureMaximum = nourritureMaximum;
		this.pierreBoostProduction = pierreBoostProduction;
		this.boisBoostProduction = boisBoostProduction;
		this.orBoostProduction = orBoostProduction;
		this.nourritureBoostProduction = nourritureBoostProduction;
		this.tempsDeJeu = tempsDeJeu;
		this.roles = roles;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the icone
	 */
	public String getIcone() {
		return icone;
	}

	/**
	 * @param icone the icone to set
	 */
	public void setIcone(String icone) {
		this.icone = icone;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the descriptif
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * @param descriptif the descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * @return the niveau
	 */
	public Integer getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * @return the pierrePossession
	 */
	public Integer getPierrePossession() {
		return pierrePossession;
	}

	/**
	 * @param pierrePossession the pierrePossession to set
	 */
	public void setPierrePossession(Integer pierrePossession) {
		this.pierrePossession = pierrePossession;
	}

	/**
	 * @return the boisPossession
	 */
	public Integer getBoisPossession() {
		return boisPossession;
	}

	/**
	 * @param boisPossession the boisPossession to set
	 */
	public void setBoisPossession(Integer boisPossession) {
		this.boisPossession = boisPossession;
	}

	/**
	 * @return the orPossession
	 */
	public Integer getOrPossession() {
		return orPossession;
	}

	/**
	 * @param orPossession the orPossession to set
	 */
	public void setOrPossession(Integer orPossession) {
		this.orPossession = orPossession;
	}

	/**
	 * @return the nourriturePossession
	 */
	public Integer getNourriturePossession() {
		return nourriturePossession;
	}

	/**
	 * @param nourriturePossession the nourriturePossession to set
	 */
	public void setNourriturePossession(Integer nourriturePossession) {
		this.nourriturePossession = nourriturePossession;
	}

	/**
	 * @return the gemmePossession
	 */
	public Integer getGemmePossession() {
		return gemmePossession;
	}

	/**
	 * @param gemmePossession the gemmePossession to set
	 */
	public void setGemmePossession(Integer gemmePossession) {
		this.gemmePossession = gemmePossession;
	}

	/**
	 * @return the pierreMaximum
	 */
	public Integer getPierreMaximum() {
		return pierreMaximum;
	}

	/**
	 * @param pierreMaximum the pierreMaximum to set
	 */
	public void setPierreMaximum(Integer pierreMaximum) {
		this.pierreMaximum = pierreMaximum;
	}

	/**
	 * @return the boisMaximum
	 */
	public Integer getBoisMaximum() {
		return boisMaximum;
	}

	/**
	 * @param boisMaximum the boisMaximum to set
	 */
	public void setBoisMaximum(Integer boisMaximum) {
		this.boisMaximum = boisMaximum;
	}

	/**
	 * @return the orMaximum
	 */
	public Integer getOrMaximum() {
		return orMaximum;
	}

	/**
	 * @param orMaximum the orMaximum to set
	 */
	public void setOrMaximum(Integer orMaximum) {
		this.orMaximum = orMaximum;
	}

	/**
	 * @return the nourritureMaximum
	 */
	public Integer getNourritureMaximum() {
		return nourritureMaximum;
	}

	/**
	 * @param nourritureMaximum the nourritureMaximum to set
	 */
	public void setNourritureMaximum(Integer nourritureMaximum) {
		this.nourritureMaximum = nourritureMaximum;
	}

	/**
	 * @return the pierreBoostProduction
	 */
	public Integer getPierreBoostProduction() {
		return pierreBoostProduction;
	}

	/**
	 * @param pierreBoostProduction the pierreBoostProduction to set
	 */
	public void setPierreBoostProduction(Integer pierreBoostProduction) {
		this.pierreBoostProduction = pierreBoostProduction;
	}

	/**
	 * @return the boisBoostProduction
	 */
	public Integer getBoisBoostProduction() {
		return boisBoostProduction;
	}

	/**
	 * @param boisBoostProduction the boisBoostProduction to set
	 */
	public void setBoisBoostProduction(Integer boisBoostProduction) {
		this.boisBoostProduction = boisBoostProduction;
	}

	/**
	 * @return the orBoostProduction
	 */
	public Integer getOrBoostProduction() {
		return orBoostProduction;
	}

	/**
	 * @param orBoostProduction the orBoostProduction to set
	 */
	public void setOrBoostProduction(Integer orBoostProduction) {
		this.orBoostProduction = orBoostProduction;
	}

	/**
	 * @return the nourritureBoostProduction
	 */
	public Integer getNourritureBoostProduction() {
		return nourritureBoostProduction;
	}

	/**
	 * @param nourritureBoostProduction the nourritureBoostProduction to set
	 */
	public void setNourritureBoostProduction(Integer nourritureBoostProduction) {
		this.nourritureBoostProduction = nourritureBoostProduction;
	}

	/**
	 * @return the tempsDeJeu
	 */
	public Integer getTempsDeJeu() {
		return tempsDeJeu;
	}

	/**
	 * @param tempsDeJeu the tempsDeJeu to set
	 */
	public void setTempsDeJeu(Integer tempsDeJeu) {
		this.tempsDeJeu = tempsDeJeu;
	}

	/**
	 * @return the roles
	 */
	public List<RoleJoueur> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleJoueur> roles) {
		this.roles = roles;
	}
    
    
    



	
}