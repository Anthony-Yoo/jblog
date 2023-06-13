<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

		<div id="header" class="clearfix">
			<h1><a href="">${sessionScope.autherUser.userName}의 블로그입니다.</a></h1>
			
			<c:choose>
			<c:when test="${sessionScope.autherUser == null}">
				<ul class="clearfix">
					<!-- 로그인 전 메뉴 -->
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</ul>
			</c:when> 	
					
					
			<c:when test="${sessionScope.autherUser != null}">			
				<ul class="clearfix">		
				<!-- 로그인 후 메뉴 (자신의 블로그일때만 관리 메뉴가 보인다. -->
					<li><a class="btn_s" href="${pageContext.request.contextPath}/${sessionScope.autherUser.id}/admin/basic">내블로그 관리</a></li>
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>			 		
				</ul>
			</c:when>
			</c:choose>
			
		</div>
		<!-- //header -->
		