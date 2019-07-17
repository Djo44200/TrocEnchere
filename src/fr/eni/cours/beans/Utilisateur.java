package fr.eni.cours.beans;

import java.io.Serializable;

public class Utilisateur implements Serializable{

	/**
	 * AMR
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int numUtil;
	private String nom;
	private String pseudo;
	private String prenom;
	private String mail;
	private String telephone;
	private String rue;
	private String ville;
	private String mdp;
	private String cp;
	private int credit;
	

	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}


	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}

	
	
	
	//Constructeur utilisé par AjouterUtilisateur
		public Utilisateur(String pseudo, String nom, String prenom, String mail, String telephone,
				String rue, String cp, String ville, String mdp, int cred) {
			
		
			setPseudo(pseudo);
			setNom(nom);
			setPrenom(prenom);
			setMail(mail);
			setTelephone(telephone);
			setRue(rue);
			setCp(cp);
			setVille(ville);
			setMdp(mdp);
			setCredit(150);
			
		}
		
	//Constructeur avec toutes les var d'instance
	public Utilisateur(int numUtil, String pseudo, String nom, String prenom, String mail, String telephone,
			String rue, String cp, String ville, String mdp, int cred) {
		
		setNumUtil(numUtil);
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setTelephone(telephone);
		setRue(rue);
		setCp(cp);
		setVille(ville);
		setMdp(mdp);
		setCredit(150);
		
	}


	//Constructeur avec toutes les var d'instance sauf credit
	public Utilisateur(int numUtil, String pseudo, String nom, String prenom, String mail, String telephone,
			String rue, String cp, String ville, String mdp) {
		
		setNumUtil(numUtil);
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setTelephone(telephone);
		setRue(rue);
		setCp(cp);
		setVille(ville);
		setMdp(mdp);
		
	}


	//Constructeur avec toutes les variables d'instance sauf le numUtil
	public Utilisateur(String pseudo, String nom, String prenom, String mail, String telephone, String rue,
			String cp, String ville, String mdp) {
		
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setTelephone(telephone);
		setRue(rue);
		setCp(cp);
		setVille(ville);
		setMdp(mdp);
	}


	//Constructeur sans paramètre
	public Utilisateur() {
	}
	
	
	/**
	 * @return the numUtil
	 */
	public int getNumUtil() {
		return numUtil;
	}
	/**
	 * @param numUtil the numUtil to set
	 */
	public void setNumUtil(int numUtil) {
		this.numUtil = numUtil;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the rue
	 */
	public String getRue() {		
		return rue;
	}
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}


	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}


	@Override
	public String toString() {
		return "Utilisateurs [numUtil=" + getNumUtil() + ", nom=" + getNom() + ", pseudo=" + getPseudo() + ", prenom=" + getPrenom()
				+ ", mail=" + getMail() + ", telephone=" + getTelephone() + ", rue=" + getRue() + ", ville=" + getVille() + ", code postal=" + getCp() + "]";
	}
	
	

	
}
