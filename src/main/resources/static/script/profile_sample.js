var user_id=1;
var div_count = 0;
var events_to_attend;
$(document).ready(function(){
	$.ajax({
	type:'POST',
	dataType:'json',
	data: {id:user_id},
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
		data: {id:user_id},
		async:false,
		url:"/getEventsToBeAttendedByUser",
		  success: function(data) {
			  console.log('success',data);
			  events_to_attend = data;
			  div_count = data.length;
			  console.log(div_count);
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	console.log(events_to_attend);
	var i;
	for(i=0;i<div_count;i++){
		var div = document.createElement("div");
		//div.style.width = "100px";
		//div.style.height = "100px";
		//div.style.background = "lightgrey";
		div.style.border= "1px solid #F0EDED";
		div.style="background-color:lightgrey";
			div.style.borderRadius= "6px";
		var p="div"+i;
		div.id=p;
		div.name=p;
		var atname=events_to_attend[i].event.eventCategory.name
		div.setAttribute('category',atname);
		//alert(div.getAttribute('category'));
		div.style.align="center";
		//div.addEventListener("click", function(){alert('q'+i)});	//pass id
		//div.onClick="myFunction()"
		div.className="row col-sm-12 panel panel-default abcd";
		div.innerHTML= "<h5>"+events_to_attend[i].event.eventTitle+"</h5>"+events_to_attend[i].event.startDate+"<br>"+events_to_attend[i].event.location+"<br>";
//		div.innerHTML+= events_to_attend[i].event.eventTitle;
//		div.innerHTML+= "</h5>";
//		div.innerHTML+= events_to_attend[i].event.;
//		div.innerHTML+= "<br>";
//		div.innerHTML+= events_to_attend[i].event.;
//		div.innerHTML+= "<br>";
		div.innerHTML+= "Category: "+div.getAttribute('category');
	
		var b=document.createElement("button");
		b.id="button"+i;
		b.class="btn-default";
		b.innerHTML="Cancel Ticket";
		b.style="background-color:red;color:white;border:1px solid red;border-radius:4px;"
		
		document.getElementById("display_events").appendChild(div);
		document.getElementById(p).innerHTML+="<br><br>";
		document.getElementById(p).appendChild(b);
	}
	//document.getElementById("div1").style.display="none";
});


function myFunction(x){
//	window.location="eventpage.html?id="+x;
	alert('hello'+x);
}

function change(){
	var i;
	for(i=0;i<div_count;i++)
	{
		if(document.getElementById("event_category").value=="All")
		{
			var x="div"+i;
			document.getElementById(x).style.display="block";
		}
		else
		{
			var x="div"+i;
			var sel=document.getElementById("event_category").value;
			//if(document.getElementById(x).name!=x){
			//alert(i+"and"+sel);
			//alert(document.getElementById(x).getAttribute('category')+"and"+sel);
			if(document.getElementById(x).getAttribute('category')!=sel){
				//alert('success');
				document.getElementById(x).style.display="none";
			}
			else{
				document.getElementById(x).style.display="block";
	
			}
		}
	}
}