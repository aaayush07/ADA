package ada;

import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //logic to read multiple file and save them
		HttpSession session = request.getSession(false);
		String saveFile1 = (String)session.getAttribute("file1");
		String saveFile2 = (String)session.getAttribute("file2");
		System.out.println(saveFile1);
		System.out.println(saveFile2);
	//save the file names with locations
		double Result = new Logic().computeResult(saveFile1,saveFile2);
		session.setAttribute("Result",Result);
		System.out.println(Result);
		if(saveFile1 !=saveFile2) {
		new Logic().filedelete(saveFile1);
		new Logic().filedelete(saveFile2);}
		else 
		new Logic().filedelete(saveFile1);
		session.setAttribute("Result", Result);
		response.sendRedirect("NewFile.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
