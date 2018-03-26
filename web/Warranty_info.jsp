<%-- 
    Document   : warranty_info
    Created on : 25-mar-2018, 21:44:56
    Author     : JVega
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />

<div class="container" ng-app="Appi" ng-controller="WaController" >
    <c:if test="${ Siniestro.get('no_garantia') == 0 }">
        <div class="row">
            <div class="col-lg-12">                 
                <div class="intro-text">     
                        <hr class="star-light">  
                    <span class="name" ><h2><font color="#424242">Tipos de garantia</font></h2></span>
                        <hr class="star-light">                	 
                </div>
            </div>
            <c:if test="${ Aseguradora.get('garantia_consignada') > 0 }">
                <div  ng-init ="garantia_consignada()" class="col-lg-4 col-md-4"><button data-toggle="modal" data-target="#myModal" class="btn btn-primary btn-lg">Tarjeta de crédito</button></div>
            </c:if>
            <c:if test="${ Aseguradora.get('valor_no_reembols') > 0 }">    
                <div ng-init="valor_no_reembol()" class="col-lg-4 col-md-4"><button data-toggle="modal" data-target="#myModal2" class="btn btn-primary btn-lg">Consignación en efectivo</button></div>
            </c:if>
            <c:if test="${ Aseguradora.get('garantia') > 0 }">    
                <div ng-init="garantia_credito()" class="col-lg-4 col-md-4"><button data-toggle="modal" data-target="#myModal3" class="btn btn-primary btn-lg">Todo riesgo no reembolsable</button></div>       
            </c:if>    
        </div>
    </c:if>
    <div class="row">
        <div class="col-lg-12">                 
            <div class="intro-text">
                    <hr class="star-light">  
                <span class="name" ><h2><font color="#424242">Datos financieros para devoluciones</font></h2></span>
                    <hr class="star-light">                	 
            </div>
        </div>
        <div class="col-lg-12 col-md-12">
            <form class="form-horizontal" role="form">
                <fieldset>
                <legend></legend> 
                <div class="form-group">
                  <label class="col-sm-3 control-label" for="card-holder-name">Número de cuenta bancaria</label>
                  <div class="col-sm-9">
                      <input type="number" ng-model="warranty.devol_cuenta_bancaria"  class="form-control num" name="devol_cuenta_bancaria" id="card-holder-name" placeholder="Número de cuenta sin separadores">
                  </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="card-number">Tipo de cuenta</label>
                    <div class="col-sm-9">
                      <select name="devol_tipo_cuenta" ng-model="warranty.devol_tipo_cuenta" class="form-control" id="tipo">
                            <option value="">Selecciona</option>
                            <option value="A">Ahoros</option>
                            <option value="C">Corriente</option>
                     </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="card-number">Banco</label>
                        <div class="col-sm-9">
                            <select name="devol_banco" ng-model="warranty.devol_banco" class="form-control" id="devol_banco" style=""><option value="">Seleccione una opción</option>
                                <option value="1">ABN AMRO</option>
                                <option value="3">AGRARIO</option>
                                <option value="4">AV VILLAS</option>
                                <option value="2">BANCAFE (GRANBANCO)</option>
                                <option value="97">BANCO MUNDO MUJER</option>
                                <option value="5">BANCOLOMBIA</option>
                                <option value="15">BANCOOMEVA</option>
                                <option value="6">BANITSMO (HSBC)</option>
                                <option value="7">BANK BOSTON</option>
                                <option value="8">BBVA</option>
                                <option value="9">BOGOTA</option>
                                <option value="10">CAJA SOCIAL</option>
                                <option value="11">CITIBANK</option>
                                <option value="12">COLMENA</option>
                                <option value="13">COLPATRIA</option>
                                <option value="14">CONFIAR</option>
                                <option value="89">CORPBANCA</option>
                                <option value="17">DAVIVIENDA</option>
                                <option value="65">FALABELLA</option>
                                <option value="22">GNB SUDAMERIS</option>
                                <option value="16">HELM BANK (CREDITO)</option>
                                <option value="90">JURISCOOP</option>
                                <option value="94">MACROFINANCIERA</option>
                                <option value="18">MEGABANCO</option>
                                <option value="19">OCCIDENTE</option>
                                <option value="82">PICHINCHA</option>
                                <option value="20">POPULAR</option>
                                <option value="21">SANTANDER</option>
                                <option value="24">THE ROYAL BANK OF SCOTLAND (COLOMBIA) S.A. (RBS) </option>
                                <option value="23">UNION</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="card-holder-name">Nombre del titular</label>
                            <div class="col-sm-9">
                              <input type="text" ng-model="warranty.devol_nombre_titular" class="form-control keyboard" name="devol_nombre_titular"  placeholder="Nombre del titular">
                            </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="card-holder-name">Identificación</label>
                            <div class="col-sm-9">
                              <input type="number" ng-model="warranty.devol_iden_titular" class="form-control num" name="devol_iden_titular" id="card-holder-name" placeholder="Número de cuenta sin separadores">
                            </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="card-holder-name"></label>
                            <div class="col-sm-9">
                                <button class="btn btn-primary"  ng-click="datos_devolucion_save()">Guardar</button>
                            </div>
                    </div>
            </fieldset>
        </form>
        </div>  
    </div>
    <div class="row">
        <div class="col-lg-12">                 
            <div class="intro-text">
                    <hr class="star-light">  
                <span class="name" ><h2><font color="#424242">Imagenes de documentos</font></h2></span>
                    <hr class="star-light">                	 
            </div>
        </div>
        <div class="col-lg-12">
            Señor Usuario, Si desea puede adjuntar aqui las imágenes escaneadas de sus documentos para agilizar aún mas la gestión de la cita de la entrega de su vehículo.
						Estas imágenes no son obligatorias.
            <table border cellspacing='0'>
                    <tr><td height='100' width='200'>Licencia de Conducción (frente)</td><td><input type='file' name='img_licencia_conduccion_frente'></td></tr>
                    <tr><td height='100' width='200'>Licencia de Conducción (reverso)</td><td><input type='file' name='img_licencia_conduccion_reverso'></td></tr>
                    <tr><td height='100' width='200'>Cédula de Ciudadanía (frente)</td><td><input type='file' name='img_cedula_ciudadania_frente'></td></tr>
                    <tr><td height='100' width='200'>Cédula de Ciudadanía (reverso)</td><td><input type='file' name='img_cedula_ciudadania_reverso'></td></tr>
            </table>
        </div>
    </div>
    
    
        <!-- Modal -->
     <div id="myModal" data-backdrop="static" class="modal fade" role="dialog">
       <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
           <div class="modal-header">
             <h4 class="modal-title">Garantia por tarjeta de credito</h4>  
             <button type="button" class="close" data-dismiss="modal">&times;</button>

           </div>
             
           <div class="modal-body">
               Valor: $ ${Aseguradora.get("garantia")}
             <form ng-submit="Verify_warranty('credito')">		     
                 <div class="form-group">
                   <label class="col-sm-3 control-label" for="card-number">Numero de Tarjeta</label>
                   <div class="col-sm-9">
                     <input type="number" maxlength="16" ng-model="warranty.numero_tarjeta" class="form-control num" name="numero_tarjeta" id="card-number" placeholder="Numero de tarjeta">
                   </div>
                 </div>
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="card-number">Franquicia</label>
                     <div class="col-sm-9">
                           <select class="form-control" id="franquicia" ng-model="warranty.franquicia" name="franquicia">
                                <option value="">Selecciona</option>
                                  <c:forEach items="${franquicias}" var="franquicia">			
                                      <option value="${franquicia.get('id')}">${franquicia.get('nombre')}</option>							
                                  </c:forEach>
                           </select>
                      </div>
                 </div> 	
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="card-number">Banco</label>
                     <div class="col-sm-9">
                         <select name="banco" class="form-control" ng-model="warranty.banco" id="banco" style="">
                             <option value="">Seleccione una opción</option>
                             <option value="1">ABN AMRO</option>
                             <option value="3">AGRARIO</option>
                             <option value="4">AV VILLAS</option>
                             <option value="2">BANCAFE (GRANBANCO)</option>
                             <option value="97">BANCO MUNDO MUJER</option>
                             <option value="5">BANCOLOMBIA</option>
                             <option value="15">BANCOOMEVA</option>
                             <option value="6">BANITSMO (HSBC)</option>
                             <option value="7">BANK BOSTON</option>
                             <option value="8">BBVA</option>
                             <option value="9">BOGOTA</option>
                             <option value="10">CAJA SOCIAL</option>
                             <option value="11">CITIBANK</option>
                             <option value="12">COLMENA</option>
                             <option value="13">COLPATRIA</option>
                             <option value="14">CONFIAR</option>
                             <option value="89">CORPBANCA</option>
                             <option value="17">DAVIVIENDA</option>
                             <option value="65">FALABELLA</option>
                             <option value="22">GNB SUDAMERIS</option>
                             <option value="16">HELM BANK (CREDITO)</option>
                             <option value="90">JURISCOOP</option>
                             <option value="94">MACROFINANCIERA</option>
                             <option value="18">MEGABANCO</option>
                             <option value="19">OCCIDENTE</option>
                             <option value="82">PICHINCHA</option>
                             <option value="20">POPULAR</option>
                             <option value="21">SANTANDER</option>
                             <option value="24">THE ROYAL BANK OF SCOTLAND (COLOMBIA) S.A. (RBS) </option>
                             <option value="23">UNION</option>
                         </select>
                     </div>
                 </div>		
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="expiry-month">Fecha de expiración</label>
                         <div class="col-sm-9">
                             <div class="row">
                             <div class="col-xs-6">
                                 <select class="form-control" ng-model="warranty.month_expi" name="month_expi" id="expiry-month">
                                     <option>Seleccionar</option>
                                     <option value="01">Ene (01)</option>
                                     <option value="02">Feb (02)</option>
                                     <option value="03">Mar (03)</option>
                                     <option value="04">Abr (04)</option>
                                     <option value="05">May (05)</option>
                                     <option value="06">Juni (06)</option>
                                     <option value="07">Juli (07)</option>
                                     <option value="08">Ago (08)</option>
                                     <option value="09">Sep (09)</option>
                                     <option value="10">Oct (10)</option>
                                     <option value="11">Nov (11)</option>
                                     <option value="12">Dec (12)</option>
                                   </select>
                             </div>
                             <div class="col-xs-6">
                                 <select class="form-control" ng-model="warranty.year_expi" name="year_expi">
                                     <option value="2017">2017</option>
                                     <option value="2018">2018</option>
                                     <option value="2018">2019</option>
                                     <option value="2020">2020</option>
                                     <option value="2021">2021</option>
                                     <option value="2022">2022</option>
                                     <option value="2023">2023</option>
                                     <option value="2024">2024</option>
                                     <option value="2025">2025</option>
                                     <option value="2026">2026</option>
                                     <option value="2027">2027</option>
                                     <option value="2028">2028</option>
                                     <option value="2029">2029</option>
                                     <option value="2030">2030</option>
                                     <option value="2031">2031</option>
                                   </select>
                             </div>
                         </div>
                     </div>
                 </div>
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="cvv">CVV</label>
                     <div class="col-sm-3">
                         <input type="number" style="width:150px;" maxlength="3" ng-model="warranty.cvv" class="form-control num" name="cvv" id="cvv" placeholder="Security Code">		           
                     </div>
                 </div>
                 <img class="pull-right" src="http://www.domyownpestcontrol.com/images/securitycode.jpg" alt="Smiley face" height="150" width="150" style="margin-top:-110px;">
                 <div class="form-group col-sm-3">
                     <input  type="submit" class="btn btn-success" ng-click="verifywarranty('credito')"  value="Registrar">
                 </div>
             </form>		  		 
           </div>
           <div class="modal-footer">      	
             <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
           </div>
         </div>
       </div>
     </div>



     <!-- Modal -->
     <div id="myModal2" data-backdrop="static" class="modal fade" role="dialog">
       <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
           <div class="modal-header">
               <h4 class="modal-title">Consignación en efectivo</h4>
             <button type="button" class="close" data-dismiss="modal">&times;</button>

           </div>
           <div class="modal-body">
               Valor: $ ${Aseguradora.get("garantia_consignada")}
             <form class="form-horizontal" ng-submit="Verify_warranty('efectivo')" role="form">
                 <div class="form-group">
                     <label class="col-sm-6 control-label" for="card-holder-name">Numero de comprobante de consignación</label>
                         <div class="col-sm-9">
                               <input type="number" class="form-control num" ng-model="warranty.comprobante_consignacion" name="comprobante_consignacion" id="card-holder-name" placeholder="Comprobante de consignación">
                         </div>
                 </div>
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="card-number">Fecha de consignación</label>
                         <div class="col-sm-9">
                             <input class="form-control" name="fecha_consignacion" ng-model="warranty.fecha_consignacion" id="fecha1" type="date">
                         </div>
                 </div>
                 <div class="form-group col-sm-3">
                     <button type="submit" class="btn btn-submit" ng-click="Verify_warranty('efectivo')">Registrar</button>
                 </div>
             </form>		  
           </div>
           <div class="modal-footer">      	
             <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
           </div>
         </div>

       </div>
     </div>




     <!-- Modal -->
     <div id="myModal3" data-backdrop="static" class="modal fade" role="dialog">
       <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
           <div class="modal-header">
               <h4 class="modal-title">Todo riesgo no reembolsable</h4>
             <button type="button" class="close" data-dismiss="modal">&times;</button>

           </div>
           <div class="modal-body">
               Valor: $ ${Aseguradora.get('valor_no_reembols')}
             <form class="form-horizontal" onsubmit="Verify_warranty('efectivo')" role="form">	
                 <div class="form-group">
                     <label class="col-sm-6 control-label" for="card-holder-name">Numero de comprobante de consignación</label>
                     <div class="col-sm-9">
                         <input type="number" class="form-control num" ng-model="warranty.riesgo_consignacion" name="riesgo_consignacion" id="card-holder-name" placeholder="Comprobante de consignación">
                     </div>
                 </div>
                 <div class="form-group">
                     <label class="col-sm-3 control-label" for="card-number">Fecha de consignación</label>
                     <div class="col-sm-9">
                         <input class="form-control" id="fecha2" ng-model="warranty.riesgo_fecha" name="riesgo_fecha" type="date">
                     </div>
                 </div>
                 <div class="form-group col-sm-3">
                     <button type="submit" class="btn btn-submit" onclick="Verify_warranty('riesgo')">Registrar</button>
                 </div>
             </form>
           </div>
           <div class="modal-footer">      	
             <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
           </div>
         </div>

       </div>
     </div>    
    
</div>


  



      
<jsp:include page="layout/footer.jsp" />