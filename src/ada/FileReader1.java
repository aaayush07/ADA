package ada;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FileReader1
 */
@WebServlet("/FileReader1")
public class FileReader1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String saveFile="";
		String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
		DataInputStream in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength();
		byte dataBytes[] = new byte[formDataLength+100];
		int byteRead = 0;
		int totalBytesRead = 0;
		while (totalBytesRead < formDataLength) {
		byteRead = in.read(dataBytes, totalBytesRead,formDataLength);
		totalBytesRead += byteRead;
		}
		String file = new String(dataBytes);
		saveFile = file.substring(file.indexOf("filename=\"") + 10);
		saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
		request.setAttribute("saveFile",saveFile);
		int lastIndex = contentType.lastIndexOf("=");
		String boundary = contentType.substring(lastIndex + 1,contentType.length());
		int pos;
		pos = file.indexOf("filename=\"");
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		int boundaryLocation = file.indexOf(boundary, pos) - 4;
		int startPos = ((file.substring(0, pos)).getBytes()).length;
		int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
		saveFile="E:/aayush/softwares/apache-tomcat-8.5.23/webapps/upload/uploaded/"+saveFile;
		File ff = new File(saveFile);
		FileOutputStream fileOut = new FileOutputStream(ff);
		fileOut.write(dataBytes, startPos, (endPos - startPos));
		fileOut.flush();
		fileOut.close();
		session.setAttribute("file1", saveFile);
		response.sendRedirect("NewFile.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
