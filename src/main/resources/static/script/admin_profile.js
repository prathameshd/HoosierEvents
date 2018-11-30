//var count=50;

$(document).ready(function(){
	$.ajax({
	type:'POST',
	dataType:'json',
	url:"/getUser",
	  success: function(data) {
		  console.log('success',data);
		  document.getElementById("user_name_display").innerHTML=data.name;
	   },
	  error:function(exception){alert('Exception:'+exception);}
	});
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getFutureEvents",
    	  success: function(data) {
    		  console.log('successEvents',data);
    		  document.getElementById("total_events").innerHTML=data.length; 
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	}); 
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getPendingEvents",
    	  success: function(data) {
    		  console.log('successEvents',data);
    		  document.getElementById("pending_events").innerHTML=data.length; 
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	}); 
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getAllUsers",
    	  success: function(data) {
    		  console.log('successEvents',data);
    		  document.getElementById("total_users").innerHTML=data.length; 
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	});
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getAllOrganizers",
    	  success: function(data) {
    		  console.log('successEvents',data);
    		  document.getElementById("total_org").innerHTML=data.length; 
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	});
	
//	document.getElementById("total_events").innerHTML+=total;
//	document.getElementById("pending_events").innerHTML+=pending;
//	document.getElementById("total_users").innerHTML+=count;
	div_count=4; //size of result set-that is list of events in waitlist table
	var i;
		for(i=0;i<div_count;i++){
		
				var div = document.createElement("div");
				//div.style.width = "100px";
				//div.style.height = "100px";
				//div.style.background = "lightgrey";
				div.style.border= "1px solid lightgrey";
					div.style.borderRadius= "6px";
				div.id="div"+i;
				//alert(div.id);
				div.style.align="center";
				var temp="div"+i;
				//var y=document.getElementById("temp");
				//document.getElementById('temp').addEventListener("click", function(){alert('q');});	//pass id
				//div.onClick="myFunction()"
				div.className="panel panel-default abcd";
				div.innerHTML= "&nbspEventName<br>";
				div.innerHTML+= "&nbspDate<br>";
				div.innerHTML+= "&nbspLocation";
				
				var b=document.createElement("button");
				b.id="approve"+i;
				b.innerHTML="Approve";
				b.style="background-color:green;color:white;border:1px solid green;border-radius:5px;box-shadow:0px 0px 0px grey;";
				b.class="app_buttons btn-primary";
				b.addEventListener("click",approveEvent);
				//alert(b.id);
							var b2=document.createElement("button");
				b2.id="deny"+i;
				b2.innerHTML="Deny";
				b2.addEventListener("click",denyEvent);
				b2.style="background-color:#C02837;border:1px solid #C02837;border-radius:5px;color:white;";
				b2.class="deny_buttons btn-primary";
				
				document.getElementById("waitlist_events").appendChild(div);
				document.getElementById(temp).innerHTML+="<br><br>";
				document.getElementById(temp).appendChild(b);
				document.getElementById(temp).appendChild(b2);
							//document.getElementById("approved").innerHTML+="<br>";

});


function approveEvent(b){
	alert('approved'+this.id);
	alert(window.location.href);
	//location.reload();
	location='#dash';
	//window.open('#approve');
}

function denyEvent(b){
	alert('Denied'+this.id);
}