<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.json.JSONObject"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="scripts/general_scripts.js"></script>
<title>quotes</title>
</head>
<body>

	<div style="text-align: center; width: 80%; margin: auto;">

		<h2>GET QUOTES</h2>


		<form action="http://localhost:8080/quotes" method="POST">
			<label
				style="width: 40%; display: inline-block; margin: auto; text-align: left;">Insert
				pickup zip code</label> <input
				style="width: 20%; display: inline-block; margin: auto;" type="text"
				name="pickup_code" /> <br> <br> <label
				style="width: 40%; display: inline-block; margin: auto; text-align: left;">Insert
				delivery zip code</label> <input
				style="width: 20%; display: inline-block; margin: auto;" type="text"
				name="delivery_code" /> <br> <br> <label
				style="width: 40%; display: inline-block; margin: auto; text-align: left;">Insert
				vehicle type</label> <select
				style="width: 20%; display: inline-block; margin: auto;"
				name="vehicle">
				<c:forEach var="item" items="${vehicle_list}">
					<option value="${item}">${item}</option>
				</c:forEach>
			</select> <br> <br> <input type="submit"
				style="width: 50%; border: none; font-size: 1.5em;" value="Search" />
			<br> <br>
		</form>

		<c:if test="${not empty solutions}">

			<div
				style="background-color: rgba(255, 0, 255, 0.5); width: 100%; color: rgba(255, 255, 255, 1);">

				<div style="display: inline-block; width: 30%;">From:"${from}"</div>
				<div style="display: inline-block; width: 30%;">To:"${to}"</div>
				<div style="display: inline-block; width: 30%;">Vehicle:"${vehicle_selected}"</div>

			</div>

			<br>
			<br>
			<br>

			<div
				style="background-color: rgba(0, 0, 255, 0.5); width: 100%; color: rgba(255, 255, 255, 1);">

				<div style="display: inline-block; width: 30%;">Company</div>
				<div style="display: inline-block; width: 30%;">Average Time</div>
				<div style="display: inline-block; width: 30%;">Price</div>

			</div>
			<div id="tmp_solution"
				style="background-color: rgba(0, 255, 0, 0.5); width: 100%;">
				<c:forEach var="listValue" items="${solutions}">
					<div style="display: inline-block; width: 30%;">${listValue.getString("service")}</div>
					<div style="display: inline-block; width: 30%;">${listValue.getInt("delivery_time")}</div>
					<div style="display: inline-block; width: 30%;">${listValue.getInt("price")}</div>
					<br>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${not empty errors}">

			<div id="errors"
				style="background-color: rgba(255, 0, 0, 0.5); width: 100%;">
				<c:forEach var="listValue" items="${errors}">
					<div style="display: inline-block; width: 50%;">${listValue}</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</body>
</html>