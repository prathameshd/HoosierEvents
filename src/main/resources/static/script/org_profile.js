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
			  live_event_count=data.length;
    		  //document.getElementById("total_events").innerHTML=data.length; 
    		  eventsRender(data, "upcoming");
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
    		  //document.getElementById("pending_events").innerHTML=data.length; 
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
	
});

//____________________AUTHOR HARISH_________________________________

function eventsRender(events, parentDiv) {
	var i;
	var divCount = events.length;
	for(i=0;i<divCount;i++){
		var currentEvent = events[i];
		var div = document.createElement("div");
		div.style.border= "0.1px solid lightgrey";
		div.style.borderRadius= "6px";
		var temp="div"+events[i].id;
		div.id=temp;
		div.style.align="center";
		div.className="row col-sm-10 panel panel-default abcd";
		div.innerHTML= "EventName : " + currentEvent.eventTitle + "<br>";
		div.innerHTML+= "Date : "+ currentEvent.startDate + "<br>";
		div.innerHTML+= "Location : "+ currentEvent.location + "&nbsp; Category : "+ currentEvent.eventCategory.name + "<br/><br/>";
		div.setAttribute("eventId", currentEvent.id);
		div.setAttribute("name", currentEvent.eventTitle);
		div.setAttribute("description", currentEvent.description);
		div.setAttribute("location", currentEvent.location);
		document.getElementById(parentDiv).appendChild(div);
		var b=document.createElement("button");
		b.id=events[i].id;
		b.innerHTML="Edit";
		b.style="";
		b.class="btn-default";
		b.addEventListener("click", editbutton);
		document.getElementById(temp).appendChild(b);
		var b2=document.createElement("button");
		b2.id=events[i].id;
		b2.innerHTML="Drop Event";
		b2.style="";
		b2.class="btn-default";
		b2.addEventListener("click", deleteEvent);
		document.getElementById(temp).appendChild(b2);
	}
}

function deleteEvent() {
//	var getEventDiv= document.getElementById("div"+this.id);
//	var modal = document.getElementById('myModal');
//	modal.style.display = "block";
//
//	document.getElementById("edit_name").value = getEventDiv.getAttribute("name");
//	document.getElementById("edit_name").value = getEventDiv.getAttribute("date");
//	document.getElementById("edit_name").value = getEventDiv.getAttribute("location");
//
//	var span = document.getElementsByClassName("close")[0];
//	span.onclick = function() {
//		modal.style.display = "none";
//	}
//
//	window.onclick = function(event) {
//	    if (event.target == modal) {
//	        modal.style.display = "block";
//	    }
//	 }
}

function editbutton() {
	var getEventDiv= document.getElementById("div"+this.id);
	var modal = document.getElementById('myModal');
	modal.style.display = "block";

	document.getElementById("edit_name").value = getEventDiv.getAttribute("name");
	document.getElementById("edit_description").value = getEventDiv.getAttribute("description");
	document.getElementById("edit_location").value = getEventDiv.getAttribute("location");
	document.getElementById("eventId").value = getEventDiv.getAttribute("eventId");

	var span = document.getElementsByClassName("close")[0];
	span.onclick = function() {
		modal.style.display = "none";
	}

	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "block";
	    }
	 }
}

function updateEvent(){
	$.ajax({
		type: "POST",
        url: '/updateEvent',
        data: {id:document.getElementById("eventId").value, eventTitle: document.getElementById("edit_name").value, description: document.getElementById("edit_description").value, location: document.getElementById("edit_location").value},
        success: function (data) {
        	var modal = document.getElementById('myModal');
        	modal.style.display = "none";
        	alert(data);
        },
        error: function (e) {
            console.log(e);
        }
	});
}
//_____________________HARISH_______________________________________
		

	

	


// Get the button that opens the modal
var btn = document.getElementById("myBtn");