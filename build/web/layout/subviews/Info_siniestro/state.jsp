<%-- 
    Document   : state_e
    Created on : 22-mar-2018, 17:32:24
    Author     : JVega
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .a_td
    {
        color:'ffdddd';
    }
    
    .r_td
    {
        color:'ddffdd';
    }
</style>

    <div class="container">
        <div clas="row">
            <div col-lg-12 col-md-12 col-sm-12>
                <c:if test="${ Autorizacion.get('estado') == 'E' }">
                    <h3>GARANTIA EN PROCESO</h3>
                    Estimado Usuario: Ya se encuentra una o más garantías en proceso. Debe esperar a que sea aprobada o rechazada para continuar con el proceso o volver a incluir datos de una nueva garantía."
                </c:if>

                <c:if test="${ Autorizacion.get('estado') == 'A' }">
                    <h3>GARANTIA PROCESADA</h3>
                    Estimado Usuario: Ya se encuentra una o más garantías procesadas
                    <br> 
                    Garantias procesadas:
                </c:if>

                <c:if test="${ Autorizacion.get('estado') == 'R' }">
                    <h3>GARANTIA PROCESADA</h3>
                    Estimado Usuario: Ya se encuentra una o más garantías procesadas. 
                    <br> 
                    Garantias procesadas:
                </c:if>

                <br><br>
                Garantía(s) en proceso:
                <br><br>
                    <table style="width:70%" class="table-hover text-center" border cellspacing='0'>
                        <tr>
                            <th>Responsable</th>
                            <th>Fecha de Solicitud</th>
                            <th>Fecha de Proceso</th>
                            <th>Resultado</th>
                        </tr>
                        <c:forEach items="${Autorizaciones}" var="row">
                        <tr>
                            <td>${row.get('nombre')}</td>
                            <td>${row.get('fecha_solicitud')}</td>
                            <td>${row.get('fecha_proceso')}</td>
                            <c:if test="${ row.get('estado') == 'A' }">
                                <td >ACEPTADA</td>
                            </c:if>
                            <c:if test="${ row.get('estado') == 'R' }">
                                <td >RECHAZADA</td>
                            </c:if>
                            <c:if test="${ row.get('estado') == 'E' }">
                                <td></td>
                            </c:if>
                        </tr>	
                        </c:forEach>


                    </table>
     
                <a href="User"><button style="margin-left:80%; margin-top: -10%" class="btn btn-warning">CONTINUAR</button></a>
               
            </div>
           
        </div>    
    </div>