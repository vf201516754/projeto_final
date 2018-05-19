$(function(){
    function intervalo(){
        myVar1 = setInterval(verificaNovaSenhaAtendimento, 2000);
        myVar2 = setInterval(verificaNovaSenhaAutenticacao, 1000);
        myVar3 = setInterval(verificaNovaSenhaCaixa, 1000);
    }
    intervalo();
	function verificaNovaSenhaAtendimento(){
        $.ajax({
            type    : 'POST',
            url     : 'verifica_senha_atendimento',
        })
        .done(function(data){
        	$('div.atendimento h2').empty();
        	$('div.atendimento h1').empty();
            $('div.atendimento h2').append(data[0].senha['fila']['nome']);
            $('div.atendimento h1').append(data[0].senha['codigo']);
        })
        .fail(function(){
            console.log('error');
        })
    };
    function verificaNovaSenhaAutenticacao(){
        $.ajax({
            type    : 'POST',
            url     : 'verifica_senha_autenticacao',
        })
        .done(function(data){
        	$('div.autenticacao h2').empty();
        	$('div.autenticacao h1').empty();
            $('div.autenticacao h2').append(data[0].senha['fila']['nome']);
            $('div.autenticacao h1').append(data[0].senha['codigo']);
        })
        .fail(function(){
            console.log('error');
        })
    };
    function verificaNovaSenhaCaixa(){
        $.ajax({
            type    : 'POST',
            url     : 'verifica_senha_caixa',
        })
        .done(function(data){
        	$('div.caixa h2').empty();
        	$('div.caixa h1').empty();
            $('div.caixa h2').append(data[0].senha['fila']['nome']);
            $('div.caixa h1').append(data[0].senha['codigo']);
        })
        .fail(function(){
            console.log('error');
        })
    }
})