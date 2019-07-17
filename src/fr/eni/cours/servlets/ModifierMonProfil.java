package fr.eni.cours.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.ant.SessionsTask;

import fr.eni.cours.beans.Utilisateur;

import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.UtilisateurDAO;

/**
 *@author jmasters2019
 */

/**
 * Servlet implementation class ModifierMonProfil
 */
@WebServlet("/ModifierMonProfil")
public class ModifierMonProfil extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public ModifierMonProfil() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String choix = request.getParameter("choix");
		request.setAttribute("choix", choix);
		if (choix.equals("supprimer")) {
			request.setAttribute("suppression", "Votre compte vient d'être supprimé");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
		} else

		if (choix.equals("valider")) {
			request.setAttribute("ModificationOK", "Votre profil vient d'être modifié");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/PagePrincipale.jsp").forward(request, response);

		} else {

			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
		}

		PrintWriter out = null;
		Utilisateur utilisateur = new Utilisateur();
		// récupération des paramètres

		String vLogin = request.getParameter("sPseudo");
		String vNom = request.getParameter("sNom");
		String vPrenom = request.getParameter("sPrenom");
		String vMail = request.getParameter("sEmail");
		String vTelephone = request.getParameter("sTelephone");
		String vRue = request.getParameter("sRue");
		String vCP = request.getParameter("sCp");
		String vVille = request.getParameter("sVille");
		String vMdp = request.getParameter("sMdp");
		HttpSession session = request.getSession();

		// Ajout en BDD

		try {
			UtilisateurDAO.modifier(vLogin, vNom, vPrenom, vMail, vTelephone, vRue, vCP, vVille, vMdp,
					(int) session.getAttribute("numUtil"));
		} catch (DALException e) {
		}

	}

}
