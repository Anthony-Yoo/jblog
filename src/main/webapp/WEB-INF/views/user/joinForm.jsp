<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!-- 메인 해더 -->
	

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2">사용할 수 있는 아이디 입니다.</td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>	      		
			</form>			
		</div>		
	</div>
	
</body>
<script type="text/javascript">
var checkId = null;
$("#btnIdCheck").on("click",function(){
	console.log("중복체크 버튼호출");
	
	var id = $("[name=id]").val();
	console.log(id);
	
	$.ajax({			
			url : "${pageContext.request.contextPath}/user/idcheck",		
			type : "post",
			/* contentType : "application/json", */
			data : {id : id},
	
			dataType : "json",
			success : function(jsonResult){
				console.log(jsonResult);
				/*성공시 처리해야될 코드 작성*/
				
				if(jsonResult.result=="success") {//처리성공
					console.log("result 성공")
					//사용가능여부 표현
					if(jsonResult.data == true) {						
						$("#tdMsg").html(id+"는 사용할 수 있는 아이디 입니다.");
						checkId = "able";
						console.log(checkId);
					}else {
						$("#tdMsg").html(id+"는 사용중입니다. 다른 아이디로 가입해 주세요.");
						checkId = "unable";
						console.log(checkId);
					}
				
				}else {
					var msg = jsonResult.failMsg;
					alter(msg);					
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
	});		
});


//가입폼 전송할때
$('#joinForm').on("submit",function(){
	console.log("회원가입버튼 클릭");
	
	if ($('#txtId').val() == "") {
		//아이디 입력여부체크
		alert("아이디를 입력해주세요");
		$('#txtId').focus();
		return false;
	}else if (checkId == null || checkId == "" || checkId == undefined) {
		//아이디 중복체크여부
		alert("아이디 중복체크 해주세요");
		console.log(checkId);
		return false;
	}else if (checkId == "unable") {
		alert("다른 아이디명을 입력하세요");
		console.log(checkId);
		return false;	
	}else if ($('#txtPassword').val() == "") {
		alert("패스워드를 입력해주세요");
		$('#txtPassword').focus();
		return false;	
	}else if ($('#txtUserName').val() == "") {
		alert("이름을 입력해주세요");
		$('#txtUserName').focus();
		return false;
	}else if ($('#chkAgree').is(':checked') == false) {
		alert("약관동의를 체크해주세요");
		$('#chkAgree').focus();
		return false;
	}else {
		return true;
	}

});

</script>

</html>