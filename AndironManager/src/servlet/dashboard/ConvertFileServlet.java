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

import algorithms.apriori.AlgoApriori;


public class ConvertFileServlet extends HttpServlet {

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
		
		//Nothing currently happens
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//We need to take an original file and convert it with the selected algorithm....
		
		//Gather the forms variables
		String fileLocatorId = request.getParameter("fileLocatorId");
		String flag = request.getParameter("flag");
		
		//Construct the Original FileLocator class
		FileLocator fileLocator = FileLocatorDAO.selectById(Integer.parseInt(fileLocatorId));
		
		//Create neccessary values
		String input = fileLocator.getFileUrl();
		String fileName = fileLocator.getFileName();
		String outputName = "output" + fileName;
		String algorithmUsed ="";
		//Conduct appropriate mesure
		if(flag.equals("apriori")) {
			AlgoApriori apriori = new AlgoApriori();
			apriori.runAlgorithm(0.4, input, outputName);
			apriori.printStats();
			algorithmUsed = "Apriori";
		}
		
		//This removes the null from the directory
		String path = request.getServletContext().getAttribute("FILES_DIR") + "Files\\" + outputName;
		String pathFixed = path.replace("null", "");
		
		//Create file
		File file = new File(pathFixed);
		String absolutePath = file.getAbsolutePath();
		file = null;
		
		//Create pointer for file
		ConvertedFile convertedFile = new ConvertedFile();
		
		convertedFile.setConvertAlgorithmUsed(algorithmUsed);
		convertedFile.setConvertedFileName(outputName);
		convertedFile.setConvertedFileUrl(absolutePath);
		convertedFile.setOriginalFileId(Integer.parseInt(fileLocatorId));
		
		ConvertedFileDAO.add(convertedFile);
		
		//Retunr to the file page "Analyze files"
		RequestDispatcher rd = request.getRequestDispatcher("./fileUploadReturn");
		rd.forward(request, response);
	}

}
