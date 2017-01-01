package servlet.dashboard;

import java.io.BufferedReader;
//Imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.FileLocator;
import dto.ConvertedFile;

import dao.FileLocatorDAO;
import dao.ConvertedFileDAO;

public class ViewFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Create file to display
		String convertFileId = request.getParameter("id");
		ConvertedFile convertFile = ConvertedFileDAO.selectById(Integer.parseInt(convertFileId));
		
		List<String> lines = new ArrayList<String>();
		
		// Open the file
		FileInputStream fstream = new FileInputStream(convertFile.getConvertedFileUrl());
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println (strLine);
		  lines.add(strLine);
		}

		//Close the input stream
		br.close();
		
		String home = "Analyse Files";
		String currentPage = "View File";
		
		List<String> names = new ArrayList<String>();
		names.add(home);
		names.add(currentPage);
		
		request.setAttribute("names", names);
		request.setAttribute("lines", lines);
		RequestDispatcher rd = request.getRequestDispatcher("WorkSpace/Dashboard/viewfile.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
