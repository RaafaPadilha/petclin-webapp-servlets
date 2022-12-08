<%--
    Document   : index
    Created on : 9 de nov de 2022, 12:24:05
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
        <title>Petclin - Página Inicial</title>
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
                <h1>Página Inicial</h1>
            </div>
            <div class="informations">
                <div class="informations-box">
                    <div class="informations-box-title">
                        <p>Animais Cadastrados</p>
                    </div>
                    <div class="informations-box-value">
                        <p>0</p>
                    </div>
                </div>
                <div class="informations-box">
                    <div class="informations-box-title">
                        <p>Total de Atendimentos</p>
                    </div>
                    <div class="informations-box-value">
                        <p>0</p>
                    </div>
                </div>
                <div class="informations-box">
                    <div class="informations-box-title">
                        <p>Atendimentos Hoje</p>
                    </div>
                    <div class="informations-box-value">
                        <p>0</p>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
