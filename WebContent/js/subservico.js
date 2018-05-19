$(function() {
	var id;
	$('#selecionarSubservicoAtendimento').blur(function() {
        id = $('#selecionarSubservicoAtendimento option:selected').val();
        $.ajax({
            type	: 'POST',
            url		: 'buscar_senha_subservico',
            data	: ({subservico : id}),
        })
        .done(function(data) {
        	if(id != ""){
        		$('#selecionarSenha').empty();
        	}
        	
        	var toAppend = '';
        	for(var i = 0; i < data.length; i++){
        		console.log(data[i].senha['id'] + data[i].senha['codigo']);
        		toAppend += '<option value='+ data[i].senha['id'] +'>'+ data[i].senha['codigo'] +'</option>'
        	}
            $('#selecionarSenha').append(toAppend);
            $('#selecionarSubservicoAtendimento').change(function() {
            	$('#selecionarSenha').empty();
        	});
            $('#selecionarServicoEncaminhamento option[value='+ id +']').prop('disabled', true);
        })
        .fail(function() {
            console.log("error");
        });
    });
	$('#selecionarSubservicoAtendimento').click(function(){
		$('#selecionarServicoEncaminhamento option[value='+ id +']').prop('disabled', false);
	});
	$('#botaoChamar').click(function(){
		idSenha = $('#selecionarSenha option:selected').val();
		var codSenha = $('#selecionarSenha option:selected').text();
		$.ajax({
			type	: 'POST',
			url		: 'cadastrar_atendimento_subservico',
			data	: ({senha
					: idSenha}),
		})
		.done(function(data){
			$('#emAtendimento').append(codSenha);
			$('#selecionarSubservicoAtendimento').prop("disabled", true);
			$('#selecionarSenha').prop("disabled", true);
		})
        .fail(function() {
            console.log("error");
        });
	})
	$('#botaoFinalizar').click(function(){
		console.log(idSenha);
		$.ajax({
			type 	: 'POST',
			url 	: 'finalizar_atendimento',
			data 	: ({senha
					: idSenha}),
		})
		.done(function(data){
			console.log("success");
			$('#emAtendimento').empty();
			$('#selecionarSenha option:selected').remove();
			idSenha = null;
			$('#selecionarSubservicoAtendimento').prop("disabled", false);
			$('#selecionarSenha').prop("disabled", false);
		})
		.fail(function(){
			console.log("error");
		})
	})
	$('#botaoEncaminhar').click(function(){
		var idSubservico = $('#selecionarServicoEncaminhamento option:selected').val();
		idSenha = $('#selecionarSenha option:selected').val();
		$.ajax({
			type 	: 'POST',
			url 	: 'encaminhar_atendimento',
			data 	: ({
				subservico	: idSubservico,
				senha 		: idSenha}),
		})
		.done(function(data){
			console.log(idSubservico);
			console.log(idSenha);
			$('#emAtendimento').empty();
			$('#selecionarSenha option:selected').remove();
			idSenha = null;
			$('#selecionarSubservicoAtendimento').prop("disabled", false);
			$('#selecionarSenha').prop("disabled", false);
		})
		.fail(function(){
			console.log("error");
		})
	})
});