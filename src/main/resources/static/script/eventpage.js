function NewPage(){
	
	var b = document.getElementById('event_title').innerHTML;
	var c = document.getElementById('quantity').value;
	var d = document.getElementById('quantity2').value;
	var e = document.getElementById('quantity3').value;
	console.log("Hello");
	console.log(b);
	console.log(c);
	console.log(d);
	console.log(e);
	url = "PaymentPortal?event_name=" + encodeURIComponent(b)+"?bronze="+encodeURIComponent(c)+"?silver="+encodeURIComponent(d)+"?gold="+encodeURIComponent(e);
	document.location.href = url;	
}

