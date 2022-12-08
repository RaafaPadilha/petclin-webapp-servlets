<%--
    Document   : delete
    Created on : 15 de nov de 2022, 17:30:44
    Author     : RaafaPadilha
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cp" value="${pageContext.request.contextPath}"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css"/>
        <title>Petclin - Deletar Atendimento</title>
    </head>
    <body>
        <header>
            <div class="logo">
                <a href="${cp}/index.jsp">
                    <img src="${cp}/resources/images/logo.png" alt="Logo Petclin">
                    <p><i>Petclin</i></p>
                </a>
            </div>
            <div class="navbar">
                <a href="${cp}/templates/animals/list.jsp">Animais</a>
                <a href="${cp}/templates/appointments/list.jsp">Atendimentos</a>
            </div>
        </header>
        <main>
            <div class="title-page">
                <h1>Deletar Atendimento</h1>
            </div>
            <div class="listing-box">
                <table>
                    <tbody>
                        <tr>
                            <td>Data:</td>
                            <td>${requestScope.appointment.dateAppointment}</td>
                        </tr>
                        <tr>
                            <td>Raça do Animal:</td>
                            <td>${requestScope.appointment.timeAppointment}</td>
                        </tr>
                        <tr>
                            <td>Cor:</td>
                            <td>${requestScope.appointment.description}</td>
                        </tr>
                        <tr>
                            <td>Genero:</td>
                            <td>${requestScope.appointment.animal.name}</td>
                        </tr>
                    </tbody>
                </table>
                <form class="delete-form" action="${cp}/appointment" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${requestScope.appointment.id}">
                    <p class="form-grid-full-width">
                        <input class="form-delete-button" type="submit" value="Deletar">
                    </p>
                </form>
            </div>
            <div class="options-box">
                <a href="${cp}/templates/animals/list.jsp">Voltar</a>
            </div>
        </main>
    </body>
</html>
