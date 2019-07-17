package fr.eni.cours.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Enchere;
import fr.eni.cours.beans.Retrait;
import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.EnchereDAO;
import fr.eni.cours.dal.RetraitDAO;
import fr.eni.cours.dal.UtilisateurDAO;
import fr.eni.cours.dal.VenteDAO;

/**
 * Servlet implementation class ServDetailVente
 * 
 * @author jbodet2019
 * 
 */
@WebServlet("/ServDetailVente")
public class ServDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		
		int numVente = Integer.parseInt(request.getParameter("numVente"));
		Vente vente = null; 
		Vente NumUtilVente = null; 
		Retrait retrait = null;
		String utilisateur = null;
		Enchere utilisateurAfficheBouton = null;
		String utilisateurEnchere = null;
		int numVendeur;
		Enchere enchere = null;
		Utilisateur user = new Utilisateur();
		String nomVendeur = null;
		
		LocalDateTime dateToday = LocalDateTime.now();
		String dateEnString; //Permet d'utiliser la requete sql pour aller chercher la dateFinEnchere
		dateEnString = dateToday.toString(); // donc là on a la date du jour en string
		Vente laVente = null; // Objet vente pour y récupérer les méthodes get pour notamment le getdate
		
		
		//méthode pour obtenir l'objet laVente chargé avec la dateFinEnchère
		try {
			laVente = VenteDAO.obtenirDateFinEnchere(numVente);
		} catch (DALException e1) {
			
			e1.printStackTrace();
		}
		
		LocalDate dateFinEnchere = laVente.getDateFinEnchere();
		LocalDateTime dateFinEnchere2 = dateFinEnchere.atStartOfDay(); // Necessaire pour formater du LocalDate en LocalDateTime, et on prend début du jour parceque 
		
		
	
	
		//Appel methode
		try {
			vente = VenteDAO.AfficherArticle(numVente);
			
			
		} catch (DALException e) {
			e.printStackTrace();
		}

		// Récupération des attibuts de la BDD


		request.setAttribute("DateFinEnchere", vente.getDateFinEnchere());
		request.setAttribute("Description", vente.getDescription());
		request.setAttribute("NomArticle", vente.getNomArticle());
		request.setAttribute("NomCate", vente.getNumCate());
		request.setAttribute("NumVente", numVente);
		request.setAttribute("PrixInitial", vente.getPrixInitial());
		request.setAttribute("PrixVente", vente.getPrixvente());
		request.setAttribute("NomUtil", vente.getUser());
		request.setAttribute("NumUtil", vente.getNumUtil());
		request.setAttribute("finEnchere", vente.getFinEnchere());
		
		int numVend = 0;
		
		
		
		try {
			numVend = VenteDAO.obtenirLeNumVendeur(numVente);
		} catch (DALException e2) {
		
			e2.printStackTrace();
		}
		
		
		
		
		
		
		//obtenir le nom du vendeur
		try {
			nomVendeur = UtilisateurDAO.rechercheUnPseudo(numVend);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		request.setAttribute("nomVendeur", nomVendeur);
		
		
		
		
		// Récupération des attibuts de la BDD de la table Retrait
		try {
			retrait = RetraitDAO.AfficherRetrait(numVente);
		} catch (SQLException | DALException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("CodePostal", retrait.getCodePostal());
		//request.setAttribute("Description", retrait.getNoVente());
		request.setAttribute("NomRueRetrait", retrait.getRue());
		request.setAttribute("NomVilleRetrait", retrait.getVille());
		
		
		
		// Récupération du no_utilisateur se trouvant dans les enchères
		
				try {
					enchere = EnchereDAO.rechercheUnUserEncheri(numVente);
					//request.setAttribute("NumUtilEnchere", enchere.getNoUtilisateur());
					utilisateurEnchere = UtilisateurDAO.rechercheUnPseudo(enchere.getNoUtilisateur());
					request.setAttribute("pseudoEnchere", utilisateurEnchere);
					
				} catch (DALException e) {
					
					e.printStackTrace();
				}
		
			
		//r�cup�ration session
		HttpSession session = request.getSession();
		user = (Utilisateur) session.getAttribute("sessionUtilisateur");
		
		
		// Récupérer le Pseudo de la personne qui fait la vente 
		try {
			
			
			numVendeur = VenteDAO.obtenirLeNumVendeur(numVente);
			utilisateur = UtilisateurDAO.rechercheUnPseudo(numVendeur);
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("pseudoVente", utilisateur);
		
		
		
		
		
		if(dateToday.isAfter(dateFinEnchere2)) {
			int numUser = user.getNumUtil();
			
			
			request.setAttribute("NumUser", numUser);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/detailVenteFinEnchere.jsp").forward(request, response);
			
		}
		
		
		// Si la dateFinEnchere n'est pas dépassée :
		else {
			
		// Vérification que l'utilisateur ait déjà enchéri sur une vente ( Pour afficher le bouton "Annuler enchère")
		
		try {
			utilisateurAfficheBouton = EnchereDAO.verificationUserEncherir(numVente, user.getNumUtil());
			
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		
		// Permet de tester le montant enchère afin d'afficher le bouton si l'utilisateur à déjà enchéri
		request.setAttribute("TestPresence", utilisateurAfficheBouton.getPrixEnchere());
		
		
		
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
		
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
