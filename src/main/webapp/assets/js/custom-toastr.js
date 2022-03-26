toastr.options.escapeHtml = true;
toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": true,
  "positionClass": "toast-bottom-left",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
function showToastr(type,message){
	 toastr[type](message);
}
$(function(){
	if($("#createSuccessToastr").val()){
	 	showToastr["success"]($("#createSuccessToastr").val());		
	}
	else if($("#createFailToastr").val()){
		showToastr["error"]($("#createFailToastr").val());	
	}

});