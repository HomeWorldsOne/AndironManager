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
										<th>Row</th>
										<th>Text</th>
			                		</tr>
								</thead>
	                			<tbody>
			                		<c:forEach var="lineItem" items="${ lines }"
									varStatus="counter">
										<tr class="warning">
											<td>${ lineItem }</td>
											<td>1</td>
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
