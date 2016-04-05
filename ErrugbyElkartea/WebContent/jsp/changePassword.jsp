<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pasahitz aldaketa</title>
<link href="/ErrugbyElkartea/css/styleSheet.css" rel="stylesheet" />
</head>
<body>
	<header>
	<h1>Errugby lizentzia lortzeko web aplikazioa</h1>
	<h3>Pasahitz aldaketa</h3>
	<img src="http://vinilos.habitaka.com/125-260-thickbox/Array.jpg" alt="rugby ball" style="width:180px;height:128px;">
	</header>
	<section>
	<div style="text-align: right;">
		<a href="/ErrugbyElkartea/servlet/PassAldatuServlet?action=logout"
			style="text-decoration: none"> <font color="white">Logout</font>
		</a>
	</div>
	</section>
	<section>
	<form method="POST" action="/ErrugbyElkartea/servlet/PassAldatuServlet">
		<table>
			<tr>
				<td>Pasahitz zaharra:</td>
				<td><input type="password" name="OldPass" /></td>
			</tr>
			<tr>
				<td>Pasahitz berria:</td>
				<td><input type="password" name="NewPass" /></td>
			</tr>
			<tr>
				<td>Pasahitz berria errepikatu:</td>
				<td><input type="password" name="NewPass2" /></td>
			</tr>
		</table>
		<br>
		<div style="text-align: center;">
			<button>Bidali</button>
		</div>
	</form>
	</section>
	<% if ((boolean)request.getAttribute("old_password_error")){ %>
	<section>
	<div style="text-align: center;">
		<h1>ERROR!</h1>
		<h3>Hori ez da zure pasahitz zaharra!</h3>
	</div>
	</section>
	<% } %>
	<% if ((boolean)request.getAttribute("password_error")){ %>
	<section>
	<div style="text-align: center;">
		<h1>ERROR!</h1>
		<h3>Pasahitz berria ondo idatzi!</h3>
	</div>
	</section>
	<% } %>
	<section> <a href="/ErrugbyElkartea/html/mainPage.html"
		style="text-decoration: none"> <font color="white">Atzera</font>
	</a> </section>
	<footer>Web Sistemak - EUITI Bilbao
	<img src="http://freepngimages.com/wp-content/uploads/2015/10/vintage-rugby-ball.png" alt="rugby ball" style="width:180px;height:80px;">
	</footer>
</body>
</html>