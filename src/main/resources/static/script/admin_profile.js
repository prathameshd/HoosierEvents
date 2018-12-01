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
	
