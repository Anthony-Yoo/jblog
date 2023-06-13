<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!--메인 해더 자리 -->
		
		
		<form id="search-form">
			<div>
				<input type="text" name="keyword" >
				<button id="btnSearch" type="submit" >검색</button>
			</div>			
			<div>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle" > 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName" > 
			</div>
		</form>
		
		<div id="resultList">
			
			
		</div>
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->
	
	
	</div>	
	<!-- //center-content -->
</body>
</html>