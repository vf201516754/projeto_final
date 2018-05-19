$(function(){
	$.ajax({
        type    : 'POST',
        url     : 'lista_servicos',
    })
    .done(function(data){
    	console.log('success');
    })
    .fail(function(){
        console.log('error');
    })
	$.ajax({
        type    : 'POST',
        url     : 'lista_filas',
    })
    .done(function(data){
    	console.log('success');
    })
    .fail(function(){
        console.log('error');
    })
});