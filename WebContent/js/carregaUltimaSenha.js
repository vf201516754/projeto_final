$(document).on("ready", carregaUltimaSenha())
	function carregaUltimaSenha(){
		$.ajax({
				type	: 'POST',
				url		: 'busca_ultima_senha',
	    })
	    .done(function(data) {
	    	console.log("success");
	    })
	    .fail(function() {
	        console.log("error");
	    });
	}