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
				      		<tbody id="tBody${cateVo.cateNo}" class="cateList">
				      			<!-- 리스트 영역 -->
				      			<tr>
									<td>${cateVo.cateNo}</td>
									<td>${cateVo.cateName}</td>
									<td id="tdPostNum">${cateVo.postNum}</td>
									<td>${cateVo.description}</td>
								    <td class='text-center'>
								    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-no="${cateVo.cateNo}">
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
	var id = "${blogVo.id}";
	
	console.log(cateName,description,id);
	
	var categoryVo = {
			cateName : cateName,
			description : description,
			id : id
	};
	console.log(categoryVo);
	

	$.ajax({			
		url : "${pageContext.request.contextPath}/category/insert",		
		type : "post",
		/* contentType : "application/json"*/
		data : categoryVo,

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			
		if(jsonResult.result == 'success') {//처리성공					
			render(jsonResult.data);
			console.log("성공");
						
			$("[name='name']").val("");
			$("[name='desc']").val("");			
			
			}else {//오류처리
				var msg = jsonResult.failMsg;
				alter(msg);				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}				
	});
	console.log("test입니다." + categoryVo.id);		
});

$("#admin-cate-list").on("click",".btnCateDel", function(){
	console.log("삭제버튼 클릭");
	var cateNo = $(this).data("no");	
	console.log(cateNo);
	var tBodyNo = $(this).closest("tbody").attr("id");
	console.log(tBodyNo);
	var tdPostNum = $(this).parent().siblings("#tdPostNum").val();
	
	if(tdPostNum > 0) {
		alert("포스트정보가 있어 삭제할수없습니다.")
	}else {		
	
	$.ajax({			
		url : "${pageContext.request.contextPath}/category/delete",		
		type : "post",
		/* contentType : "application/json"*/
		data : {cateNo : cateNo},

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			
		if(jsonResult.result == 'success') {//처리성공			
			console.log("Ajax 결과성공");
			$("#"+tBodyNo).remove();			
			
			}else {//오류처리
				var msg = jsonResult.failMsg;
				alert(msg);				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}				
	});
	
	}
	
});

	function render(cateVo) {
		var src = "";
		src += '<tbody id="tBody'  + cateVo.cateNo + '" class="cateList">';
		src += '	<tr>';
		src += '		<td>' + cateVo.cateNo + '</td>';
		src += '		<td>' + cateVo.cateName + '</td>';
		src += '		<td>' + cateVo.postNum + '</td>';
		src += '		<td>' + cateVo.description + '</td>';
		src += '		<td class="text-center">';
		src += '			<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-no="' + cateVo.cateNo + '">';
		src += '		</td>';
		src += '	</tr>';
		src += '</tbody>';
		$("#admin-cate-list").prepend(src);
	}

</script>
</html>