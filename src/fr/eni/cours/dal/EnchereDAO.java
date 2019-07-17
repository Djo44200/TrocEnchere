package fr.eni.cours.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.cours.beans.Enchere;
import fr.eni.cours.beans.Retrait;
import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.util.DBConnection;
import java.util.ArrayList;

/**
 * 
 * @author jbodet2019
 *
 */

public class EnchereDAO {
	
	private final static String RECHERCHER_UN_NUMUSER = "select no_utilisateur from ENCHERES where no_vente = ? ORDER BY montantEnchere DESC;";
	private final static String INSERERENCHERE = "insert into ENCHERES(date_enchere, no_utilisateur, no_vente, montantEnchere) values (?,?,?,?);";
	private final static String VERIFICATION_USER_ENCHERIR = "select * FROM ENCHERES WHERE no_utilisateur = ? AND no_vente = ? ;";
	private final static String RECHERCHE_NOVENTE_PAR_NOUSER = "select no_vente FROM ENCHERES WHERE no_utilisateur = ? ;";
	private final static String RECHERCHELISTEENCHEREPARNOVENTE = "select * FROM ENCHERES WHERE no_vente = ? ;";
	private final static String MODIFIER = "update ENCHERES set date_enchere = ?, montantEnchere = ? WHERE no_utilisateur= ? AND no_vente = ?;";
	private final static String SUPPRESSION_ENCHERE = "delete FROM ENCHERES WHERE no_utilisateur= ? AND no_vente = ?;";
	private final static String SUPPRESSION_ENCHERE_COMPLETE = "delete FROM ENCHERES WHERE no_vente = ?;";
	private final static String RECHERCHER_MEILLEURE_VENTE = "select * from ENCHERES where no_vente = ? ORDER BY montantEnchere DESC";

	public static Enchere rechercheUnUserEncheri(int numVente) throws DALException {
		// Méthode recherchant un numUtil d'une personne qui encheri
		
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Enchere enchere = new Enchere();
		
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(RECHERCHER_UN_NUMUSER);
			pstmt.setInt(1, numVente);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {	
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
			
			}
		} catch (SQLException e) {
			
			throw new DALException ("Probleme - Impossible de prendre un n° utilisateur ( EnchereDAO ) - " + e.getMessage());
		}finally{
			
			try {
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		return enchere;
	
	}
	
	//méthode pour créer une enchère
	
	
	
	public static void ajouterEnchere(String date, Enchere enchere) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
	
		try{
			cnx = DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(INSERERENCHERE);
			pstmt.setString(1, date);
			pstmt.setInt(2, enchere.getNoUtilisateur());
			pstmt.setInt(3, enchere.getNoVente()); 
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			

		} catch (SQLException e){
			throw new DALException ("Probleme - ajouter une enchère - " + e.getMessage());
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			}catch (SQLException e){
				throw new DALException ("Probleme - fermerConnexion - " + e.getMessage());
			}		
		}
	}
	
	public static Enchere verificationUserEncherir(int numVente, int numUser) throws DALException {
		// Permet de vérifier si l'utilisateur à enchéri au moins une fois

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere verificationEnchere = new Enchere();
		
		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(VERIFICATION_USER_ENCHERIR);
			pstmt.setInt(1, numUser);
			pstmt.setInt(2, numVente);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				verificationEnchere.setNoUtilisateur(numUser);
				verificationEnchere.setNoVente(numVente);
				verificationEnchere.setPrixEnchere(rs.getInt("montantEnchere"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Probleme - VerificationUserEncherir - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
		
		return verificationEnchere;
	}
	
	public static ArrayList<Integer> quelleVenteEncherie (int no_user) throws DALException {
		//chercher les ventes sur lesquelles l'utilisateur a ench�ri
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(RECHERCHE_NOVENTE_PAR_NOUSER);
			
			pstmt.setInt(1, no_user);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("no_vente"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Probleme - VerificationUserEncherir - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		return list;
	}
	
	
	
	
	//Méthode pour obtenir la liste d'enchère à partir du num vente
	public static ArrayList<Enchere> listeEncheres (int numVente) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Enchere> listeEnchere = new ArrayList<>();
			
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(RECHERCHELISTEENCHEREPARNOVENTE);					
				pstmt.setInt(1, numVente);	
				rs=pstmt.executeQuery();
				
				while (rs.next()) {
					Enchere enchereZ = new Enchere();
					
					enchereZ.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchereZ.setNoVente(numVente);
					enchereZ.setMontantEnchere(rs.getInt("montantEnchere"));
					listeEnchere.add(enchereZ);
					
				}
			} catch (SQLException e) {		
					throw new DALException ("Probleme - listeEnchere - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeEnchere;		
		}

	public static void modificationEnchere(String dateTime, int numUtil, int numVente,
			int proposEnchere) throws DALException {
		// Permet de modifier une enchère
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, dateTime);
			pstmt.setInt(2, proposEnchere);
			pstmt.setInt(3, numUtil);
			pstmt.setInt(4, numVente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - modifierEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
	}

	public static void suppressionEnchere(int numUtil, int numVente) throws DALException {
		//Suppression d'une enchère pour ensuite l'ajouter de nouveau avec les bonnes valeurs
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(SUPPRESSION_ENCHERE);
			pstmt.setInt(1, numUtil);
			pstmt.setInt(2, numVente);	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - suppressionEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
	}

	public static void ajouterEnchereSuiteSuppression(String date, int numUtil, int numVente, int proposEnchere) throws DALException {
		//Création d'une enchère suite à la suppression dans la base
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
	
		try{
			cnx = DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(INSERERENCHERE);
			pstmt.setString(1, date);
			pstmt.setInt(2, numUtil);
			pstmt.setInt(3, numVente); 
			pstmt.setInt(4, proposEnchere);
			pstmt.executeUpdate();
			

		} catch (SQLException e){
			throw new DALException ("Probleme - ajouter une enchère suite suppression - " + e.getMessage());
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			}catch (SQLException e){
				throw new DALException ("Probleme - fermerConnexion suite suppression - " + e.getMessage());
			}		
		}
	
		
	}

	public static void suppressionEnchereComplete(int numVente) throws DALException {
		// Suppression totale de l'enchere avec tous les users.
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(SUPPRESSION_ENCHERE_COMPLETE);
			pstmt.setInt(1, numVente);	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - suppressionEnchere - " + e.getMessage());
		}finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
		
		
		
		
	}

	public static Enchere rechercheMeilleureMontant(int numVente) throws DALException {
		// Recherche de la meilleure vente afin de MAJ prixVente de la table VENTE
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Enchere enchere = new Enchere();
		
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(RECHERCHER_MEILLEURE_VENTE);
			pstmt.setInt(1, numVente);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {	
				enchere.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				enchere.setPrixEnchere(rs.getInt("montantEnchere"));
				enchere.setNoVente(numVente);
			
			}
		} catch (SQLException e) {
			
			throw new DALException ("Probleme - Impossible de prendre un n° utilisateur ( EnchereDAO ) - " + e.getMessage());
		}finally{
			
			try {
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		return enchere;
		
	}
	}
	
}