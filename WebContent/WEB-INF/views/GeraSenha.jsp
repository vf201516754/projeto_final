<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- Site Properties -->
	<title>Cartorio-de-Notas - Gerar Senha</title>
	<link rel="stylesheet" type="text/css" href="semantic/components/reset.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/site.css">

	<link rel="stylesheet" type="text/css" href="semantic/components/container.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/grid.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/header.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/image.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/menu.css">

	<link rel="stylesheet" type="text/css" href="semantic/components/divider.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/segment.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/form.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/input.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/button.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/list.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/message.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/icon.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/sidebar.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/transition.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/dropdown.css">
	
	<script src="js/spinner.js" type="text/javascript"></script>

	<!-- Style CSS -->
	<style type="text/css">
		body {
			background-color: #DADADA;
		}

		body>.grid {
			height: 100%;
		}

		.image {
			margin-top: -100px;
		}

		.column {
			max-width: 450px;
		}

		.form-servico {
			margin-bottom: 16px;
		}

		.form-prioridade {
			margin-bottom: 16px;
		}

				.hidden.menu {
			display: none;
		}

		.masthead.segment {
			min-height: 700px;
			padding: 1em 0em;
		}

		.masthead .logo.item img {
			margin-right: 1em;
		}

		.masthead .ui.menu .ui.button {
			margin-left: 0.5em;
		}

		.masthead h1.ui.header {
			margin-top: 3em;
			margin-bottom: 0em;
			font-size: 4em;
			font-weight: normal;
		}

		.masthead h2 {
			font-size: 1.7em;
			font-weight: normal;
		}

		.ui.vertical.stripe {
			padding: 8em 0em;
		}

		.ui.vertical.stripe h3 {
			font-size: 2em;
		}

		.ui.vertical.stripe .button+h3,
		.ui.vertical.stripe p+h3 {
			margin-top: 3em;
		}

		.ui.vertical.stripe .floated.image {
			clear: both;
		}

		.ui.vertical.stripe p {
			font-size: 1.33em;
		}

		.ui.vertical.stripe .horizontal.divider {
			margin: 3em 0em;
		}

		.quote.stripe.segment {
			padding: 0em;
		}

		.quote.stripe.segment .grid .column {
			padding-top: 5em;
			padding-bottom: 5em;
		}

		.footer.segment {
			padding: 5em 0em;
		}

		.secondary.pointing.menu .toc.item {
			display: none;
		}

		a:link,
		a:visited {
			text-decoration: none;
			color: #fff;
		}

		a:hover {
			text-decoration: none;
			color: #e6e6e6;
		}

		a:active {
			text-decoration: none;
			color: #fff;
		}

		.style-form-gerar-senha {
			margin-top: 64px;
		}

		body {
			background-color: black !important;
		}
		.title-gerar-senha {
			color:#2185D0;
		}
	</style>


	<!-- Script JS-->
	<script src="js/jquery.min.js"></script>
	<script src="js/npm.js"></script>
	<script src="semantic/components/visibility.js"></script>
	<script src="semantic/components/sidebar.js"></script>
	<script src="semantic/components/transition.js"></script>


	<!-- Script JS -->
	<script>
			$(document)
				.ready(function () {
					// fix menu when passed
					$('.masthead')
						.visibility({
							once: false,
							onBottomPassed: function () {
								$('.fixed.menu').transition('fade in');
							},
							onBottomPassedReverse: function () {
								$('.fixed.menu').transition('fade out');
							}
						})
						;
					// create sidebar and attach to menu open
					$('.ui.sidebar')
						.sidebar('attach events', '.toc.item')
						;
				})
				;
		</script>

</head>


<body>

	<!-- Following Menu -->
	<div class="ui large top fixed hidden menu">
		<div class="ui container">
			<a href="index.html" class="item">Home</a>
			<a href="form_senha" class="active item">Gerar Senha</a>
			<a href="painel_senhas" class="item">Painel de Senhas</a>
			<a href="painel_servico" class="item">Painel de Serviços</a>
			<a href="painel_subservico" class="item">Painel de Subserviços</a>
		</div>
	</div>

	<!-- Sidebar Menu -->
	<div class="ui vertical inverted sidebar menu">
		<a href="index.html" class="item">Home</a>
		<a href="form_senha" class="active item">Gerar Senha</a>
		<a href="painel_senhas" class="item">Painel de Senhas</a>
		<a href="painel_servico" class="item">Painel de Serviços</a>
		<a href="painel_subservico" class="item">Painel de Subserviços</a>
	</div>

	<div class="pusher">
			<div class="ui inverted vertical masthead center aligned segment">
	
				<div class="ui container">
					<div class="ui large secondary inverted pointing menu">
						<a class="toc item">
							<i class="sidebar icon"></i>
						</a>
						<a href="index.html" class="item">Home</a>
						<a href="form_senha" class="active item">Gerar Senha</a>
						<a href="painel_senhas" class="item">Painel de Senhas</a>
						<a href="painel_servico" class="item">Painel de Serviços</a>
						<a href="painel_subservico" class="item">Painel de Subserviços</a>
					</div>
				</div>
				
				<div class="ui middle aligned center aligned grid">
						<div class="column style-form-gerar-senha">
							<h2 class="ui teal image header">
								<div class="content">
									<h2 class="title-gerar-senha">Gerar Senha</h2>
								</div>
							</h2>
				
							<form action="gera_senha" method="GET" class="ui large form">
								<div class="ui stacked segment">
									<div class="form-servico">
										<select class="form-control ui dropdown" name="servico" id="servico">
											<option value=""></option>
											<c:forEach var="servico" items="${servicos}">
												<option value="${servico.sigla}">${servico.nome}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-prioridade">
										<select class="form-control ui dropdown" name="fila" id="fila">
											<option value=""></option>
											<c:forEach var="fila" items="${filas}">
												<option value="${fila.sigla}">${fila.nome}</option>
											</c:forEach>
										</select>
									</div>
									<div id="actions">
										<button id="botao" class="ui fluid large blue button" type="submit">
											Gerar Senha
										</button>
									</div>
								</div>
				
								<div id="ajaxGetUserServletResponse"></div>
							</form>
						</div>
					</div>
			</div>


	</div>

	
</body>


</html>