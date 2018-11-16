
function DisplayAllEvents(){
	console.log("Hello");
	$.ajax({
		type:'POST',
		dataType:'JSON',
		url:"/getAllEvents",
		  success: function(data) {
				console.log('success',data);
				document.getElementById("event_title").innerHTML=data;

		   },
		   error:function(exception){alert('Exception:'+exception);}})
}