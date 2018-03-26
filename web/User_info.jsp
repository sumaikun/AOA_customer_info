<%-- 
    Document   : User_info.jsp
    Created on : 23-mar-2018, 8:41:02
    Author     : JVega
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <jsp:include page="layout/header.jsp" />
    <style>
	#home{
        background-color: white;   			
        background-size: 95% 85%;
    	background-repeat: no-repeat;  
    	background-position:center 75px;
    	background-attachment: fixed;
	}	
	#formbutton{
		display:none;
	}
	input::-webkit-calendar-picker-indicator {
	  display: none;/* remove default arrow */
	}
	.myarrow:after {
	    content: url(http://s25.postimg.org/6k40u5hcr/arrow.png);
	    margin-left: -20px; 
	    padding: .1em;
	    pointer-events:none;
	}
	
	#qid {
            padding: 10px 15px;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 20px;
          }
        
</style> 

         <div class="container">
         <div class="row">
             <div class="col-lg-12">             	                 
                 <div class="intro-text">
                    <div id="former"  class="col-lg-12 col-md-12 col-sm-12">
                        <div class="container-fluid" style="background: rgba(0,0,0,0);">
                            <div class="modal-dialog">
                              <div class="modal-content" >
                                 <div class="modal-header">
                                </div>
                                <div class="modal-body" >
                                    <div class="col-xs-3 col-xs-offset-5">
                                         <div id="loadbar" style="display: none;">
                                            <div class="blockG" id="rotateG_01"></div>
                                            <div class="blockG" id="rotateG_02"></div>
                                            <div class="blockG" id="rotateG_03"></div>
                                            <div class="blockG" id="rotateG_04"></div>
                                            <div class="blockG" id="rotateG_05"></div>
                                            <div class="blockG" id="rotateG_06"></div>
                                            <div class="blockG" id="rotateG_07"></div>
                                            <div class="blockG" id="rotateG_08"></div>
                                        </div>
                                    </div>
                                    <div id="formcontent-2">
                                        <h3 style="color:black !important;">¡Vamos a verificar tus datos!</h3>
                                        <div class="form-group">
                                                        <label class="form-control ">Ingrese su cedula</label>
                                                        <input type="number" name="validate-identificacion" class="form-control num">												
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-primary form-control" onclick="validate_cedula()">Consultar</button>
                                        </div>							       	  	  
                                    </div>
                                    <div id="formcontent-1" style="display:none;">
                                            <h3 style="color:black !important;" id="welcome-message">¡Bienvenido!</h3>
                                            <span style="color:black !important;">¡Por favor seleccione una de la siguientes opciones!</span>
                                        <div class="form-group">
                                            <button class="btn btn-danger form-control" onclick="update_data()">Actualizar mis datos</button>
                                        </div>
                                        <div class="form-group">
                                            <a href="Warranty_info"><button class="btn btn-success form-control" >Continuar mi proceso</button></a>
                                        </div>								       	  	  
                                    </div>
                                    <form action="user_data"  method="post" id="Client_form" >   								 
                                        <div class="quiz" id="quiz" data-toggle="buttons">
                                            <div id="formcontent0" style="display:none;">
                                                <div class="form-group">
                                                    <label class="form-control ">Tipo de identificación</label>
                                                    <select class="form-control" name="tipo_identificacion" required>
                                                        <option value="">Seleccione una opción</option>
                                                        <option value="CC">CEDULA DE CIUDADANIA</option>
                                                        <option value="CE">CEDULA DE EXTRANJERIA</option>
                                                        <option value="NIT">NIT</option>
                                                        <option value="PAS">PASAPORTE</option>
                                                        <option value="TI">TARJETA DE IDENTIDAD</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Numero de identificación</label>
                                                    <input type="number" name="identificacion" class="form-control num" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Lugar de expedición</label>
                                                    <input type="text" name="lugar_expedicion" class="form-control " required>
                                                </div>
                                            </div>
                                            <div id="formcontent1" style="display:none;">
                                                <div class="form-group">
                                                    <label class="form-control">Sexo</label>
                                                    <select class="form-control"  name="sexo" required>
                                                        <option value="">Selecciona</option>
                                                        <option value="M">Masculino</option>
                                                        <option value="F">Femenino</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Nombres</label>
                                                    <input type="text" name="nombres" class="form-control " required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Apellidos</label>
                                                    <input type="text" name="apellidos" class="form-control " required>
                                                </div>
                                            </div>
                                            <div id="formcontent2" style="display:none;">
                                                <div class="form-group">
                                                    <label class="form-control">Departamento</label>
                                                    <select  class="form-control" name="departamento" required>
                                                        <option>Selecciona</option>
                                                        <c:forEach items="${departamentos}" var="row">			
                                                           <option value="${row.get('departamento')}">${row.get('departamento')}</option>							
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Ciudad domicilio</label>
                                                        <div class="input-group">
                                                            <input class="form-control" id="ciudad"  name="ciudad" list="ciudades" disabled required>												
                                                            <span class="input-group-btn">
                                                                <button  data-toggle="modal" data-target="#myModalCiudad" id="btn-ciudad" class="btn btn-primary" disabled>Selecciona</button>
                                                            </span>
                                                        </div>        
                                                    <input type="hidden" name="codigo_ciudad" value="none">
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Dirección domicilio</label>
                                                    <input type="text" class="form-control "  name="dir_domicilio" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-control">Barrio</label>
                                                    <input type="text" class="form-control " name="barrio" required>
                                                </div>
                                            </div>
                                            <div id="formcontent3" style="display:none;">
                                                <div class="form-group">
                                                    <label class="form-control">Tel. oficina</label>
                                                    <input type="number" class="form-control num" name="tel_oficina">
                                                </div>
                                                <div class="form-group">
                                                        <label class="form-control">Tel. vivienda</label>
                                                        <input type="number" class="form-control num" name="tel_vivienda">
                                                </div>
                                                <div class="form-group">
                                                        <label class="form-control">Celular</label>
                                                        <input type="number" class="form-control num" name="celular" required>
                                                </div>
                                                <div class="form-group">
                                                        <label class="form-control">Email</label>
                                                        <input type="email" class="form-control kemail" name="correo" required>
                                                </div>
                                            </div>
                                        </div>	
                                        <div class="form-group">												
                                            <input type="submit" id="formbutton" class="btn btn-success" onclick="send_form()"  value="enviar y terminar">
                                        </div>				       	   	
                                    </form>						       	  
                                    </div>
                                    <div class="modal-footer text-muted" style="text-align:center;">								    	
                                        <button onclick="form_pos_backward()" class="arrow" style="background: rgba(0,0,0,0); border: none; display:none;"><h3><span class="label label-warning" id="qid">Anterior</span> </h3></button>
                                        <button onclick="form_pos_forward()" class="arrow" style="background: rgba(0,0,0,0); border: none; display:none;"><h3><span class="label label-warning" id="qid">Siguiente</span> </h3></button>							    	
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                 </div>                 
             </div>
         </div>
     </div>

     <!-- Modal ciudad -->
     <div id="myModalCiudad" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">      
            <div class="modal-body">
              <div id="table-ajax" >

              </div>      		
              <div style="text-align:center;">
                      <button data-dismiss="modal"  class="btn btn-lg btn-success btn-xs">Cerrar</button>        	
              </div>
            </div>      
          </div>

        </div>
    </div>                                                                                     
                                                                                   

    <jsp:include page="layout/footer.jsp" />
    
