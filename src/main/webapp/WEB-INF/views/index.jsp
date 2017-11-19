<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="<c:url value="/resources/js/script.js" />"></script>
	</head>
<body>
	<div class="wrapper">
		<div class="content">
			<div class="controls">
				<div class="wallets">
					<div class="wallet-user">
						<p>Ваш счет</p>
						<table>
							<tr>
								<th>Номинал</th> <th>Кол-во</th> <th></th>
							</tr>
							<c:forEach items="${userCoins}" var="coin">
								<tr>
									<td>${coin.key.value}</td> <td>${coin.value}</td> <td>Внести</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="wallet-vm">
						<p>Счет ВМ</p>
						<table>
							<tr>
								<th>Номинал</th> <th>Кол-во</th>
							</tr>
							
							<c:forEach items="${vmCoins}" var="coin">
								<tr>
									<td>${coin.key.value}</td> <td>${coin.value}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				
				<div class="result-panel">
					Внесенная сумма: <span class="total"> ${currentPaidAmount}</span>р.  <button type="button">Сдача</button>
				</div>
			</div>
			
			<div class="products">
				Товары
				<div class="product-list">
					<c:forEach items="${products}" var="product" varStatus="loop">
						
						<c:if test="${loop.index % 3 == 0}">
							<c:if test="${!loop.last}">
							
							</c:if>
							
							<div class="row">
						</c:if>
					    
					    	<div class="product">
								<p>${product.name}</p>
								<p>${product.price} р.</p>
								<p>${product.amount} шт.</p>
							</div>
					    
					    <c:if test="${(loop.index + 1) % 3 == 0}">
							</div>
						</c:if>
						
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
