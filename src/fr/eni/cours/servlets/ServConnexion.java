package fr.eni.cours.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServConnexion
 * 
 * @author jbodet2019
 */
@WebServlet("/index.html")
public class ServConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("successCreateUser") != null
				&& request.getParameter("successCreateUser").equals("1")) {

			request.setAttribute("MessageAjoutUser", "Votre compte vient d'être créé.");
		}
		if (request.getParameter("error") != null) {

			request.setAttribute("error", null);
		}
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vLogin = request.getParameter("sPseudo");
		String vMdp = request.getParameter("sMdp");
		String vMail = request.getParameter("sPseudo");

		UtilisateurDAO vUtilisateurDAO = new UtilisateurDAO();
		// ajout vro
		HttpSession session = request.getSession();
		// fin ajout

		if (vLogin != null || vMail != null && vMdp != null) {
			Utilisateur vUtilisateur = null;

			try {
				vUtilisateur = vUtilisateurDAO.verification(vLogin, vMdp, vMail);

			} catch (DALException e) {

				e.printStackTrace();

			}
			// Ajout des informations utilisateur sur son profil

			session.setAttribute("Pseudo", vUtilisateur.getPseudo());
			session.setAttribute("Nom", vUtilisateur.getNom());
			session.setAttribute("Prenom", vUtilisateur.getPrenom());
			session.setAttribute("Mail", vUtilisateur.getMail());
			session.setAttribute("Telephone", vUtilisateur.getTelephone());
			session.setAttribute("Rue", vUtilisateur.getRue());
			session.setAttribute("Cp", vUtilisateur.getCp());
			session.setAttribute("Ville", vUtilisateur.getVille());
			session.setAttribute("Mdp", vUtilisateur.getMdp());
			session.setAttribute("Credit", vUtilisateur.getCredit());
			session.setAttribute("numUtil", vUtilisateur.getNumUtil());

			if (vUtilisateur != null && vUtilisateur.getPseudo() != null && vUtilisateur.getPseudo().equals(vLogin)
					&& vUtilisateur.getMdp() != null && vUtilisateur.getMdp().equals(vMdp)
					|| vUtilisateur != null && vUtilisateur.getMail() != null && vUtilisateur.getMail().equals(vMail)
							&& vUtilisateur.getMdp() != null && vUtilisateur.getMdp().equals(vMdp)) {

				request.getSession().setAttribute("UserConnect", vUtilisateur);
				// ajout vro
				// response.sendRedirect("/WEB-INF/jsp/PagePrincipale.jsp");
				response.sendRedirect("./ServPagePrincipale");

				session.setAttribute("sessionUtilisateur", vUtilisateur);
				// fin ajout

			} else {

				request.setAttribute("error", "Mot de passe ou identifiant incorrect");
				// ajout vro
				session.setAttribute("sessionUtilisateur", null);
				// fin ajout

				doGet(request, response);

			}

		} else {
			// ajout vro
			session.setAttribute("sessionUtilisateur", null);
			// fin ajout
		}

	}
}
