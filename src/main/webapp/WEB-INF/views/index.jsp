<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		
		
		<%-- <spring:url value="/resources/css/style.css" var="mainCss" />
		<link rel="stylesheet" type="text/css" href="${mainCss}"> --%>
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
		
		
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
							<tr>
								<td>10</td> <td>15</td> <td>+</td>
							</tr>
							<tr>
								<td>5</td> <td>20</td> <td>+</td>
							</tr>
							<tr>
								<td>2</td> <td>30</td> <td>+</td>
							</tr>
							<tr>
								<td>1</td> <td>10</td> <td>+</td>
							</tr>
						</table>
					</div>
					<div class="wallet-vm">
						<p>Счет ВМ</p>
						<table>
							<tr>
								<th>Номинал</th> <th>Кол-во</th>
							</tr>
							<tr>
								<td>10</td> <td>100</td>
							</tr>
							<tr>
								<td>5</td> <td>100</td>
							</tr>
							<tr>
								<td>2</td> <td>100</td>
							</tr>
							<tr>
								<td>1</td> <td>100</td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="result-panel">
					Внесенная сумма: <span class="total"> 987 р.</span> <button type="button">Сдача</button>
				</div>
			</div>
			
			<div class="products">
				Товары
				<div class="product-list">
					<div class="row">
						<div class="product">
							<p>Чай</p>
							<p>13 р.</p>
							<p>10 шт.</p>
						</div>
						<div class="product">
							<p>Кофе</p>
							<p>18 р.</p>
							<p>20 шт.</p>
						</div>
						<div class="product">
							<p>Кофе с молоком</p>
							<p>21 р.</p>
							<p>20 шт.</p>
						</div>
					</div>
					<div class="row">
						<div class="product">
							<p>Сок</p>
							<p>35 р.</p>
							<p>15 шт.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
