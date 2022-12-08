<%--
    Document   : create
    Created on : 15 de nov de 2022, 17:30:35
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
        <title>Petclin - Novo Atendimento</title>
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
                <h1>Novo Atendimento</h1>
            </div>
            <div class="form-box">
                <form action="${cp}/appointment" method="post" onsubmit="disabledButton()">
                    <input type="hidden" name="action" value="insert">
                    <p>
                        <label for="dateAppointment">Data</label>
                        <input type="text" name="dateAppointment" id="date" placeholder="__/__/____"
                               pattern="(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)[0-9][0-9]"
                               min="1900-01-01" max="2999-12-31" required>
                    </p>
                    <p>
                        <label for="timeAppointment">Horário</label>
                        <input type="text" name="timeAppointment" id="hora" placeholder="00:00"
                               pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}" required>
                    </p>
                    <p>
                        <label for="description">Descrição</label>
                        <input type="text" name="description" id="description" placeholder="e.g. Exames de rotina" required>
                    </p>
                    <p>
                        <jsp:useBean
                            id="serviceAnimal"
                            scope="page"
                            class="br.com.raafapadilha.petclin.service.AnimalService"/>

                        <label for="gender">Animal</label>
                        <select name="animalId" required>
                            <c:forEach items="${serviceAnimal.findAllAnimals()}" var="animal">
                                <option value="${animal.id}">
                                    ${animal.name} - ${animal.animalType.getValue()}
                                </option>
                            </c:forEach>
                        </select>
                    </p>
                    <p class="form-grid-full-width">
                        <input class="form-register-button" type="submit" value="Cadastrar" id="buttonSave">
                    </p>
                </form>
            </div>
            <div class="options-box">
                <a href="${cp}/templates/appointments/list.jsp">Voltar</a>
            </div>
        </main>
        <script src="${cp}/resources/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/resources/js/libs/mask/jquery.mask.min.js"></script>
        <script src="${cp}/resources/js/input-masks.js"></script>
        <script src="${cp}/resources/js/script.js"></script>
    </body>
</html>
