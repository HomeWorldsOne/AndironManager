package servlet.dashboard;

//Imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
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

import dto.*;
import dao.*;
import organisers.*;
import algorithms.converters.*;

public class AnalyzeFileServlet extends HttpServlet {
	private static final String SAVE_DIR = "analyzedFiles";
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
		
		//Get the project ID
		int projectId = 1;
		
		//Find all the inputs
		List<Input> inputs = ConvertList.getAllInputFiles(
				ProjectInputDAO.selectAllByProjectId(projectId)
				);
		request.setAttribute("inputs", inputs);
		
		//Find all the outputs
		List<Output> outputs = ConvertList.getAllOutputFiles(
				ProjectOutputDAO.selectAllByProjectId(projectId)
				);
		request.setAttribute("outputs", outputs);
		
		
		
		//Initiate request
		RequestDispatcher rd = request.getRequestDispatcher("workspace/home/dashboard/fileanalyze.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Project variables
		int projectId = 1;
		
		//Respond to form -- Analyze form
		String method = request.getParameter("method");
		String inputId = request.getParameter("inputFile");
		int inputIdInt = Integer.parseInt(inputId);
		Input input = InputDAO.selectById(inputIdInt);
		String inputLocation = input.getFileUrl();
		
		//Create the output location
		String appPath = request.getServletContext().getRealPath("/");
        String newPath = appPath.replace(".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/", "");
        String outputLocation = newPath + File.separator + SAVE_DIR;
        File fileSaveDir = new File(outputLocation);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        //Output filename is the equivalent of the project, the algorithm and the inputFileId
        
        String outputFileLocation = outputLocation + File.separator + projectId + input.getInputId() + ".txt";
		
		//Analyze
		AlgorithmOrganiser ao = new AlgorithmOrganiser();
		ao.selector(Integer.parseInt(method), inputLocation, outputFileLocation);
		
		//Create the output locator
		Date date = new Date();
		String dateString = date.toString();
		
		Output output = new Output();
		output.setDateMade(dateString);
		output.setOutputAlgorithm(method);
		output.setOutputFileName(method + input.getDataName());
		output.setOutputFileUrl(outputLocation);
		int outputId = OutputDAO.add(output);
		
		//Insert into the database
		ao.createAprioriDtos(outputFileLocation, outputId);
		
		//create the project output
		ProjectOutput projectOutput = new ProjectOutput();
		projectOutput.setOutputId(outputId);
		projectOutput.setProjectId(1);
		ProjectOutputDAO.add(projectOutput);
		
		//Return to page
		doGet(request, response);
	}
	
	

}