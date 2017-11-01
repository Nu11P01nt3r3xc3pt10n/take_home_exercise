function search_elements(){
	alert(document.getElementById("pickup_code").value);
	$.post("http://localhost:8080/quotes",
			{
				'pickup_code': document.getElementById("pickup_code").value, 
				'delivery_code':document.getElementById("delivery_code").value, 
				'vehicle':document.getElementById("vehicle").value
			},
			function(data, status){
//				alert("Data: " + data + "\nStatus: " + status);
				location.reload();
				alert(document.getElementById("tmp_solution").innerHTML);
			});
//	$.ajax({
//	url: 'http://localhost:8080/quotes',
//	type: "POST",
//	data:{ 
//	'pickup_code': document.getElementById("pickup_code").value, 
//	'delivery_code':document.getElementById("delivery_code").value, 
//	'vehicle':document.getElementById("vehicle").value
//	},
//	function(data){
//	alert('wow' + data);
//	alert(documet.getElementById("tmp_solution").innerHTML);

//	documet.getElementById("_results").innerHTML=result;
//	}
//	});

}