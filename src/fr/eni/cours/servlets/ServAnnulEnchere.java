package fr.eni.cours.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Enchere;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.EnchereDAO;
import fr.eni.cours.dal.RetraitDAO;
import fr.eni.cours.dal.UtilisateurDAO;
import fr.eni.cours.dal.VenteDAO;

/**
 * @author jbodet2019
 * Servlet implementation class ServAnnulEnchere
 */
@WebServlet("/ServAnnulEnchere")
public class ServAnnulEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Méthode pour annuler une enchère
		int numVente =	Integer.parseInt(request.getParameter("numVente").trim());
		HttpSession session = request.getSession();
		int numUtil = (int) session.getAttribute("numUtil");
		Enchere verifEnchere = null;
		Enchere meilleureMontant = null;
		Vente vente = null;
		int montantEnchere = 0;
		int nbrCreditUtil = (int) session.getAttribute("Credit");
		int ajoutCredit=0 ;
		
		
		try {
			verifEnchere = EnchereDAO.verificationUserEncherir(numVente, numUtil);
			montantEnchere = verifEnchere.getPrixEnchere();
			ajoutCredit =  montantEnchere + nbrCreditUtil ;
			UtilisateurDAO.modifierCredit(ajoutCredit, numUtil);
			
			//Suppression d'une enchère
			EnchereDAO.suppressionEnchere(numUtil, numVente);
			
			// Récupère le numUtil et le montantEnchere pour MAJ la BDD
			meilleureMontant = EnchereDAO.rechercheMeilleureMontant(numVente);
			
			if (meilleureMontant.getPrixEnchere()==0) {
				// MAJ de la table Vente
			vente = VenteDAO.AfficherArticle(numVente);	
			VenteDAO.initPrixVente(vente.getPrixInitial(),numVente);
				
			}else {
				// MAJ de la table Vente 
				VenteDAO.initPrixVente(meilleureMontant.getPrixEnchere(),numVente);
			}
			
			
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("SuppressionEnchère", "Votre enchère vient d'être supprimée");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/PagePrincipale.jsp").forward(request, response);
	}

}
