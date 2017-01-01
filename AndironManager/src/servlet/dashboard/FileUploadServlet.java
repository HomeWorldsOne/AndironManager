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

public class FileUploadServlet extends HttpServlet {

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
		String name = "Analyse Files";
		//Request to response
		request.setAttribute("convertedFiles", convertedFiles);
		request.setAttribute("names", name);
		
		//intitiate request
		RequestDispatcher rd = request.getRequestDispatcher("WorkSpace/Dashboard/fileuploader.jsp");
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
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName=" + fileItem.getFieldName());
				System.out.println("FileName=" + fileItem.getName());
				System.out.println("ContentType=" + fileItem.getContentType());
				System.out.println("Size in bytes=" + fileItem.getSize());

				//This code removes null being added to the path directory.
				String path = request.getServletContext().getAttribute("FILES_DIR") + "Files\\" + fileItem.getName();
				String pathFixed = path.replace("null", "");
				
				File file = new File(pathFixed);
				fileItem.write(file);
				
				//Create the FileLocator object
				FileLocator fileLocator = new FileLocator();
				fileLocator.setFileName(fileItem.getName());
				fileLocator.setFileUrl(file.getAbsolutePath());
				fileLocator.setFileType("Standard");
				fileLocator.setFolderName("To Be Done");
				
				FileLocatorDAO.addOrUpdate(fileLocator);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