<script>                                                                                         
function validate_cedula()
{
	 if($("input[name='validate-identificacion']" ).val()!= "" & $("input[name='validate-identificacion']" ).val().length>=6)
	 { 
		$.post("get_user_info",{documento:$("input[name='validate-identificacion']" ).val()}, function(data){
			if(data!="")
				{					
					$.post("get_by_code",{codigo:data.ciudad},
							function(data){
						$("select[name='departamento']").val(data.departamento);
						$("input[name='ciudad']").val(data.nombre);
					});
					console.log("in");
					$("select[name='tipo_identificacion']").val(data.tipo_id);
					$("input[name='identificacion']").val(data.identificacion);
					$("input[name='lugar_expedicion']").val(data.lugar_expdoc);
					$("select[name='sexo']").val(data.sexo);
					$("input[name='nombres']").val(data.nombre);
					$("input[name='apellidos']").val(data.apellido);
					$("select[name='departamento']").val(data.departamento);
					$("input[name='codigo_ciudad']").val(data.ciudad);
					$("input[name='dir_domicilio']").val(data.direccion);
					$("input[name='barrio']").val(data.barrio);
					$("input[name='tel_oficina']").val(data.telefono_oficina);
					$("input[name='tel_vivienda']").val(data.telefono_casa);
					$("input[name='celular']").val(data.celular);
					$("input[name='correo']").val(data.email_e);
					
					$("#formcontent-2").hide();
					$("#formcontent-1").show();
					$("#welcome-message").append(" "+data.nombre+" "+data.apellido);
					$("#formcontent").show();
					$("input[name='identificacion']").attr('readonly', true);
					//$(".arrow").show();
				}
			else{
				alert("Bienvenido proceda a ingresar sus datos");
				$("input[name='identificacion']").val($("input[name='validate-identificacion']" ).val());
				$("#formcontent-2").hide();
				$("#formcontent0").show();
				$(".arrow").show();
				$("html, body").animate({ scrollTop:  164.75}, 600);
			}
		});
	 }
	 else{
		 alert("El valor de la cedula no puede ser vacio y debe ser mayor o igual a 6 digitos");
	 }
}

