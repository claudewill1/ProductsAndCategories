<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Home</title>
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
					<a class="nav-link active" href="/">Dashboard</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" href="/category/new">New Category</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" href="/product/new">New Product</a>
				</li>
			</ul>
		</div>
	</nav>
	<h1><c:out value="${selectedProduct.name}"/></h1>
	<main class="mt-3 d-flex flex-row">
		
		<div class="col-6 flex-left">
			<h4>Products</h4>
			<ul class="offset-1">
				<c:forEach items="${product.categories}" var="category">
					<li><c:out value="${category.name}"/></li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-3">
				<form:form action="/products/addCategory" method="POST" modelAttribute="productCategory">
			    
 			    	<!--  Hidden input for Product ID -->
				    <form:input type="hidden" path="product" value="${ product.id }"/>
				    
					<!--  Category Selection Field -->
			        <div class="form-group col-9">
			        	<form:label class="col-form-label" path="category">Add Category:</form:label>
			        	<form:select class="form-control" path="category">
				        	<c:forEach items="${categoriesNotListed}" var="nonCat">
				        		<form:option value="${nonCat.id}">${nonCat.name}</form:option>
				        	</c:forEach>
			        	</form:select>
			        	<form:errors class="small" path="category"/>
			        </div>
				    
					<!--  Submit Button -->
				    <div class="form-group mx-0">
				    	<div class="col-3 offset-5">
				    		<input class = "btn btn-success" type="submit" value="Add"/>
				    	</div>	
			    	</div>
			    </form:form>	
			</div>
	</main>
</body>
</html>
</body>
</html>