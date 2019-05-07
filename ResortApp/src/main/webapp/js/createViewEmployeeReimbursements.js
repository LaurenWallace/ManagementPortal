/**
 * 
 */

var user;
var reims;
var status = ["Pending", "Approved", "Denied"];

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
	xhr.open("GET", "getEmployeeReimbursements", true);
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
				tr += '<td id="rid">'+ reims.reimburseId + '</td>';
				tr += '<td>'+ getSDate(reims.sdate) + '</td>';
				tr += '<td>'+ reims.reqName + '</td>';
				tr += '<td>'+ reims.typeName + '</td>';
				tr += '<td class="stat">'+ reims.statName + '</td>';
				tr += '<td>'+ reims.amount + '</td>';
				tr += '<td>'+ reims.reason + '</td>';
				tr += '<td>' + isReceiptNull(reims.receipt) + '</td>';
				tr += '<td>'+ isNameNull(reims.resName) + '</td>';
				tr += '<td>'+ isNull(reims.rNotes) + '</td>';
				tr += '<td>'+ getRDate(reims.rdate) + '</td>';
				tr += '</tr>';
			
				$('#empreimdata').append(tr);
				//end loop
			});
			$('#reimsTable').DataTable();
			
			/*var changes = "";
			for(let p=0; p < reims.length; reims++) {
				changes+="<option id=\"option"+i+"\" name=\"ridoption\" value="+reims[i].reimburseId+">"+reims[i].reimburseId+"</option><br/>";
			}
			options.innerHTML = changes;*/
			
		}
	}
	 //viewEmployeeReimbursement
	xhr.open("GET", "getEmployeeReimbursements", true);
	xhr.send(); 
};

/*$('#reimsTable empreimdata').on('click', '.stat', function() {
    var row = this.parentElement;
    if (!$('#reimsTable').hasClass("editing")) {
        $('#reimsTable').addClass("editing");
        var data = table.row(row).data();
        var $row = $(row);
      //the td:nth-child(#) should have the number of the column you
      //want to make available to edit
        var thisStatus = $row.find("td:nth-child(5)"); 
        var thisStatusText = thisStatus.text();
        thisStatus.empty().append($("<select></select>", {
            "id": "status_" + data[0],
            "class": "changeStatus"
        }).append(function() {
            var options = [];
            $.each(status, function(k, v) {
                options.push($("<option></option>", {
                    "text": v,
                    "value": v
                }))
            })
            return options;
        }));
        $("#status_" + data[0]).val(thisStatusText)
    }
});

$('#reimsTable empreimdata').on("change", ".changeStatus", function() {
	var $this = $(this);
	var tempData = table.row($this.closest("tr")).data().slice();
    tempData[5] = $this.val();
    table.row($this.closest("tr")).data(tempData);
    $this.parent("td").empty().text($this.val());
    var thisReimId = $row.find("td:nth-child(1)");
    updateStat(thisReimId, $this.val);
    $('#reimsTable').removeClass("editing");
});

function updateStat(rd, tv) {
	var xhr = new XMLHttpRequest();
	var st = [rd, tv];
	
	st = JSON.stringify(st);
	
	xhr.open("POST", "updateStatus", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(st);
}*/

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