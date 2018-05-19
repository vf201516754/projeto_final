<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<head>
	<!-- Standard Meta -->
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- Site Properties -->
	<title>Home - Cartório de Notas</title>
	<link rel="stylesheet" type="text/css" href="semantic/components/reset.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/site.css">

	<link rel="stylesheet" type="text/css" href="semantic/components/container.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/grid.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/header.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/image.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/menu.css">

	<link rel="stylesheet" type="text/css" href="semantic/components/divider.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/dropdown.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/segment.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/button.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/list.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/icon.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/sidebar.css">
	<link rel="stylesheet" type="text/css" href="semantic/components/transition.css">

	<!-- Style CSS -->
	<style>
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

		@media only screen and (max-width: 700px) {
			.ui.fixed.menu {
				display: none !important;
			}
			.secondary.pointing.menu .item,
			.secondary.pointing.menu .menu {
				display: none;
			}
			.secondary.pointing.menu .toc.item {
				display: block;
			}
			.masthead.segment {
				min-height: 350px;
			}
			.masthead h1.ui.header {
				font-size: 2em;
				margin-top: 1.5em;
			}
			.masthead h2 {
				margin-top: 0.5em;
				font-size: 1.5em;
			}

		}
	</style>

	<!-- Script JS-->
	<script src="js/jquery.min.js"></script>
	<script src="js/npm.js"></script>
	<script src="semantic/components/visibility.js"></script>
	<script src="semantic/components/sidebar.js"></script>
	<script src="semantic/components/transition.js"></script>
	<script src="js/spinner.js" type="text/javascript"></script>

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
			<a href="index.jsp" class="active item">Home</a>
			<a href="form_senha" class="item">Gerar Senha</a>
			<a href="painel_senhas" class="item">Painel de Senhas</a>
			<a href="painel_servico" class="item">Painel de Serviços</a>
			<a href="painel_subservico" class="item">Painel de Subserviços</a>
		</div>
	</div>

	<!-- Sidebar Menu -->
	<div class="ui vertical inverted sidebar menu">
		<a href="index.jsp" class="active item">Home</a>
		<a href="form_senha" class="item">Gerar Senha</a>
		<a href="painel_senhas" class="item">Painel de Senhas</a>
		<a href="painel_servico" class="item">Painel de Serviços</a>
		<a href="painel_subservico" class="item">Painel de Subserviços</a>
	</div>


	<!-- Page Contents -->
	<div class="pusher">
		<div class="ui inverted vertical masthead center aligned segment">

			<div class="ui container">
				<div class="ui large secondary inverted pointing menu">
					<a class="toc item">
						<i class="sidebar icon"></i>
					</a>
					<a href="index.jsp" class="active item">Home</a>
					<a href="form_senha" class="item">Gerar Senha</a>
					<a href="painel_senhas" class="item">Painel de Senhas</a>
					<a href="painel_servico" class="item">Painel de Serviços</a>
					<a href="painel_subservico" class="item">Painel de Subserviços</a>
				</div>
			</div>

			<div class="ui text container">
				<h1 class="ui inverted header">
					Cartório-de-Notas
				</h1>
				<h2>O que podemos fazere por você hoje?</h2>
				<div class="ui huge primary button">
					<a href="form_senha" class="btn-gerar-senha">Gerar Senha</a>
					<i class="right arrow icon"></i>
				</div>
			</div>

		</div>

		<div class="ui vertical stripe segment">
			<div class="ui middle aligned stackable grid container">
				<div class="row">
					<div class="eight wide column">

						<h3 class="ui header">Baixe nosso aplicativo</h3>
						<p>Tudo ficou mais fácil agora. Com nosso novo aplicativo você pode retirar senhas e consultar andamento do seu atendimento.
							Baixe agora e teste!</p>
					</div>
					<div class="six wide right floated column">
						<img src="img/img-1.jpg" class="ui large bordered rounded image">
					</div>
				</div>
				<div class="row">
					<div class="center aligned column">
						<a class="ui huge button">Baixar aplicativo</a>
					</div>
				</div>
			</div>
		</div>


		<div class="ui vertical stripe quote segment">
			<div class="ui equal width stackable internally celled grid">
				<div class="center aligned row">
					<div class="column">
						<h3>"Missão"</h3>
						<p>Prestar ao usuário serviço notarial com qualidade, respeito, presteza, tranquilidade e segurança jurídica.
						</p>
					</div>
					<div class="column">
						<h3>"Visão"</h3>
						<p>Ser referência na prestação de serviços notariais. Ser um cartório ágil e moderno.</p>
					</div>
					<div class="column">
						<h3>"Valores"</h3>
						<p>Assessoria Notarial Atendimento de Qualidade Colaboradores positivos e motivados Criatividade jurídica e racionalidade
							na elaboração dos atos Ética e Confiança Responsabilidade Ambiental e Social</p>
					</div>
				</div>
			</div>
		</div>




		<div class="ui inverted vertical footer segment">
			<div class="ui container">
				<div class="ui stackable inverted divided equal height stackable grid">
					<div class="three wide column">
						<h4 class="ui inverted header">Paginas</h4>
						<div class="ui inverted link list">
							<a href="index.jsp" class="active-item">Home</a>
							<a href="form_senha" class="item">Gerar Senha</a>
							<a href="painel_senhas" class="item">Painel de senhas</a>
						</div>
					</div>
					<div class="three wide column">
						<h4 class="ui inverted header">Nossos serviços</h4>
						<div class="ui inverted link list">
							<a href="form_senha" class="item">Abetura de firmas</a>
							<a href="form_senha" class="item">Ata Notarial</a>
							<a href="form_senha" class="item">Autenticação</a>
							<a href="form_senha" class="item">Divórcio</a>
							<a href="form_senha" class="item">Escritura</a>
							<a href="form_senha" class="item">Invetário</a>
							<a href="form_senha" class="item">Procuração</a>
							<a href="form_senha" class="item">Reconheciment de firma</a>
							<a href="form_senha" class="item">Serviço Mensalista</a>
							<a href="form_senha" class="item">Testamento</a>
						</div>
					</div>
					<div class="seven wide column">
						<h4 class="ui inverted header">Endereço</h4>
						<p>Avenida Interlagos, 3051 - São Paulo (Capital) / SP - CEP 07171-230</p>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script>

</script>
</html>