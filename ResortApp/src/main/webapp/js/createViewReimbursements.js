/**
 * 
 */

var user;
var reims;

$(document).ready(function(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			var dto = JSON.parse(xhr.responseText);
			user = dto.user;
			reims = dto.reims;
			
			document.getElementById('welcome').innerHTML = "Hello: " + user.firstname + " " + user.lastname + "\n";
			loadTable();
		}
	}
	xhr.open("GET", "getReimbursements", true);
	xhr.send();
})

function loadTable(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			var dto = JSON.parse(xhr.responseText);
			user = dto.user;
			reims = dto.reims;
			
			//$('#view').html(xhr.responseText);
			
			//for loop start
			//for(let i = 0; i < reims.length; i++) {
			$.each(reims, function(i, reims) {
				var tr = '<tr>';
				tr += '<td>'+ reims.reimburseId + '</td>';
				tr += '<td>'+ getSDate(reims.sdate) + '</td>';
				tr += '<td>'+ reims.typeName + '</td>';
				tr += '<td>'+ reims.statName + '</td>';
				tr += '<td>'+ reims.amount + '</td>';
				tr += '<td>'+ reims.reason + '</td>';
				tr += '<td>' + isReceiptNull(reims.receipt) + '</td>';
				tr += '<td>'+ isNameNull(reims.resName) + '</td>';
				tr += '<td>'+ isNull(reims.rNotes) + '</td>';
				tr += '<td>'+ isNull(reims.rdate) + '</td>';
				tr += '</tr>';
			
				$('#reimdata').append(tr);
				//end loop
			});
			$('#reimTable').DataTable();
		}
	}
	 //viewEmployeeReimbursement
	xhr.open("GET", "getReimbursements", true);
	xhr.send(); 
};

/*function addReasonReimbursement() {
	var xhr = new XMLHttpRequest();
	var reason = $('#firstname').val();
	var amount = $('#lastname').val();
	var rtype  = $('#email').val();
	
	var nreim = [reason, amount, rtype];
	
	nreim = JSON.stringify(nreim);
	
	xhr.open("POST", "addReimbursement", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(nreim);
};

function addDetailedReimbursement() {
	var xhr = new XMLHttpRequest();
	var reason = $('#reason').val();
	var amount = $('#amount').val();
	var receipt = $('#receipt').val();
	var rtype  = $('#type').val();
	
	var nreim = [reason, amount, receipt, rtype];
	
	nreim = JSON.stringify(nreim);
	
	xhr.open("POST", "addDetailReimbursement", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(nreim);
};*/

function getSDate(s) {
	let stime = s;
	let startd = new Date(stime);
	let smonth = (startd.getMonth() + 1);
	let stdate = (smonth.length > 1 ? smonth: "0" + smonth) + "/" + startd.getDate() + "/" + startd.getFullYear();
	return stdate;
}

function isReceiptNull(r) {
	if(r !== null) {
		return '<a href="'+ r +'">' + r + '</a>';
	 } else {
		 return "-";
	 }
}

function isNameNull(d) {
	if(d !== "null null") {
		 return d;
	 } else {
		 return "-";
	 }
}

function isNull(d) {
	if(d !== null) {
		 return d;
	 } else {
		 return "-";
	 }
}

function getRDate(r) {
		if(r !== null) {
			let rtime = r;
			var resdate = new Date(rtime);
			var rmonth = (resdate.getMonth() + 1);
			var rsdate = (rmonth.length > 1 ? rmonth: "0" + rmonth) + "/" + resdate.getDate() + "/" + resdate.getFullYear();
		
			return rsdate;
		} else {
			return "-";
		}
}