function update_data()
{
	$("html, body").animate({ scrollTop:  164.75}, 600);
	$("#formcontent-1").hide();
	$(".arrow").show();
	$("#formcontent0").show();
}
function go_to_docs()
{
	document.getElementById("Client_form").submit();
}


var pos = 0;



function form_pos_forward()
{
	if(pos < 3){
		$("#formcontent"+pos).hide();
		pos = pos +1;
		$("#formcontent"+pos).show();
	}
	if(pos==3)
	 {
		$("html, body").animate({ scrollTop:  215.75}, 600);
		$("#formbutton").show();
	 }
	else{
		$("#formbutton").hide();
	}
}

function form_pos_backward()
{
	if(pos != 0){
		$("#formcontent"+pos).hide();
		pos = pos -1;
		$("#formcontent"+pos).show();
		$("#formbutton").hide();
	}
        
        if(pos==0)
        {
            $("#formcontent-1").hide();
            $("#formcontent0").hide();
            $("#formcontent-2").show();
            
        }
	
}



function present_intro()
{
	$("#myModal").modal('show');
}

function validate2()
{
	$("#myModal2").modal('show');
}

$("select[name='departamento']" ).change(function() {
	$('#ciudades').empty();
	$('input[name="ciudad"]').val("");
	$.post("get_ciudades",{departamento:$("select[name='departamento']").val()}, function(data){
		$('#table-ajax').empty();
		table = '<table class="table table-condensed  table-bordered" id="tableselect"><thead><th>Codigo Dane</th><th>Nombre ciudad</th><th>Opción</th></thead><tbody id="table_ciudades"></tbody></table>';
		$('#table-ajax').append(table);
		$.each(data,function() {
            cityOption = "<tr><td>"+this.codigo+"</td><td>"+this.nombre+"<td><button class='btn btn-danger' onclick=select_ciudad('"+this.codigo+"','"+this.nombre+"')>Selecciona</button></td></tr>";
            $('#table_ciudades').append(cityOption);
        });
		$('#tableselect').DataTable({"language": {"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/Spanish.json"},"pageLength": 10});
		$("#btn-ciudad").prop("disabled", false);
	});
});

