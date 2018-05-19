<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Atendimento | Balcão</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/subservico.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="inc/css/atendente-style.css" />
    </head>
    <body>
        <div class="menu">

        </div>
        <div class="atendimento">
            <h3>
                Subserviço em sessão
            </h3>
            <select name="" id="selecionarSubservicoAtendimento">
                <option value="" disabled>Selecione um Subservico</option>
                <c:forEach var="sub" items="${subservico }">
                	<option value="${sub.id }">${sub.nome }</option>
                </c:forEach>
            </select>            
        </div>
        <div class="selecionarSenha">
            <h3>
                Selecionar Senha
            </h3>
            <select name="" id="selecionarSenha">
            </select> 
            <input id="botaoChamar" type="submit" value="Chamar">
        </div>
        <div class="encaminharSenha">
            <h3>
                Em atendimento: <span id="emAtendimento"></span>
            </h3>
            <select name="" id="selecionarServicoEncaminhamento">
                <option value="">Selecione um Encaminhamento</option>
                <c:forEach var="subs" items="${subservico }">
                	<option value="${subs.id }">${subs.nome }</option>
                </c:forEach>
            </select> 
            <input id="botaoEncaminhar" type="submit" value="Encaminhar">
            <input id="botaoFinalizar" type="submit" value="Finalizar">
        </div>
        <span>
            <p>Selecione o serviço que deseja realizar atendimento.</p>
            <p>Após selecionar, você pode escolher a senha que deseja chamar<br>
            ou clicar em chamar para atender a próxima senha em sequência.</p>
        </span>
        <div id="ajaxGetUserServletResponse"></div>
    </body>
</html>