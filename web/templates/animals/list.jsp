<%--
    Document   : list
    Created on : 9 de nov de 2022, 20:00:39
    Author     : RaafaPadilha
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefix" value="animal?action="/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css"/>
        <title>Petclin - Listagem de Animais</title>
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
                <h1>Listagem de Animais</h1>
            </div>
            <div class="options-box">
                <a href="${cp}/templates/animals/create.jsp">Novo Animal</a>
            </div>
            <div class="listing-box">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Raça</th>
                            <th>Cor</th>
                            <th>Genero</th>
                            <th>Data de Nascimento</th>
                            <th>Peso</th>
                            <th>Animal</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean
                            id="service"
                            scope="page"
                            class="br.com.raafapadilha.petclin.service.AnimalService"/>

                        <c:choose>
                            <c:when test="${service.findAllAnimals().size() eq 0}">
                                <tr>
                                    <td colspan="9">Nenhum dado cadastrado</td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach items="${service.findAllAnimals()}" var="animal">
                                    <tr>
                                        <td>${animal.id}</td>
                                        <td>${animal.name}</td>
                                        <td>${animal.breed}</td>
                                        <td>${animal.color}</td>
                                        <td>${animal.gender.getValue()}</td>
                                        <td>
                                            <fmt:parseDate
                                                value="${animal.birthDate}"
                                                type="date"
                                                pattern="yyyy-MM-dd"
                                                var="birthDateParsed"/>

                                            <fmt:formatDate
                                                value="${birthDateParsed}"
                                                type="date"
                                                pattern="dd/MM/yyyy"
                                                var="birthDateFormatted"/>

                                            ${birthDateFormatted}
                                        </td>
                                        <td>${animal.weigth} Kg</td>
                                        <td>${animal.animalType.getValue()}</td>
                                        <td>
                                            <a class="table-action-button yellow-button" href="${cp}/${prefix}updateAnimal&id=${animal.id}">
                                                Alterar
                                            </a>
                                            <a class="table-action-button red-button" href="${cp}/${prefix}deleteAnimal&id=${animal.id}">
                                                Excluir
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            <div class="options-box">
                <a href="${cp}/index.jsp">Voltar</a>
            </div>
        </main>
    </body>
</html>
