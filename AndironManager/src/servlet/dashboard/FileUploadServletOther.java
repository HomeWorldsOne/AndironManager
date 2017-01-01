package servlet.dashboard;

//Imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class FileUploadServletOther extends HttpServlet {

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
		
		//Create files for webpage
		List<FileLocator> files = FileLocatorDAO.selectAll();
		for(FileLocator f: files){
			System.out.println(f.getFileName());
		}
		//Request to response
		request.setAttribute("files", files);
		
		//Create files for webpage
		List<ConvertedFile> convertedFiles = ConvertedFileDAO.selectAll();
		
		//Request to response
		request.setAttribute("convertedFiles", convertedFiles);
		
		RequestDispatcher rd = request.getRequestDispatcher("WorkSpace/Dashboard/fileuploader.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
