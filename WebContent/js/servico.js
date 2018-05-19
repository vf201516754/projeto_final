$(function() {
	var idSenha;
	$('#selecionarAtendimento').blur(function() {
        var id = $('#selecionarAtendimento option:selected').val();

        $.ajax({
            type	: 'POST',
            url		: 'buscar_senha',
            data	: ({senha : id}),
        })
        .done(function(data) {
        	
        	if(id != ""){
        		$('#selecionarSenha').empty();
        	}
        	
        	var toAppend = '';
        	for(var i = 0; i < data.length; i++){
        		toAppend += '<option value='+ data[i]['id'] +'>'+ data[i]['codigo'] +'</option>'
        	}
            $('#selecionarSenha').append(toAppend);
            $('#selecionarAtendimento').change(function() {
            	$('#selecionarSenha').empty();
        	});
        })
        .fail(function() {
            console.log("error");
        });
    });
	$('#botaoChamar').click(function(){
		idSenha = $('#selecionarSenha option:selected').val();
		var codSenha = $('#selecionarSenha option:selected').text();
		$.ajax({
			type	: 'POST',
			url		: 'cadastrar_atendimento',
			data	: ({senha
					: idSenha}),
		})
		.done(function(data){
			console.log(codSenha);
			$('#emAtendimento').append(codSenha);
			$('#selecionarAtendimento').prop("disabled", true);
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
			$('#selecionarAtendimento').prop("disabled", false);
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
			console.log("success");
			$('#emAtendimento').empty();
			$('#selecionarSenha option:selected').remove();
			idSenha = null;
			$('#selecionarAtendimento').prop("disabled", false);
			$('#selecionarSenha').prop("disabled", false);
		})
		.fail(function(){
			console.log("error");
		})
	})
});