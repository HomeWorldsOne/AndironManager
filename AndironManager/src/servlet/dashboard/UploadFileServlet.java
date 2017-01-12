package servlet.dashboard;

//Imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
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

import dto.*;
import dao.*;
import organisers.*;
import algorithms.converters.*;

public class UploadFileServlet extends HttpServlet {
	private static final String SAVE_DIR = "uploadFiles";
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
		List<Input> inputs = ConvertList.getAllInputFiles(ProjectInputDAO.selectAllByProjectId(projectId));
		request.setAttribute("inputs", inputs);
		
		//Initiate request
		RequestDispatcher rd = request.getRequestDispatcher("workspace/home/dashboard/fileupload.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}

		
		try {
			
			//Lists
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			
			//Step through form data
			while (fileItemsIterator.hasNext()) {
				
				//Create File Item
				// gets absolute path of the web application
		        String appPath = request.getServletContext().getRealPath("/");
		        String newPath = appPath.replace(".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/", "");
		        // constructs path of the directory to save uploaded file
		        String savePath = newPath + File.separator + SAVE_DIR;
		         
		        // creates the save directory if it does not exists
		        File fileSaveDir = new File(savePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdir();
		        }
		        
		        FileItem fileItem = fileItemsIterator.next();
		        if(!fileItem.getName().equals(null)){
			        String fileName = fileItem.getName();
			        System.out.println(fileName);
			        fileItem.write(new File(fileSaveDir + File.separator + fileItem.getName()));
					
					//Make the input
					Input input = new Input();
					input.setDataName(fileName);
					input.setFileUrl(fileSaveDir + fileItem.getName());
					int rowId = InputDAO.add(input);
					
					//Get the project Id
					int projectId = 1;
				
					//Create a project - input locator
					ProjectInput projectInput = new ProjectInput();
					projectInput.setInputId(rowId);
					projectInput.setProjectId(projectId);
					ProjectInputDAO.add(projectInput);
		        }
				
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
	
	

}