<%-- 
    Document   : header
    Created on : 21-mar-2018, 8:50:02
    Author     : JVega
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Sistemas de clientes AOA</title>

  <!--Angular-->
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js" ></script>
  <script src="js/Modules/app.js"></script>
  <script src="js/Controllers/WaController.js"></script>
  <script src="js/Services/WaService.js"></script>




  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <!-- Loading gear -->
  <link href="css/gear.css" rel="stylesheet">
</head>

<style>
  .menu_l:hover{
    background-color: #0B610B !important;
  }
  .rotate90 {
    -webkit-transform: rotate(90deg);
    -moz-transform: rotate(90deg);
    -o-transform: rotate(90deg);
    -ms-transform: rotate(90deg);
    transform: rotate(90deg);
    margin-left: -0.7em !important;
    max-width: 10em !important;
    height: 2.6em !important;
   
    
  }

   .first_label_normal
  {
     /*margin-top: 1.4em !important;*/
  }

  .first_label_collapse
  {
    margin-top: 2em !important;
  }

  .normal_pos{
    max-width: 245px;
    height: 125px;
    margin-top: 15px;
  }

  .second_label_normal
  {
     /*margin-top: 1.4em !important;*/
  }

  .second_label_collapse
  {
     /*margin-top: 10em !important;
     height: 75px !important;*/
  }
  
  .text-center
  {
      text-align: center;
  }

</style>



<body class="fixed-nav sticky-footer bg-dark" id="page-top" style="background-color: white !important;">
  <!-- Navigation-->
  <nav style="background-color: #0B610B!important;" class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.jsp">AOA administración operativa Automotriz</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
    
      <ul class="navbar-nav ml-auto">
     
         <span class="pull-right" style="margin-top: 0.4em; color:white">        
         </span>
        <c:if test="${ idsiniestro != null }">
            <li class="nav-item">          
              <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                <i class="fa fa-fw fa-sign-out"></i>Cerrar sesión</a>
            </li>
        </c:if>
      </ul>
        
    </div>
  
  </nav>
 
<c:if test="${ error_message != null }">
    <div class="alert alert-warning alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Lo sentimos ${ error_message  } por favor comuniquese al 018000186262 o en bogota al 8837069</strong>  
    </div>
</c:if>