/**
 * 
 */

var user;
var users;

$(document).ready(function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var dto = JSON.parse(xhr.responseText);
			user = dto.user;
			users = dto.users;
			
			document.getElementById('welcome').innerHTML = "Hello: " + user.firstname + " " + user.lastname + "\n";
			loadTable();
			document.getElementById('submitAddUser').addEventListener("click", addUser);
		}
	}
	xhr.open("GET", "getEmployees", true);
	xhr.send();
})


function loadTable(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var dto = JSON.parse(xhr.responseText);
			user = dto.user;
			users = dto.users;
			
			//$('#view').html(xhr2.responseText);
			
			//for loop start
			let tr = "";
			
				for (let i = 0; i < users.length; i++) {
					tr += '<tr><td>'+ users[i].firstname + '</td>';
					tr += '<td>'+ users[i].lastname + '</td>';
					tr += '<td>'+ users[i].email + '</td>';
					tr += '<td>'+ users[i].password + '</td></tr>';
				}
			
				$('#usdata').append(tr);
				//end loop
			
			$('#usersTable').DataTable();
		}
	}
	xhr.open("GET", "getEmployees", true);
	xhr.send(); 
};

function addUser() {
	var xhr = new XMLHttpRequest();
	var ufname = $('#firstname').val();
	var ulname = $('#lastname').val();
	var email  = $('#email').val();
	var pass   = $('#password').val();
	
	var auser = [ufname, ulname, email, pass];
	
	auser = JSON.stringify(auser);
	
	xhr.open("POST", "addUser", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(auser);
};