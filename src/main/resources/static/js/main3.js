/**
 * 
 */
$(document).ready(function () {
	$('.table .delbtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.myform #delRef').attr('href',href);
		$('.myform #exampleModal').modal();
	});
});