var event_name;

//window.onload = function () {
//    var url = document.location.href,
//        params = url.split('?')[1].split('&'),
//        data = {}, tmp;
//    for (var i = 0, l = params.length; i < l; i++) {
//         tmp = params[i].split('=');
//         data[tmp[0]] = tmp[1];
//    }
//    event_name = decodeURIComponent(data.name);
//    document.getElementById('here').innerHTML = event_name;
//}

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
	
	var url = document.location.href,
    params = url.split('?')[1].split('&'),
    data = {}, tmp;
	for (var i = 0, l = params.length; i < l; i++) {
	     tmp = params[i].split('=');
	     data[tmp[0]] = tmp[1];
	}
	event_name = decodeURIComponent(data.name);
	
	$.ajax({
		type:'POST',
		data: {eventTitle:event_name},
		url:"/saveEventTicket",
		  success: function(data) {
				console.log('success',data);
				window.location.href = 'HomePage.html';
		   },
		   error:function(exception){alert('Exception:'+exception);}})
		   
}