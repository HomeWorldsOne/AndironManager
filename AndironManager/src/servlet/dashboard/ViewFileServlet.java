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
import algorithms.apriori.AprioriDAO;
import algorithms.apriori.AprioriDTO;
import algorithms.converters.*;

public class ViewFileServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Get the id
		String outputIdString = request.getParameter("id");
		Output output = OutputDAO.selectById(Integer.parseInt(outputIdString));
		
		//generate all entries
		List<AprioriDTO> entries = AprioriDAO.selectAllByInputId(output.getOutputId());
		
		//push to object value
		request.setAttribute("entries", entries);
		
		//Initiate request
		RequestDispatcher rd = request.getRequestDispatcher("workspace/home/dashboard/fileview.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//Return to page
		doGet(request, response);
	}
	
	

}