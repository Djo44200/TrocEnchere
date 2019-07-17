package fr.eni.cours.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Categorie;
import fr.eni.cours.beans.Retrait;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.RetraitDAO;
import fr.eni.cours.dal.VenteDAO;

/**
 * Servlet permettant de gerer une nouvelle vente
 * @author jbodet2019
 */
@WebServlet("/ServNouvelleVente")
public class ServNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String rue;
		String cp;
		String ville;
		
		rue = (String) session.getAttribute("Rue");
		cp = (String)session.getAttribute("Cp");
		ville = (String)session.getAttribute("Ville");
		
		
		request.setAttribute("rue", rue);
		request.setAttribute("cp", cp);
		request.setAttribute("ville", ville);
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("sPublier"));	
		Vente nVente = null;
		String selectionner= "Selectionner"; 
		Categorie categorie = new Categorie();
		
		// R�cup�ration des donn�es de la nouvelle vente
		String vArticle = request.getParameter("sArticle");
		String vDescription = request.getParameter("sDescription");
		//ajout vro adresse photo
		String vPhoto = request.getParameter("sPhoto");
		String vPrix = request.getParameter("sPrix");
		String vDate = request.getParameter("sDate");
		String vAdresse = request.getParameter("sAdresse");
		String vCP = request.getParameter("sCP");
		String vVille = request.getParameter("sVille");
		String vCate = request.getParameter("sCate");
		HttpSession session = request.getSession();
		int numUtil = (int)session.getAttribute("numUtil");
		int numCat;
		LocalDate dateFin = LocalDate.parse(vDate);
		int prixInit = Integer.parseInt(vPrix);
		Retrait retrait;
		
		
		
		if(vArticle.isEmpty() || vArticle == null) {
			request.setAttribute("erreur", "Veuillez remplir le nom de l'article");
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
		}
		
		if(vDescription.isEmpty() || vDescription == null) {
			request.setAttribute("erreur", "Veuillez saisir une description");
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
		}
		
		if(vDate.isEmpty() || vDate == null) {
			request.setAttribute("erreur", "Veuillez saisir une date de fin d'enchère");
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
		}
		
		if(vCate.equals(selectionner)) {
			request.setAttribute("erreur", "Veuillez selectionner une catégorie");
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
		}
		

		
		try {
			categorie = VenteDAO.obtenirLeNumCate(vCate);
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		
		numCat = categorie.getNumCate();
		
		if (request.getParameter("sPublier")!=null) {
				
		nVente = new Vente(vArticle, vDescription, dateFin, prixInit, prixInit, numUtil, numCat, true, true);
		} else {
		nVente = new Vente(vArticle, vDescription, dateFin, prixInit, prixInit, numUtil, numCat, false, true);	
		}
		
		
		
		
		try {
			VenteDAO.ajouter(nVente);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		retrait = new Retrait(nVente.getNumVente(), vAdresse, vCP, vVille);
	
		
		
		
		try {
			RetraitDAO.ajouterRetrait(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("MessageAjoutVente", "Votre annonce vient d'etre publiee");
		
		response.sendRedirect("./ServPagePrincipale?successCreateVente=1");
		

	}

}
