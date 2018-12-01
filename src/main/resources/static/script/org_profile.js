
var count=50;
var div_count=6;
var events_to_be_attended;
$(document).ready(function(){
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getFutureEventsByOrganizer",
    	  success: function(data) {
    		  console.log('successEvents',data);
			  var live_event_count=data.length;
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
		   },
		  error:function(exception){alert('Exception:'+exception);}
		});
	
	$.ajax({
    	type:'POST',
    	dataType:'json',
    	url:"/getEventsByOrganizerToBeApproved",
    	  success: function(data) {
    		  console.log('successEvents',data);
			  var pending_event_count=data.length;
    		  document.getElementById("pending_events").innerHTML=data.length; 
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
});

// Load google charts

		

	
	function editbutton(id)
	{
	// call edit page via controller and pass event id via edit button
	// on editpage get id and autofill page via retrieving data from controller
		alert(this.id);
	var x=this.parentElement.getAttribute("name");
	alert(x);
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