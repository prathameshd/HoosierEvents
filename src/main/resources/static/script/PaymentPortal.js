var event_name;
var bronze;
var silver;
var gold;
var total_cost;

window.onload = function() {
	  
	var parameters = location.search.substring(1).split("&");
    var temp = parameters[0].split("=");
    console.log(temp[1]);
    console.log(unescape(temp[1]));
    var event_name_coded = unescape(temp[1]);
    event_name = event_name_coded.replace('+', ' ');
    temp = parameters[1].split("=");
    bronze = decodeURIComponent(temp[1]);
    temp = parameters[2].split("=");
    silver = decodeURIComponent(temp[1]);
    temp = parameters[3].split("=");
    gold = decodeURIComponent(temp[1]);
    $.ajax({
		type:'POST',
		data: {eventTitle:event_name , tickets_bronze:bronze, tickets_silver:silver, tickets_gold:gold},
		url:"/getTicketPrice",
		  success: function(data) {
				console.log('success',data);
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
											total : '1.00',
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
	
	
	
//	var url = document.location.href,
//    params = url.split('?')[1].split('&'),
//    b_params = url.split('&')[2].split('&'),
//    s_params = url.split('&')[3].split('&'),
//    g_params = url.split('&')[4].split('&'),
//    data = {}, b_data = {}, s_data = {}, g_data = {},tmp;
////	console.log(params);
////	console.log(b_params);
////	console.log(s_params);
////	console.log(g_params);
//	for (var i = 0, l = params.length; i < l; i++) {
//	     tmp = params[i].split('=');
//	     data[tmp[0]] = tmp[1];
//	}
//	for (var i = 0, l = b_params.length; i < l; i++) {
//	     tmp = b_params[i].split('=');
//	     b_data[tmp[0]] = tmp[1];
//	}
//	for (var i = 0, l = params.length; i < l; i++) {
//	     tmp = s_params[i].split('=');
//	     s_data[tmp[0]] = tmp[1];
//	}
//	for (var i = 0, l = params.length; i < l; i++) {
//	     tmp = g_params[i].split('=');
//	     g_data[tmp[0]] = tmp[1];
//	}
//	event_name = decodeURIComponent(data.event_name);
//	var bronze = decodeURIComponent(b_data.bronze);
//	var silver = decodeURIComponent(s_data.silver);
//	var gold = decodeURIComponent(g_data.gold);
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