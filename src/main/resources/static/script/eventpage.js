function NewPage(){
	
	var b = document.getElementById('event_title').innerHTML;
	console.log("Hello");
	console.log(b);
	url = "PaymentPortal?name=" + encodeURIComponent(b);
	document.location.href = url;	
}