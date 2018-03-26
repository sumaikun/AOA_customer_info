<%-- 
    Document   : InformacionSiniestro
    Created on : 22-mar-2018, 12:21:09
    Author     : JVega
--%>

<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<jsp:include page="layout/header.jsp" />

<c:if test="${ Siniestro.get('renta') == 1 }">
    <jsp:include page="layout/subviews/Info_siniestro/rent_table.jsp" />
</c:if>

<c:if test="${ Siniestro.get('renta') == 0 }">
    <jsp:include page="layout/subviews/Info_siniestro/Siniester_table.jsp" />
</c:if>

<jsp:include page="layout/subviews/Info_siniestro/state.jsp" />
  
<jsp:include page="layout/footer.jsp" />
