
var div_count = 0;
var div_count_2 = 0;
var events_to_attend;
var events_attended;

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
		async:false,
		url:"/getCategories",
		  success: function(data) {
			  console.log('success',data);
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	$.ajax({
		type:'POST',
		dataType:'json',
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
	
	$.ajax({
		type:'POST',
		dataType:'json',
		async:false,
		url:"/getEventsAttendedByUser",
		  success: function(data) {
			  console.log('successatt',data);
			  events_attended = data;
			  div_count_2 = data.length;
			  console.log(div_count_2);
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	$.ajax({
		type:'POST',
		dataType:'json',
		async:false,
		url:"/getUser",
		  success: function(data) {
			  console.log('success',data);
			  document.getElementById("user_name").value = data.name;
			  document.getElementById("user_address").value = data.address;
			  document.getElementById("user_contact").value = data.phoneNumber;
			  document.getElementById("user_email").value = data.email;
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	console.log(events_to_attend);
	console.log(events_attended);
	
	var i;
	for(i=0;i<div_count;i++){
		var div = document.createElement("div");
		
		//div.style.width = "100px";
		//div.style.height = "100px";
		//div.style.background = "lightgrey";
		div.style.border= "0.1px solid #F0EDED";
		div.style="background-color:white;font-family: 'Open Sans', sans-serif;";
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
		div.className="row col-sm-12 panel panel-default abc";
		
		
		var display_date;
		
		var day=String(events_to_attend[i].event.startDate[8])+String(events_to_attend[i].event.startDate[9]);
			var month=String(events_to_attend[i].event.startDate[5])+String(events_to_attend[i].event.startDate[6]);
			//document.getElementById("event_date").innerHTML+="<p style='color:black;'>Date:";
			if(month=="01")
			{	display_date="Date: Jan "+day;	}
			if(month=="02")
			{	display_date="Date: Feb "+day;	}
			if(month=="03")
			{	display_date="Date: Mar "+day;	}
			if(month=="04")
			{	display_date="Date: Apr "+day;	}
			if(month=="05")
			{	display_date="Date: May "+day;	}
			if(month=="06")
			{	display_date="Date: Jun "+day;	}
			if(month=="07")
			{	display_date="Date: Jul "+day;	}
			if(month=="08")
			{	display_date="Date: Aug "+day;	}
			if(month=="09")
			{	display_date="Date: Sep "+day;	}
			if(month=="10")
			{	display_date="Date: Oct "+day;	}
			if(month=="11")
			{	display_date="Date: Nov "+day;	}
			if(month=="12")
			{	display_date="Date: Dec "+day;	}
			
			//document.getElementById("event_date").innerHTML+="&nbsp"+day;	
			//document.getElementById("event_start_time").innerHTML+="Time:&nbsp";
			
			/*for(x=11;x<=15;x++)
			{
			document.getElementById("event_start_time").innerHTML+=data.startDate[x];
			}*/
			
			
		
		
		
		
		
		
		
		
		
		
		//document.getElementById("eventimg").src='/getImage?img='+ data.id+'.jpg';

		//retrieve image src by id of event
		//div.innerHTML="<img src='/getImage?img="+events_to_attend.event.id+".jpg'>";

		div.innerHTML+= "<h5>"+events_to_attend[i].event.eventTitle+"</h5>"+display_date+"<br>"+events_to_attend[i].event.location+"<br>";


		//display category on the tile	div.innerHTML+= "Category: "+div.getAttribute('category');
	
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


function updateUser(){
	
	var name = document.getElementById("user_name").value;
	var address = document.getElementById("user_address").value;
	var phoneNo = document.getElementById("user_contact").value;
	var email = document.getElementById("user_email").value;
	
	$.ajax({
		type:'POST',
		data: {user_name:name, user_address:address, user_phone:phoneNo, user_email:email},
		url:"/updateUserDetails",
		  success: function(data) {
			  console.log('success',data);
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
}


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