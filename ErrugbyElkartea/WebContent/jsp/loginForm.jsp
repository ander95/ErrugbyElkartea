<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login orria</title>
<link href="/ErrugbyElkartea/css/styleSheet.css" rel="stylesheet" />
</head>
<body>
	<header>
	<h1>Errugby lizentzia lortzeko web aplikazioa</h1>
	<h3>Login formularioa</h3>
	<img src="http://vinilos.habitaka.com/125-260-thickbox/Array.jpg" alt="rugby ball" style="width:180px;height:128px;">
	</header>
	<section>
	<form method="POST" action="/ErrugbyElkartea/servlet/LogInServlet">
		<table>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="Email" /></td>
			</tr>
			<tr>
				<td>Pasahitza:</td>
				<td><input type="password" name="Password" /></td>
			</tr>
		</table>
		<br>
		<div style="text-align: center;">
			<button>Bidali</button>
		</div>
	</form>
	</section>
	<% if ((boolean)request.getAttribute("login_error")){ %>
	<section>
	<div style="text-align: center;">
	<h1>LOGIN ERROR!</h1>
	<h3>Erabiltzaile edo pasahitz okerra!</h3>
	</div>
	</section>
	<% } %>
	<section>
	<div style="text-align: center;">
		<a href="/ErrugbyElkartea/html/signupForm.html"
			style="text-decoration: none"> <font color="white">Erregistratu</font>
		</a>
	</div>
	</section>
	<footer>Web Sistemak - EUITI Bilbao
	<img src="http://freepngimages.com/wp-content/uploads/2015/10/vintage-rugby-ball.png" alt="rugby ball" style="width:180px;height:80px;">
	</footer>
</body>
</html>