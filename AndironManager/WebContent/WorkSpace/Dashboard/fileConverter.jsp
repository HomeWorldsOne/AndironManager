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
                                <h3 class="panel-title">Convert Files</h3>
                            </div>
                            <div class="panel-body">
		                    <form action="./fileConvert" method="post" enctype="multipart/form-data">
							Select File to Upload:<input type="file" name="fileName">
							<br>
							<input type="submit" value="Upload">
							</form>
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
