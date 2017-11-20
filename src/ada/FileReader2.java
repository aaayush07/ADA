package ada;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FileReader2
 */
@WebServlet("/FileReader2")
public class FileReader2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String saveFile1="";
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
		saveFile1 = file.substring(file.indexOf("filename=\"") + 10);
		saveFile1 = saveFile1.substring(0, saveFile1.indexOf("\n"));
		saveFile1 = saveFile1.substring(saveFile1.lastIndexOf("\\") + 1,saveFile1.indexOf("\""));
		request.setAttribute("saveFile1",saveFile1);
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
		saveFile1="E:/aayush/softwares/apache-tomcat-8.5.23/webapps/upload/uploaded/"+saveFile1;
		File ff = new File(saveFile1);
		FileOutputStream fileOut = new FileOutputStream(ff);
		fileOut.write(dataBytes, startPos, (endPos - startPos));
		fileOut.flush();
		fileOut.close();
		session.setAttribute("file2", saveFile1);
		System.out.println("File 2 read");
		response.sendRedirect("MainServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
