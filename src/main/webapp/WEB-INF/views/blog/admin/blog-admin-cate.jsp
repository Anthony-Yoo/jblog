<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		<!-- 개인블로그 해더 -->


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<c:forEach items="${cateList}" var="cateVo">
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			<tr>
							<td>${cateVo.cateNo}</td>
							<td>${cateVo.cateName}</td>
							<td>${cateVo.postNum}</td>
							<td>${cateVo.description}</td>
						    <td class='text-center'>
						    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    </td>
						</tr>						
						<!-- 리스트 영역 -->
					</tbody>
					</c:forEach>
				</table>
      			<%-- <form id="categoryForm" method="post" action="${pageContext.request.contextPath}/user/join"> --%>
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
		    <!--</form> -->
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
$('#btnAddCate').on("click",function(){
	console.log("카테고리추가 버튼 클릭");
	
	var cateName = $("[name='name']").val();
	var description = $("[name='desc']").val();
	
	console.log(cateName,description);
	
	var categoryVo = {
			cateName : cateName,
			description : description
	};
	console.log(categoryVo);
	

	$.ajax({			
		url : "${pageContext.request.contextPath}/category/insert",		
		type : "post",
		/* contentType : "application/json"*/
		data : categoryVo,

		dataType : "json",
		success : function(jsonResult){
			var id = "t-"+guestVo.no;
			
			console.log(jsonResult);
			/*성공시 처리해야될 코드 작성*/
			
			if(jsonResult.data>0) {//처리성공					
				$("#"+id).remove();
				$('#myModal').modal('hide');
					
			}else {//오류처리
				alert("비밀번호가 틀렸습니다.")
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}			
	});
	console.log("test입니다." + guestVo.no);	
	
	
});

</script>
</html>