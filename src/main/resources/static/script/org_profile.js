
var count=50;
var div_count=6;
var events_to_occur;
var events_occured;
var pending_event_count;
var live_event_count;
$(document).ready(function(){
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getFutureEventsByOrganizer",
    	  success: function(data) {
    		  console.log('successEvents',data);
			  live_event_count=data.length;
			  events_to_occur = data;
    		  document.getElementById("total_events").innerHTML=data.length; 
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	}); 
	$.ajax({
		type:'POST',
		dataType:'json',
		url:"/getUser",
		  success: function(data) {
			  console.log('success',data);
			  document.getElementById("user_name_display").innerHTML=data.name;
			  document.getElementById("user_name").value = data.name;
			  document.getElementById("user_address").value = data.address;
			  document.getElementById("user_contact").value = data.phoneNumber;
			  document.getElementById("user_email").value = data.email;
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getEventsByOrganizerToBeApproved",
    	  success: function(data) {
    		  console.log('successEvents',data);
			  pending_event_count=data.length;
    		  document.getElementById("pending_events").innerHTML=data.length; 
    		  events_occured = data;
    	   },
    	  error:function(exception){alert('Exception:'+exception);}
    	}); 
	
	$.ajax({
		type:'POST',
		dataType:'json',
		url:"/getUserDemographics",
		  success: function(data) {
			  console.log('User demographics Success!')
			  console.log('success',data);
			  document.getElementById("total_users").innerHTML=data.length; 
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	$.ajax({
		type:'POST',
		dataType:'json',
		url:"/getRevenue",
		  success: function(data) {
			  console.log('User demographics Success!')
			  console.log('success',data);
//			  document.getElementById("total_users").innerHTML=data.length; 
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	console.log("hello");
	console.log(live_event_count);
	console.log(events_to_occur);
	var i;
	for(i=0;i<div_count;i++){
		
		var div = document.createElement("div");
		//div.style.width = "100px";
		//div.style.height = "100px";
		//div.style.background = "lightgrey";
		div.style.border= "0.1px solid lightgrey";
			div.style.borderRadius= "6px";
		div.id="div"+i;
		//alert(div.id);
		div.style.align="center";
		var temp="div"+i;
		//alert(temp);
		//var y=document.getElementById("temp");
		//document.getElementById('temp').addEventListener("click", function(){alert('q');});	//pass id
		//div.onClick="myFunction()"
		div.className="row col-sm-10 panel panel-default abcd";
		div.innerHTML= "EventName<br>";
		div.innerHTML+= "Date<br>";
		div.innerHTML+= "Location";
		div.setAttribute("name","Event1");
		div.setAttribute("date",'date1');
		div.setAttribute("location",'location1');									
		var b=document.createElement("button");
		b.id="button"+i;
		b.innerHTML="Edit";
		b.style="";
		b.class="btn-default";
		b.addEventListener("click",editbutton);
		//alert(b.id);
		document.getElementById("approved").appendChild(div);
		document.getElementById(temp).innerHTML+="</div><br><br>";
		document.getElementById(temp).appendChild(b);
					//document.getElementById("approved").innerHTML+="<br>";
	}
	
	
	
//	var i;
//	for(i=0;i<live_event_count;i++){
//		
//		var div = document.createElement("div");
//		div.style.border= "0.1px solid #F0EDED";
//		div.style="background-color:white;font-family: 'Open Sans', sans-serif;";
//			div.style.borderRadius= "6px";
//		var p="div"+i;
//		div.id=p;
//		div.name=p;
//		var atname=events_to_occur[i].event.eventCategory.name
//		div.setAttribute('category',atname);
//		div.style.align="center";
//		div.className="row col-sm-12 panel panel-default abc";
//		var display_date;
//		var day=String(events_to_occur[i].event.startDate[8])+String(events_to_occur[i].event.startDate[9]);
//		var month=String(events_to_occur[i].event.startDate[5])+String(events_to_occur[i].event.startDate[6]);
//		if(month=="01")
//		{	display_date="Date: Jan "+day;	}
//		if(month=="02")
//		{	display_date="Date: Feb "+day;	}
//		if(month=="03")
//		{	display_date="Date: Mar "+day;	}
//		if(month=="04")
//		{	display_date="Date: Apr "+day;	}
//		if(month=="05")
//		{	display_date="Date: May "+day;	}
//		if(month=="06")
//		{	display_date="Date: Jun "+day;	}
//		if(month=="07")
//		{	display_date="Date: Jul "+day;	}
//		if(month=="08")
//		{	display_date="Date: Aug "+day;	}
//		if(month=="09")
//		{	display_date="Date: Sep "+day;	}
//		if(month=="10")
//		{	display_date="Date: Oct "+day;	}
//		if(month=="11")
//		{	display_date="Date: Nov "+day;	}
//		if(month=="12")
//		{	display_date="Date: Dec "+day;	}
//		div.innerHTML+= "<h5>"+events_to_occur[i].event.eventTitle+"</h5>"+display_date+"<br>"+events_to_occur[i].event.location+"<br>";
//
//		var b=document.createElement("button");
//		b.id="button"+i;
//		b.class="btn-default";
//		b.innerHTML=" Edit Event";
//		b.style="background-color:red;color:white;border:1px solid red;border-radius:4px;"
//		
//		document.getElementById("approved").appendChild(div);
//		document.getElementById(p).innerHTML+="<br><br>";
//		document.getElementById(p).appendChild(b);
	//document.getElementById("div1").style.display="none";
		
//		var div = document.createElement("div");
//		//div.style.width = "100px";
//		//div.style.height = "100px";
//		//div.style.background = "lightgrey";
//		div.style.border= "1px solid lightgrey";
//			div.style.borderRadius= "6px";
//		div.id="div"+i;
//		//alert(div.id);
//		div.style.align="center";
//		var temp="div"+i;
//		//alert(temp);
//		//var y=document.getElementById("temp");
//		//document.getElementById('temp').addEventListener("click", function(){alert('q');});	//pass id
//		//div.onClick="myFunction()"
//		div.className="row col-sm-10 panel panel-default abcd";
//		div.innerHTML= "EventName<br>";
//		div.innerHTML+= "Date<br>";
//		div.innerHTML+= "Location";
//		div.setAttribute("name","Event1");
//		div.setAttribute("date",'date1');
//		div.setAttribute("location",'location1');									
//		var b=document.createElement("button");
//		b.id="button"+i;
//		b.innerHTML="Edit";
//		b.style="";
//		b.class="btn-default";
//		b.addEventListener("click",editbutton);
//		//alert(b.id);
//		document.getElementById("approved").appendChild(div);
//		document.getElementById(temp).innerHTML+="<br><br>";
//		document.getElementById(temp).appendChild(b);
//					//document.getElementById("approved").innerHTML+="<br>";
//	}
});

// Load google charts

		

	
	function editbutton(id)
	{
	// call edit page via controller and pass event id via edit button
	// on editpage get id and autofill page via retrieving data from controller
		//alert(this.id);
	var x=this.parentElement.getAttribute("name");
	//alert(x);
		var modal = document.getElementById('myModal');
	   modal.style.display = "block";
	   document.getElementById("modal_name").value=x;
	   // Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 


// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "block";
    }
    
}

	}
	


// Get the button that opens the modal
var btn = document.getElementById("myBtn");