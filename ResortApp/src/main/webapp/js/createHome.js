/**
 * 
 */

var user;

$(document).ready(function(){
	//console.log("Inside of getUserInformation()");
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			var dto = JSON.parse(xhr.responseText);
			user = dto;
			
			document.getElementById('welcome').innerHTML = "Hello: " + dto.firstname + " " + dto.lastname + "\n";
			loadTable();
		}
	}
	xhr.open("GET", "getUserInfo", true);
	xhr.send();
});

function loadTable(){

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			
			$('#view').html(xhr.responseText);
			
			//for loop start
			var tr = "";
			
			tr += '<tr><td>'+ user.firstname + '</td>';
			tr += '<td>'+ user.lastname + '</td>';
			tr += '<td>'+ user.email + '</td>';
			tr += '<td>'+ user.password + '</td></tr>';
			
			$('#udata').append(tr);
			//end loop
			
			$('#userTable').DataTable();
		}
	}
	xhr.open("GET", "getDashboard", true);
	xhr.send(); 
};