<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
		<!-- 메인 로고 이미지를 링크하세요 logo.jpg -->
	 	<a href="${pageContext.request.contextPath}/">
			<img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
		</a>
		<c:choose>
			<c:when test="${sessionScope.authUser==null}">
				<ul class="menu">						
					<!-- 로그인 전 메뉴 -->
					<li><a href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
					<li><a href="${pageContext.request.contextPath}/user/joinForm">회원가입</a></li>
		 		</ul>
 			</c:when> 			
		
			<c:when test="${sessionScope.authUser!=null}"> 
				<ul class="menu">				
					<!-- 로그인 후 메뉴 -->
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}" target="_blank">내블로그</a></li>
 				</ul>
 			</c:when>
 						
		</c:choose>	