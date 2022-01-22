<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand text-decoration-none" href="#">Products and Categories</a>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
		<span class="navbar-toggler-icon"></span>
		</button>
		
		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link active" href="/persons/new">Dashboard</a>
				</li>
			</ul>
		</div>
	</nav>
	<main>
		<h1>New Product</h1>
		<form:form action="/addProduct" method="POST" modelAttribute="product">
			<div class="form-group row mx-0">
				<form:label path="name">Name:</form:label>
				<div class="col-sm-4">
					<form:input type="text" path="name"/>
				</div>
			</div>
			<div class="form-group row mx-0">
				<form:label path="description">Description:</form:label>
				<div class="col-sm-4">
					<form:textarea path="description" rows="3" col="10"/>
				</div>
			</div>
			<div class="form-group row mx-0">
				<form:label path="price">Price:</form:label>
				<div class="col-sm-4">
					<form:input type="number" path="price"/>
				</div>
			</div>
			<div class="form-group row mx-0">
				<div class="col-sm-4 offset-sm-1">
					<input class="btn btn-success" type="submit" value="Create"/>
				</div>
			</div>
		</form:form>
	</main>
</body>
</html>