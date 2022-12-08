<%--
    Document   : delete
    Created on : 15 de nov de 2022, 10:47:37
    Author     : rafael
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
        <title>Petclin - Deletar Animal</title>
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
                <h1>Deletar Animal</h1>
            </div>
            <div class="listing-box">
                <table>
                    <tbody>
                        <tr>
                            <td>Nome do Animal:</td>
                            <td>${requestScope.animal.name}</td>
                        </tr>
                        <tr>
                            <td>Raça do Animal:</td>
                            <td>${requestScope.animal.breed}</td>
                        </tr>
                        <tr>
                            <td>Cor:</td>
                            <td>${requestScope.animal.color}</td>
                        </tr>
                        <tr>
                            <td>Genero:</td>
                            <td>${requestScope.animal.gender.getValue()}</td>
                        </tr>
                        <tr>
                            <td>Data de Nascimento:</td>

                            <fmt:parseDate
                                value="${requestScope.animal.birthDate}"
                                type="date"
                                pattern="yyyy-MM-dd"
                                var="birthDateParsed"/>

                            <fmt:formatDate
                                value="${birthDateParsed}"
                                type="date"
                                pattern="dd/MM/yyyy"
                                var="birthDateFormatted"/>

                            <td>${birthDateFormatted}</td>
                        </tr>
                        <tr>
                            <td>Peso (Quiilograma):</td>
                            <td>${requestScope.animal.weigth} Kg</td>
                        </tr>
                        <tr>
                            <td>Tipo do Animal:</td>
                            <td>${requestScope.animal.animalType.getValue()}</td>
                        </tr>
                    </tbody>
                </table>
                <form action="${cp}/animal" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${requestScope.animal.id}">
                    <input type="hidden" name="animalType" value="${requestScope.animal.animalType}">
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
