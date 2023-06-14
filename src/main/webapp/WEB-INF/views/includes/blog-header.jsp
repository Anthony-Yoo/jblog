<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
		<div id="header" class="clearfix">
			<h1><a href="${pageContext.request.contextPath}/${blogVo.id}">${blogVo.blogTitle}</a></h1>
			
			<c:choose>			
			<c:when test="${sessionScope.authUser == null}">
				<ul class="clearfix">
					<!-- 로그인 전 메뉴 -->
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</ul>
			</c:when> 					
					
			<c:when test="${sessionScope.authUser != null}">			
				<ul class="clearfix">		
				<!-- 로그인 후 메뉴 (자신의 블로그일때만 관리 메뉴가 보인다. -->
					<c:if test="${sessionScope.authUser.id eq blogVo.id}">
					<li><a class="btn_s" href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">내블로그 관리</a></li>
					</c:if>
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>			 		
				</ul>
			</c:when>
			</c:choose>
			
		</div>
		<!-- //header -->
