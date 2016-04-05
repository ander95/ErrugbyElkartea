<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erregistro orria</title>
<link href="/ErrugbyElkartea/css/styleSheet.css" rel="stylesheet" />
</head>
<body>
	<header>
		<h1>Errugby lizentzia lortzeko web aplikazioa</h1>
		<h3>Erregistro formularioa</h3>
		<img src="http://vinilos.habitaka.com/125-260-thickbox/Array.jpg" alt="rugby ball" style="width:180px;height:128px;">
	</header>
	<section>
		<form method="POST" action="/ErrugbyElkartea/servlet/SignUpServlet">
			<table>
				<tr>
					<td>Erabiltzaile izena:</td>
					<td><input name="ErabiltzaileIzena" /></td>
				</tr>
				<tr>
					<td>Pasahitza:</td>
					<td><input type="password" name="Pasahitza" /></td>
				</tr>
				<tr>
					<td>Izena:</td>
					<td><input name="Izena" /></td>
				</tr>
				<tr>
					<td>Abizena:</td>
					<td><input name="Abizena" /></td>
				</tr>
				<tr>
					<td>Email-a:</td>
					<td><input type="text" name="Email" /></td>
				</tr>
				<tr>
					<td>Kalea:</td>
					<td><input name="Kalea" /></td>
				</tr>
				<tr>
					<td>Herria:</td>
					<td><input name="Herria" /></td>
				</tr>
				<tr>
					<td>Posta kodea:</td>
					<td><input type="number" name="PostaKodea" /></td>
				</tr>
				<tr>
					<td>Jaiotze-data:</td>
					<td><input type="date" name="JaiotzeData" /></td>
				</tr>
				<tr>
					<td>Telefonoa:</td>
					<td><input type="number" name="Telefonoa" /></td>
				</tr>
			</table>
			<br>
			<div style="text-align: center;">
				<button>Bidali</button>
			</div>
		</form>
	</section>
	<% if ((boolean)request.getAttribute("empty_value_error")){ %>
		<section>
		<div style="text-align: center;">
			<h1>ERROR!</h1>
			<h3>Erregistratzeko datu guztiak sartu behar dituzu!</h3>
		</div>
		</section>
	<% } %>
	<% if ((boolean)request.getAttribute("signup_error")){ %>
		<section>
			<h1>ERROR!</h1>
			<h3>Email hori beste erabiltzaile batek erabiltzen du!</h3>
		</section>	
	<% } %>
	<section>
		<a href="/ErrugbyElkartea/html/loginForm.html"
			style="text-decoration: none"> <font color="white">Atzera</font>
		</a>
	</section>
	<footer>Web Sistemak - EUITI Bilbao
	<img src="http://freepngimages.com/wp-content/uploads/2015/10/vintage-rugby-ball.png" alt="rugby ball" style="width:180px;height:80px;">
	</footer>
</body>
</html>