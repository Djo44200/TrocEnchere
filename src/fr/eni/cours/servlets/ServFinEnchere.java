package fr.eni.cours.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.cours.beans.Enchere;
import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.EnchereDAO;
import fr.eni.cours.dal.UtilisateurDAO;
import fr.eni.cours.dal.VenteDAO;

/**
 * Servlet implementation class ServFinEnchere
 */
@WebServlet("/ServFinEnchere")
public class ServFinEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String PrixVente = request.getParameter("PrixVente");
		int prixVendu = Integer.parseInt(request.getParameter("PrixVente"));
		request.setAttribute("PrixVente", PrixVente);
		int numVente = Integer.parseInt(request.getParameter("NumVente"));
		Vente vente = new Vente();
		List<Enchere> listeEnchere= new ArrayList();
		int montant = 0; //a utiliser pour obtenir le montant de chaque enchère à rentrer en méthode pour recréditer
		int utilEnchere = 0; //a utiliser pour le nomUtil pour le recréditer du montant ou débiter le gagnant.
		Utilisateur utilisateur = new Utilisateur(); //a utiliser pour le recréditer
		int numVendeur;		
		String pseudoGagnant = request.getParameter("PseudoVente"); // On récupère le pseudo de celui qui a gagné
		
		//On obtient l'objet vente completement chargé
		try {
			vente = VenteDAO.AfficherArticle(numVente);
			listeEnchere = EnchereDAO.listeEncheres(numVente);
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		
		
		//on recrédite tous les ceux qui ont fait des enchères même le winner
		for (Enchere enchere : listeEnchere) {
			utilEnchere = enchere.getNoUtilisateur();
			
			try {
				utilisateur = UtilisateurDAO.obtenirUnUtil(utilEnchere); // on obtient l'utilisateur chargé
				montant = enchere.getMontantEnchere() + utilisateur.getCredit(); // pour obtenir le montant à créditer + ce qu'il a déjà
				UtilisateurDAO.modifierCredit(montant, utilEnchere); // méthode pour modifier le crédit en BD
			} catch (DALException e1) {
				
				e1.printStackTrace();
			}
			
		}
		// on file ses sous au vendeur
		
		
		numVendeur = vente.getNumUtil();
		
		try {
			utilisateur = UtilisateurDAO.obtenirUnUtil(numVendeur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		montant = utilisateur.getCredit() + prixVendu;
		
		// on refait exactement la même chose pour le vendeur
		try {
			UtilisateurDAO.modifierCredit(montant, numVendeur);
			//on débite une fois pour toute le gagnant
			utilisateur = UtilisateurDAO.obtenirUnUtilpseudo(pseudoGagnant);
		} catch (DALException e) {
			e.printStackTrace();
		} 
		

		
		
		
		montant = utilisateur.getCredit() - prixVendu;
		utilEnchere = utilisateur.getNumUtil();
		
		try {
			UtilisateurDAO.modifierCredit(montant, utilEnchere);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//si jamais le vendeur est le même que celui qui a gagné l'enchère (car personne n'a enchérit) on le redébite du montant.
		
		if(utilEnchere == numVendeur) {
			
			montant = utilisateur.getCredit() - prixVendu;
			try {
				UtilisateurDAO.modifierCredit(montant, utilEnchere);
				
				//Finir par un set no_utilisateur -1 pour empecher d'autre retrait(facile a faire directement sur l'obbjet vente avec un setnumutil
				VenteDAO.finirVente(numVente);

			} catch (DALException e) {
				e.printStackTrace();
			}
	
		}
		
		
		request.setAttribute("VenteCloturee1", "La vente a bien été clôturée");
		request.setAttribute("VenteCloturee2", "Votre compte vient d'être crédité.");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/finEnchere.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
	}

}
