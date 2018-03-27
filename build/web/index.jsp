<%-- 
    Document   : index
    Created on : 22-feb-2018, 5:42:15
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="layout/header.jsp" />
    <% session.invalidate(); %>
    <jsp:include page="layout/login.jsp" />
<jsp:include page="layout/footer.jsp" />