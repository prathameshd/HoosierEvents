/**
 * 
 */
function saveEvent() {
	
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
         }
    };
    xhttp.open("POST", "saveEvent", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    Object data = { eventTitle: document.getElementById("event_name").value};
    xhttp.send(data);
}
