package webtier;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CambiaPopolazioneServlet
 */
@WebServlet("/CambiaPopolazioneServlet")
public class CambiaPopolazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaPopolazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Preleva il bean dell' utente */
		HttpSession session = request.getSession(true);
		UserBean user=(UserBean) session.getAttribute("currentSessionUser");
			
		/* e gli setta la nuova popolazione */
		String A = request.getParameter("idNuovaPopolazione");
		int idNuovaPopolazione=Integer.parseInt(A);	
		System.out.println(" Parsato in id: "+idNuovaPopolazione);
		user.setPopolazione(idNuovaPopolazione);
		
		/* rimettiamo il bean nella sessione e redirezioniamo l'utente */
		session.setAttribute("currentSessionUser", user);
		response.sendRedirect("guiGestionePopolazione.jsp");
	
}

}
