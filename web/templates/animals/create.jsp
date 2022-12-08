<%--
    Document   : create
    Created on : 10 de nov de 2022, 10:04:03
    Author     : RaafaPadilha
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cp" value="${pageContext.request.contextPath}"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css"/>
        <title>Petclin - Novo Animal</title>
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
                <h1>Novo Animal</h1>
            </div>
            <div class="form-box">
                <form action="${cp}/animal" method="post" onsubmit="disabledButton()">
                    <input type="hidden" name="action" value="insert">
                    <p>
                        <label for="name">Nome</label>
                        <input type="text" name="name" id="name" placeholder="e.g. Sandy" required>
                    </p>
                    <p>
                        <label for="breed">Raça</label>
                        <input type="text" name="breed" id="breed" placeholder="e.g. SRD" required>
                    </p>
                    <p>
                        <label for="color">Cor</label>
                        <input type="text" name="color" id="color" placeholder="e.g. Branca" required>
                    </p>
                    <p>
                        <label for="gender">Genero</label>
                        <select name="gender" id="gender">
                            <option value="male">Macho</option>
                            <option value="female">Femea</option>
                        </select>
                    </p>
                    <div class="form-grid-three-columns">
                        <p>
                            <label for="birthDate">Data de Nascimento</label>
                            <input type="text" name="birthDate" id="date" placeholder="__/__/____"
                                   pattern="(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)[0-9][0-9]"
                                   min="1900-01-01" max="2999-12-31" required>
                        </p>
                        <p>
                            <label for="weigth">Peso</label>
                            <input type="number" name="weigth" id="weigth"
                                   min="0" max="99" step="0.1" placeholder="00.0" required>
                        </p>
                        <p>
                            <label for="animalType">Animal</label>
                            <select name="animalType" id="animalType">
                                <option value="dog">Cachorro</option>
                                <option value="cat">Gato</option>
                            </select>
                        </p>
                    </div>
                    <p class="form-grid-full-width">
                        <input class="form-register-button" type="submit" value="Cadastrar" id="buttonSave">
                    </p>
                </form>
            </div>
            <div class="options-box">
                <a href="${cp}/templates/animals/list.jsp">Voltar</a>
            </div>
        </main>
        <script src="${cp}/resources/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/resources/js/libs/mask/jquery.mask.min.js"></script>
        <script src="${cp}/resources/js/input-masks.js"></script>
        <script src="${cp}/resources/js/script.js"></script>
    </body>
</html>
