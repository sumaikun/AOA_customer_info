<%-- 
    Document   : login
    Created on : 14-mar-2018, 12:52:42
    Author     : JVega
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>




  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" type="image/png" href="http://www.aoacolombia.com/assets/img/logo_footer.png" />
  <title>SACAP AOA</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <script src='https://www.google.com/recaptcha/api.js'></script>



  <style>
    .btn:hover{
      background-color: #0B610B!important;
    }
    .log_btn{
       background-color: #0A0A2A !important; border-color: #0A0A2A; color:white;
    }
   
  </style>
<div style="background-color: white;">
  <div class="container" >

      <br>
      <br>
      <div class="text-center">
        <image src="http://www.aoacolombia.com/assets/img/logo.png" class="img-responsive center-block">
      </image>
        <br>
        <br>
      <div class="card card-login mx-auto mt-5">
          <div class="card-header" style="background-color: #0B610B!important;"><strong>Captura de información de garantia</strong></div>
        <div class="card-body" style="background-color: #97af00!important;">

          <script src="https://www.google.com/recaptcha/api.js" async defer></script>
           <script>
             function onSubmit(token) {
               document.getElementById("login_form").submit();
             }
           </script>


          <form method="post" id="login_form" action="login">
            <div class="form-group">
              <label for="exampleInputEmail1">Placa de Vehiculo</label>
              <input style=" text-align: center !important;" class="form-control" id="exampleInputEmail1" type="text" autocomplete="off" aria-describedby="emailHelp" placeholder="JVX766" name='placa' maxlength="40" requried>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Telefono registrado</label>
              <input style=" text-align: center !important;" class="form-control" id="exampleInputPassword1" name="celphone" type="password" placeholder="3004775262" required>
            </div>

             <button class="btn log_btn  btn-block"  type="submit">Entrar</button>

          </form>

        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  </div>
</div>
  

