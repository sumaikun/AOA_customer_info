<%-- 
    Document   : Siniester_table
    Created on : 22-mar-2018, 15:44:50
    Author     : JVega
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Actions.Helpers.Decoration_functions"%>
    <br><br><br>
    <div class="container text-center">
        <div col-lg-12 col-md-12>
            <h3>Información del siniestro</h3>
            <br>
            <table class="table table-bordered  table-hover ">
                <tbody>
                    <tr>
                        <td><b>Placa del vehículo Siniestrado</b></td>
                        <td>${ Siniestro.get("placa")}</td>
                    </tr>
                    <tr>
                        <td><b>Numero de Siniestro</b></td>
                        <td>${ Siniestro.get("numero")}</td>
                    </tr>
                    <tr>
                        <td><b>Aseguradora</b></td>
                        <td>${ Aseguradora.get("nombre")}</td>
                    </tr>
                    <tr>
                        <td><b>Asegurado</b></td>
                        <td>${ Siniestro.get("asegurado_nombre")}</td>
                    </tr>
                    <tr>
                        <td><b>Declarante</b></td>
                        <td>${ Siniestro.get("declarante_nombre")}</td>
                    </tr>
                    <tr>
                        <td><b>Ciudad de Atención</b></td>
                        <td>${ Ciudad.get("nciu")}</td>
                    </tr>
                    <tr>
                        <td><b>Fecha de Autorización</b></td>                        
                        <td><% out.print(Decoration_functions.convert_date_spanish(request.getAttribute("fec_siniestro").toString())); %></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
