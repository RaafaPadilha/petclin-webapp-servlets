<%--
    Document   : list
    Created on : 15 de nov de 2022, 17:30:28
    Author     : RaafaPadilha
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefix" value="appointment?action="/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${cp}/resources/css/style.css"/>
        <title>Petclin - Listagem de Atendimentos</title>
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
                <h1>Listagem de Atendimentos</h1>
            </div>
            <div class="options-box">
                <a href="${cp}/templates/appointments/create.jsp">Novo Atendimento</a>
            </div>
            <div class="listing-box">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Data</th>
                            <th>Horário</th>
                            <th>Descrição</th>
                            <th>Animal</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean
                            id="service"
                            scope="page"
                            class="br.com.raafapadilha.petclin.service.AppointmentService"/>

                        <c:choose>
                            <c:when test="${service.findAllAppointments().size() eq 0}">
                                <tr>
                                    <td colspan="6">Nenhum dado cadastrado</td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach items="${service.findAllAppointments()}" var="appointment">
                                    <tr>
                                        <td>${appointment.id}</td>
                                        <td>
                                            <fmt:parseDate
                                                value="${appointment.dateAppointment}"
                                                type="date"
                                                pattern="yyyy-MM-dd"
                                                var="dateAppointmentParsed"/>

                                            <fmt:formatDate
                                                value="${dateAppointmentParsed}"
                                                type="date"
                                                pattern="dd/MM/yyyy"
                                                var="dateAppointmentFormatted"/>

                                            ${dateAppointmentFormatted}
                                        </td>
                                        <td>${appointment.timeAppointment} h</td>
                                        <td>${appointment.description}</td>
                                        <td>${appointment.animal.name}</td>
                                        <td>
                                            <a class="table-action-button yellow-button" href="${cp}/${prefix}updateAppointment&id=${appointment.id}">
                                                Alterar
                                            </a>
                                            <a class="table-action-button red-button" href="${cp}/${prefix}deleteAppointment&id=${appointment.id}">
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