$("input[name='identificacion']" ).change(function() {
	if($("input[name='identificacion']" ).val()!= "" & $("input[name='identificacion']" ).val().length>=6)
	 { 
		$.post("get_user_info",{documento:$("input[name='identificacion']" ).val()}, function(data){
                        console.log(data);
			if(data!="")
				{
					
					$.post("get_by_code",{codigo:data.ciudad},
							function(data){
						$("select[name='departamento']").val(data.departamento);
						$("input[name='ciudad']").val(data.nombre);
					});
					console.log("in");
					$("select[name='tipo_identificacion']").val(data.tipo_id);
					$("input[name='identificacion']").val(data.identificacion);
					$("input[name='lugar_expedicion']").val(data.lugar_expdoc);
					$("select[name='sexo']").val(data.sexo);
					$("input[name='nombres']").val(data.nombre);
					$("input[name='apellidos']").val(data.apellido);
					$("select[name='departamento']").val(data.departamento);
					$("input[name='codigo_ciudad']").val(data.ciudad);
					$("input[name='dir_domicilio']").val(data.direccion);
					$("input[name='barrio']").val(data.barrio);
					$("input[name='tel_oficina']").val(data.telefono_oficina);
					$("input[name='tel_vivienda']").val(data.telefono_casa);
					$("input[name='celular']").val(data.celular);
					$("input[name='correo']").val(data.email_e);
				}
		});
	 }	
});

function select_ciudad(codigo,nombre)
{	
	$("#myModalCiudad").modal('hide');
	$("input[name='codigo_ciudad']").val(codigo);
	$("input[name='ciudad']").val(nombre)
}

/*function check_city()
{
	//console.log("checked");	
	var code = $("#ciudad").val();
	if(code != "")
	{
		console.log(code);
		var danecode = $("#ciudades [value='"+code+"']").data('value');
		if (danecode == undefined && $("input[name='codigo_ciudad']").val() == "none" ) {
		    alert("el nombre de la ciudad no es valido");
		    $("#ciudad").focus();
		}else{
			console.log(danecode);
			$("input[name='codigo_ciudad']").val(danecode);
			}
		
	}
	
}*/

function send_form()
{
	
	//alert("into class");
	//console.log("find "+$("select[name='tipo_identificacion']").val() );
	
	if($("select[name='tipo_identificacion']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent0").show();
		$("#formbutton").hide();
		alert("Debe seleccionar un tipo de identificación");
		pos = 0;
		return false;
	}
	
	if($("select[name='identificacion']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent0").show();
		$("#formbutton").hide();
		alert("ponga un numero de identificación");
		pos = 0;
		return false;
	}
	
	if($("input[name='lugar_expedicion']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent0").show();
		$("#formbutton").hide();
		alert("Ingrese el lugar de expedición de la cedula");
		pos = 0;
		return false;
	}	
	
	if($("select[name='sexo']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent1").show();
		$("#formbutton").hide();
		alert("Seleccione su sexo");
		pos = 1;
		return false;
	}
	
	if($("input[name='nombres']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent1").show();
		$("#formbutton").hide();
		alert("Escriba sus nombres");
		pos = 1;
		return false;
	}
	
	if($("input[name='apellidos']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent1").show();
		$("#formbutton").hide();
		alert("Escriba sus apellidos");
		pos = 1;
		return false;
	}
	
	/*if($("input[name='codigo_ciudad']").val() == "none")
	{
		alert("Ingrese la ciudad en la que vive");
		return false;
	}*/	
	
	if($("select[name='departamento']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent2").show();
		$("#formbutton").hide();
		alert("Ingrese el departamento donde vive");
		pos = 2;
		return false;
	}
	
	if($("input[name='ciudad']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent2").show();
		$("#formbutton").hide();
		alert("Ingrese la ciudad donde vive");
		pos = 2;
		return false;
	}
	
	if($("input[name='dir_domicilio']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent2").show();
		$("#formbutton").hide();
		alert("Ingrese su dirección de domicilio");
		pos = 2;
		return false;
	}
	
	if($("input[name='barrio']").val() == "")
	{
		$("#formcontent3").hide();
		$("#formcontent2").show();
		$("#formbutton").hide();
		alert("Ingrese el barrio donde vive");
		pos = 2;
		return false;
	}
	
	if($("input[name='celular']").val() == "")
	{
		alert("Ingrese un numero celular");
		pos = 3;
		return false;
	}
	
	if($("input[name='correo']").val() == "")
	{
		alert("Ingrese el correo electrónico");
		pos = 3;
		return false;
	}
	
	
	//document.getElementById("Client_form").submit();
	return true;
	//
	
}

</script>   