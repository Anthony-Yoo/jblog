<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}" >
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
					<!-- 카테고리 반복 -->
					<c:forEach items="${cateList}" var="cateVo">
						<li><a href="${pageContext.request.contextPath}/${blogVo.id}?cateNo=${cateVo.cateNo}&postNo=${postVo.postNo}">${cateVo.cateName}</a></li>						
					</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				<c:choose>
					<c:when test="${empty selectPost}">	
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
						</div>					    
						<div id="post" >
						</div>						
					</c:when>
					<c:when test="${not empty selectPost}">	
						<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>${selectPost.postTitle}</strong></div>
									<div id="postDate" class="text-left"><strong>${selectPost.regDate}</strong></div>
									<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
						</div>
						<!-- //postBox -->					
						<div id="post" >${selectPost.postContent}</div>
					</c:when>
				</c:choose>	
				<c:if test="${sessionScope.authUser != null}">
				<br>
				<div id="listTitle" class="text-left"><strong>POST의 댓글</strong></div>
				<hr>
<<<<<<< HEAD
				<form action="" id="cmtBtnForm" method="get">
=======
				<form id="cmtForm" action="" method="get">
>>>>>>> branch 'master' of https://github.com/Anthony-Yoo/jblog.git
					<table>						
						<tr>
							<td class="text-left"><input type="hidden" id="userNo" value="${sessionScope.authUser.userNo}">${sessionScope.authUser.userName}</td>
							<td class="text-left"><input id="cmtContent" style="width:700px;"/></td>
							<td class="text-right"><input type="hidden" id="postNo" value="${selectPost.postNo}">
			      			<button class="cmtBtn" type="submit" value="">저장</button>
			      			</td>
						</tr>											
					</table>
				</form>			
				</c:if>
				<div id="listParent">
					<p id="listPatch">
				</div>				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<c:choose>
						<c:when test="${empty selectPostList}">														
							<table>					
								<colgroup>
									<col style="">
									<col style="width: 20%;">
								</colgroup>						
								<tr>
									<td class="text-left">해당카테고리에 등록된 Post가 없습니다.</td>
									<td class="text-right"></td>
								</tr>											
							</table>															
						</c:when>				
						<c:otherwise>	
							<c:forEach items="${selectPostList}" var="postVo">				
								<table>					
									<colgroup>
										<col style="">
										<col style="width: 20%;">
									</colgroup>						
									<tr>
										<td class="text-left"><a href="${pageContext.request.contextPath}/${blogVo.id}?postNo=${postVo.postNo}&cateNo=${param.cateNo}">${postVo.postNo}. ${postVo.postTitle}</a></td>
										<td class="text-right">${postVo.regDate}</td>
									</tr>											
								</table>
							</c:forEach>					
						</c:otherwise>
					</c:choose>			
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
<<<<<<< HEAD
$("#cmtBtnForm").on("submit",function(e){
	e.preventDefault();
	console.log("코멘트 등록버튼 작동");
=======
$("document").ready(function(){
	console.log("DOM실행");
>>>>>>> branch 'master' of https://github.com/Anthony-Yoo/jblog.git
	
	var postNo = $("#postNo").val();	
	console.log(postNo);	
	
	$.ajax({			
		url : "${pageContext.request.contextPath}/comments/list",		
		type : "post",
		/*contentType : "application/json"*/
		data : {postNo : postNo},

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			/*성공시 처리해야될 코드 작성*/
			
			if(jsonResult.result=="success") {//처리성공					
					renderEach(jsonResult.data);
					console.log("성공");					
					
					$("#cmtContent").val("");					
					
			}else {//오류처리
				var msg = jsonResult.failMsg;
				alter(msg);				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}			
	});			
});		

$("#cmtForm").on("submit",function(e){
	e.preventDefault();
	console.log("코멘트 등록버튼 작동");

	var userNo = $("#userNo").val();
	var cmtContent = $("#cmtContent").val();
	var postNo = $("#postNo").val();
	
	var CommentsVo = {
			userNo : userNo,
			cmtContent : cmtContent,
			postNo : postNo
	};
	console.log(CommentsVo);
	
	$.ajax({			
		url : "${pageContext.request.contextPath}/comments/insert",		
		type : "post",
		/*contentType : "application/json"*/
		data : CommentsVo,

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			/*성공시 처리해야될 코드 작성*/
			
			if(jsonResult.result=="success") {//처리성공					
					render(jsonResult.data);
					console.log("성공");					
					
					$("#cmtContent").val("");					
					
			}else {//오류처리
				var msg = jsonResult.failMsg;
				alter(msg);				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}			
	});			
});	

$("#listParent").on("click",".cmtDelBtn",function(){
	console.log("딜리트버튼 작동");
	
	var orgCmtNo = $(this).closest('table').attr('id');
	var cmtNo = orgCmtNo.substring(1,10);
	console.log(cmtNo);	
	
	$.ajax({			
		url : "${pageContext.request.contextPath}/comments/delete",		
		type : "post",
		/*contentType : "application/json"*/
		data : {cmtNo : cmtNo},

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			/*성공시 처리해야될 코드 작성*/
			
			if(jsonResult.result=="success") {//처리성공	
					console.log("성공");					
					
					$('#t' + cmtNo).remove();					
					
			}else {//오류처리
				var msg = jsonResult.failMsg;
				alter(msg);				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}			
	});			
});	


function render(CommentsVo) {
	var str = "";	
	str += '	<table id="t' + CommentsVo.cmtNo + '">';						
	str += '		<tr>';
<<<<<<< HEAD
	str += '			<td class="text-left">' + CommentsVo.userName + '</td>';
	str += '			<td class="text-left">' + CommentsVo.cmtContent + '</td>';
	str += '			<td class="text-right">' + CommentsVo.regDate + '</td>';
=======
	str += '			<td class="text-left" style="width:60px;">' + CommentsVo.userName + '</td>';
	str += '			<td class="text-left" style="width:600px;">' + CommentsVo.cmtContent + '</td>';
	str += '			<td class="text-right">' + CommentsVo.regDate + '</td>';;
>>>>>>> branch 'master' of https://github.com/Anthony-Yoo/jblog.git
	str += '  			<td><button class="cmtDelBtn" type="submit" value="' + CommentsVo.cmtNo + '">X</button></td>';
	str += '		</tr>';											
	str += '	</table>';	
	$("#listPatch").prepend(str);	
}

function renderEach(CommentsVo) {
	$.each(CommentsVo,function(key,value){	
		var str = "";	
		str += '	<table id="t' + value.cmtNo + '">';						
		str += '		<tr>';
		str += '			<td class="text-left" style="width:60px;">' + value.userName + '</td>';
		str += '			<td class="text-left" style="width:600px;">' + value.cmtContent + '</td>';
		str += '			<td class="text-right">' + value.regDate + '</td>';;
		str += '  			<td><button class="cmtDelBtn" type="submit" value="' + value.cmtNo + '">X</button></td>';
		str += '		</tr>';											
		str += '	</table>';	
		$("#listPatch").append(str);		
	});
};
	
</script> 
</html>