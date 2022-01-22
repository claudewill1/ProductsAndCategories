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
					<a class="nav-link active" href="/category/new">New Category</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" href="/product/new">New Product</a>
				</li>
			</ul>
		</div>
	</nav>
	<main>
		<h1>All Products and Related Categories</h1>
		<div class="row">
			<div class="col-9">
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Price</th>
							<th>Categories</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="product">
							<tr>
								<td>${product.name}</td>
								<td>${product.price}</td>
								<td>
									<ul>
										<c:forEach items="${product.categories}" var="category">
											<li>${category.name}</li>
										</c:forEach>
									</ul>
								</td>
								<td>
									<a class="btn btn-success" href="/products/${product.id}">Show</a>
									<a class="btn btn-danger" href="/products/delete/${product.id}">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<h3>All Categories and Related Products</h3>
		<div class="row">
			<div class="col-9">
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th>Category Name</th>
							<th>Products</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categories}" var="category">
							<tr>
								<td>${category.name}</td>
								<td>
									<ul>
										<c:forEach items="${category.products}" var="product">
											<li>${product.name}</li>
										</c:forEach>
									</ul>
								</td>
								<td>
									<a class="btn btn-success" href="/categories/${category.id}">Show</a>
									<a class="btn btn-danger" href="/category/delete/${category.id}">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
</body>
</html>