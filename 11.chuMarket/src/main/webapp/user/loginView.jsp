<%
	/***************************************************
	*	�ۼ��� : �߰��
	*	�ۼ��� : 2024-03-31
	*	��  �� : ������ FrameSet ������ 
	*			 header / article / footer 
	*			 jspInclude ������� ���� 
	*			 Bulma Css FrameWork ���뿡 ����
	*			 ������ UI ����
	****************************************************/
%>
<%@ page contentType="text/html; charset=euc-kr" %>
<html>
	<!-- Header -->
	<header>
		<jsp:include page="/layout/header.jsp"></jsp:include>
	</header>
	<!-- Header -->
	
	<!-- Script -->
	<script type="text/javascript">
	
		//���� �� submit �̺�Ʈ
		document.addEventListener("DOMContentLoaded", function()
		{
		  var inputFields = document.querySelectorAll('.ct_input_g');
	
			inputFields.forEach(function(inputField) 
			{
				inputField.addEventListener("keypress", function(event) 
				{
					if (event.key === "Enter") 
					{
						fncLogin();
					}
				});
			});
		});
		
		$(function() 
		{
			/* ########################## Style ########################## */	
			$("img[src='/images/btn_add.gif']").css('cursor', 'pointer');
			$("img[src='/images/btn_login.gif']").css('cursor', 'pointer');
			/* ########################## Style ########################## */
			
			
			
			//==> �߰��Ⱥκ� : "addUser"  Event ����
			$("img[src='/images/btn_login.gif']").on("click" , function() 
			{
				fncLogin(); 
			});
			
			//==> �߰��Ⱥκ� : "addUser"  Event ����
			$("img[src='/images/btn_add.gif']").on("click" , function() 
			{
				self.location = "/user/addUser"
			});
		});
	
		function fncLogin() 
		{
			var id=document.loginForm.userId.value;
			var pw=document.loginForm.password.value;
			if(id == null || id.length <1) 
			{
				alert('ID �� �Է����� �����̽��ϴ�.');
				document.loginForm.userId.focus();
				return;
			}
			
			if(pw == null || pw.length <1) 
			{
				alert('�н����带 �Է����� �����̽��ϴ�.');
				document.loginForm.password.focus();
				return;
			}
			
			$.ajax
			({
				url : "/user/json/login",
				method : "POST" ,
				dataType : "json" ,
				headers : 
				{
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					userId : id,
					password : pw
				}),
				success : function(JSONData , status) 
				{
					//Debug...
					//alert(status);
					//alert("JSONData : \n"+JSONData);
					//alert( "JSON.stringify(JSONData) : \n"+JSON.stringify(JSONData) );
					//alert( JSONData != null );
					
					if( JSONData != null )
					{
						//[���1]
						//$(window.parent.document.location).attr("href","/index.jsp");
						
						//[���2]
						//window.parent.document.location.reload();
						
						//[���3]
						$(document.location).attr("href","/");
						
						//==> ��� 1 , 2 , 3 ��� ����
					}else
					{
						alert("���̵� , �н����带 Ȯ���Ͻð� �ٽ� �α���...");
					}
				}
			}); 
		}
	</script>
	<!-- Script -->
	
	<!-- Article -->
	<body bgcolor="#ffffff" text="#000000" >
		<div class="container is-max-desktop" style="align:center;" >
			<br><br><br><br><br><br>
			<form name="loginForm"  method="post" action="/user/login" target="_parent">
				<div align="center">
					<table WITH="100%" HEIGHT="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
						<tr>
							<td ALIGN="CENTER" VALIGN="MIDDLE">
							
							<table width="650" height="390" border="5" cellpadding="0" cellspacing="0" bordercolor="#D6CDB7">
							  <tr> 
							    <td width="10" height="5" align="left" valign="top" bordercolor="#D6CDB7">
							    	<table width="650" height="390" border="0" cellpadding="0" cellspacing="0">
							        	<tr>
							          		<td width="305">
							           			<img src="/images/logo-spring.png" width="305" height="390">
							          		</td>
									        <td width="345" align="left" valign="top" background="/images/login02.gif">
									          	<table width="100%" height="220" border="0" cellpadding="0" cellspacing="0">
									                <tr> 
										                <td width="30" height="100">&nbsp;</td>
										                <td width="100" height="100">&nbsp;</td>
										                <td height="100">&nbsp;</td>
										                <td width="20" height="100">&nbsp;</td>
									                </tr>
									                <tr> 
										                <td width="30" height="50">&nbsp;</td>
										                <td width="100" height="50">
									                		<img src="/images/text_login.gif" width="91" height="32">
									              	    </td>
									                	<td height="50">&nbsp;</td>
									                	<td width="20" height="50">&nbsp;</td>
									                </tr>
									                <tr> 
									                	<td width="200" height="50" colspan="4"></td>
									                </tr>              
									                <tr> 
										                <td width="30" height="30">&nbsp;</td>
										                <td width="100" height="30">
										                	<img src="/images/text_id.gif" width="100" height="30">
										                </td>
										                <td height="30">
										                  <input 	type="text" name="userId"  class="ct_input_g" 
										                  				style="width:180px; height:19px"  maxLength='50'/>          
										          		</td>
										                <td width="20" height="30">&nbsp;</td>
									                </tr>
									                <tr> 
										                <td width="30" height="30">&nbsp;</td>
										                <td width="100" height="30">
										                	<img src="/images/text_pas.gif" width="100" height="30">
										                </td>
										                <td height="30">                    
										                    <input 	type="password" name="password" class="ct_input_g" style="width:180px; height:19px"  maxLength="50" >
										                </td>
										                <td width="20" height="30">&nbsp;</td>
									                </tr>
									                <tr> 
									                	<td width="30" height="20">&nbsp;</td>
									                	<td width="100" height="20">&nbsp;</td>
									               		<td height="20" align="center">
									      					<table width="136" height="20" border="0" cellpadding="0" cellspacing="0">
									                            <tr> 
										                            <td width="56">
										                            	<img src="/images/btn_login.gif" width="56" height="20" border="0">
										                            </td>
										                            <td width="10">&nbsp;</td>
										                            <td width="70">
										                            	<img src="/images/btn_add.gif" width="70" height="20" border="0">
										                            </td>
									                            </tr>
									                    </table>
									                  </td>
									                  <td width="20" height="20">&nbsp;</td>
									                  </tr>
									            </table>
									       </td>
							       		</tr>	                            
							      	</table>
							      </td>
							    </tr>
							</table>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
	<!-- Article -->
	
	<!-- Footer -->
	<footer>
		<jsp:include page="/layout/footer.jsp"></jsp:include>
	</footer>
	<!-- Footer -->
</html>

<script type="text/javascript">
	document.loginForm.userId.focus();
</script>