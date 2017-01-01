<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ include file="navigation.html"%>
    <div id="wrapper">
        <div id="page-wrapper">
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="row">
                    <%@ include file="title-breadcrum.html"%>
                </div>
                <!-- /.row -->
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Uploaded Files</h3>
                            </div>
                            <div class="panel-body">
		                    <form action="./fileUpload" method="post" enctype="multipart/form-data">
							Select File to Upload:<input type="file" name="fileName">
							<br>
							<input type="submit" value="Upload">
							</form>
						</div>
					</div>
					</div>
                </div>
                <!-- /.row -->
               
               <div class="row">
                	<div class="col-lg-12">
                	 <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Uploaded Files</h3>
                            </div>
                            <div class="panel-body">
	                		<table class="table table-hover">
								<thead>
									<tr>
										<th>File Name</th>
										<th>Location</th>
										<th>Type</th>
			                		</tr>
								</thead>
	                			<tbody>
			                		<c:forEach var="fileLocatorItem" items="${ files }"
									varStatus="counter">
										<tr class="warning">
											<td>${ fileLocatorItem.getFileName() }</td>
											<td>${ fileLocatorItem.getFileUrl() }</td>
											<td>${ fileLocatorItem.getFileType() }</td>
											<td>
											<td>
												<form action="./convertFile" method="POST">
														<button
															onclick="return confirm('Ready to convert?')"
															class="btn btn-success" name="convertFile">Analyse</button>
														<input type="hidden" name="fileLocatorId"
															value="${ fileLocatorItem.getFileLocatorId() }" /> <input
															type="hidden" name="flag" value="apriori" />
												</form>
											</td>
											<td>
												<form action="./convertFile" method="POST">
													<button
														onclick="return confirm('Are you sure you want to delete?')"
														class="btn btn-danger" name="deleteFile">Delete</button>
													<input type="hidden" name="fileLocatorId"
														value="${ fileLocatorItem }" /> <input
														type="hidden" name="flag" value="delete" />
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
	                	</div>
                	  
                        </div>
                    </div>
                </div>
                
                <div class="row">
                	<div class="col-lg-12">
                	 <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Analysed Files</h3>
                            </div>
                            <div class="panel-body">
	                		<table class="table table-hover">
								<thead>
									<tr>
										<th>File Name</th>
										<th>Location</th>
										<th>Algorithm Used</th>
			                		</tr>
								</thead>
	                			<tbody>
			                		<c:forEach var="fileLocatorItem" items="${ convertedFiles }"
									varStatus="counter">
										<tr class="warning">
											<td>${ fileLocatorItem.getConvertedFileName() }</td>
											<td>${ fileLocatorItem.getConvertedFileUrl() }</td>
											<td>${ fileLocatorItem.getConvertAlgorithmUsed() }</td>
											<td><a
												href="./viewFile?id=${ fileLocatorItem.getConvertedFileId() }"
												class="btn btn-success">View</a></td>
											<td>
												<form action="./home" method="POST">
													<button
														onclick="return confirm('Are you sure you want to delete?')"
														class="btn btn-danger" name="deleteBooking">Delete</button>
													<input type="hidden" name="bookingId"
														value="${ bookingItem.getBookingId() }" /> <input
														type="hidden" name="flag" value="deleteBooking" />
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
	                	</div>
                	  
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!--/#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
