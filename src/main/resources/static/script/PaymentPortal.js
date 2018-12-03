var event_name;
var bronze;
var silver;
var gold;
var total_cost;
var disc_cost;

window.onload = function() {
	  
	var parameters = location.search.substring(1).split("&");
    var temp = parameters[0].split("=");
    console.log(temp[1]);
    console.log(unescape(temp[1]));
    var event_name_coded = unescape(temp[1]);
    event_name = event_name_coded.replace(/\+/g, ' ');
    console.log(event_name);
    temp = parameters[1].split("=");
    bronze = decodeURIComponent(temp[1]);
    temp = parameters[2].split("=");
    silver = decodeURIComponent(temp[1]);
    temp = parameters[3].split("=");
    gold = decodeURIComponent(temp[1]);
    $.ajax({
		type:'POST',
		dataType:'text',
		data: {eventTitle:event_name , tickets_bronze:bronze, tickets_silver:silver, tickets_gold:gold},
		url:"/checkIfStudent",
		  success: function(data) {
				console.log('success',data);
				if(data == "true"){
//					total_cost = total_cost/2;
//					disc_cost = total_cost/2;
//					console.log(total_cost);
//					console.log(disc_cost);
					document.getElementById("discount").innerHTML = "<h4>50% Student Discount Applied</h4>";	
				}
				
		   },
		   error:function(exception){alert('Exception:'+exception);}})
    $.ajax({
		type:'POST',
		dataType:'JSON',
		data: {eventTitle:event_name , tickets_bronze:bronze, tickets_silver:silver, tickets_gold:gold},
		url:"/getTicketPrice",
		  success: function(data) {
				console.log('success',data);
				total_cost = parseInt(data);
				document.getElementById("here").innerHTML = "<h3>Your total cost is: $"+data+"</h3>";
		   },
		   error:function(exception){alert('Exception:'+exception);}})
    
};

// Render the PayPal button
paypal.Button.render({
	
						
						// Set your environment
						env : 'sandbox', // sandbox | production

						// Specify the style of the button
						style : {
							layout : 'vertical', // horizontal | vertical
							size : 'medium', // medium | large | responsive
							shape : 'rect', // pill | rect
							color : 'gold' // gold | blue | silver | white | black
						},

						// Specify allowed and disallowed funding sources
						//
						// Options:
						// - paypal.FUNDING.CARD
						// - paypal.FUNDING.CREDIT
						// - paypal.FUNDING.ELV
						funding : {
							allowed : [ paypal.FUNDING.CARD,
									paypal.FUNDING.CREDIT ],
							disallowed : []
						},

						// PayPal Client IDs - replace with your own
						// Create a PayPal app: https://developer.paypal.com/developer/applications/create
						client : {
							sandbox : 'AQLepnBdVqmuvr05Yc2K_yHJuRqCXTmaWHgjEdjc7a62MIMky-BDQx3W-hj4D63MW4QsrfaTTmGN3lTf',
							production : '<insert production client id>'
						},

						payment : function(data, actions) {
							return actions.payment.create({
								payment : {
									transactions : [ {
										amount : {
											total : total_cost,
											currency : 'USD'
										}
									} ]
								}
							});
						},

						onAuthorize : function(data, actions) {
							return actions.payment.execute().then(function() {
								alert("Payment Complete!") 
								SaveEventTicket();		
							});
						}
					}, '#paypal-button-container');

function SaveEventTicket(){
	
	console.log(event_name);
	console.log(bronze);
	console.log(silver);
	console.log(gold);
	
	$.ajax({
		type:'POST',
		data: {eventTitle:event_name , tickets_bronze:bronze, tickets_silver:silver, tickets_gold:gold},
		url:"/saveEventTicket",
		  success: function(data) {
				console.log('success',data);
				window.location.href = '/homepage';
		   },
		   error:function(exception){alert('Exception:'+exception);}})
		   
}