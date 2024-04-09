<%
	/***************************************************
	*	작성자 : 추경운
	*	작성일 : 2024-03-31
	*	내  용 : 기존의 FrameSet 형식을 
	*			 header / article / footer 
	*			 jspInclude 방식으로 수정 
	*			 Bulma Css FrameWork 적용에 따른
	*			 페이지 UI 수정
	****************************************************/
%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<!-- Header -->
	<header>
		<jsp:include page="/layout/header.jsp"></jsp:include>
	</header>
	<!-- Header -->
	
	<!-- Script -->
	<script type="text/javascript">
		$(function() 
		{
			var menu = '${menu}';
			
			/* ########################## Style ########################## */	
			$(".ct_btn01").css('cursor', 'pointer');
			$(".pageNavi > tbody > tr > td > a").css('cursor', 'pointer');
			$(".ct_list_pop > td:nth-child(2)").css('cursor', 'pointer');
			/* ########################## Style ########################## */	
			
			
			/* ########################## Paging ########################## */
			$(".pageNavi > tbody > tr > td > a").on("click" , function() 
		 	{
				fncGetList($(this).attr('id'));
			}); 
			/* ########################## Paging ########################## */
			
			
			/* ########################## Product ########################## */
			$(".ct_list_pop > td:nth-child(2)").on("click" , function() 
			{
				var stock = $(this).parent().children('td:nth-child(6)').attr('id');
				if(menu == "manage" || menu == "bsns")
				{
					$(window.location).attr("href","/product/getProduct?prodNo="+$(this).attr('id')+"&menu="+menu);
				}else
				{
					if(stock == 0)
					{
						alert("품절된 상품입니다.");
					}else
					{
						$(window.location).attr("href","/product/getProduct?prodNo="+$(this).attr('id')+"&menu="+menu);
					}
				}
		 		
			}); 
			
			/* ########################## Product ########################## */
		});
	</script>
	<!-- Script -->
	
	<body bgcolor="#ffffff" text="#000000">
		
		<!-- Article -->
		<form name="detailForm" method="post" action="/market/getMarket?marketNo=${market.marketNo}&menu=${menu}">
			<div class="table-container">
				<table class="table" width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td background="/images/ct_ttl_img02.gif" width="100%">
							<b>${market.marketName}</b>
						</td>
					</tr>
				</table>
				<div class="container is-max-desktop" style="align:center;" >
					<table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
						<tr>
							<td height="1" colspan="3" bgcolor="D6D6D6"></td>
						</tr>
						<tr>
							<td width="104" class="ct_write">
								소개
							</td>
							<td class="ct_write01">${market.marketIntro}</td>
						</tr>
						<tr>
							<td width="104" class="ct_write">
								운영정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
							</td>
							<td class="ct_write01">
								<c:if test="${market.manaFlag == 'Y' }">
									현재 운영중인 쇼핑몰입니다.
								</c:if>
								<c:if test="${market.manaFlag == 'W' }">
									현재 승인 대기중인 쇼핑몰입니다.
								</c:if>
								<c:if test="${market.manaFlag == 'F' }">
									현재 운영이 중단된 쇼핑몰입니다.
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="13"z bgcolor="D6D7D6" height="1"></td>
						</tr>
					</table>
					<h3 class="title">판매중인 상품</h3>
					<table class="table" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
						<tr>
							<td colspan="13" >전체 <b>${resultPage.totalCount}</b> 건수</td>
						</tr>
						<tr>
							<td class="ct_list_b" width="100">No</td>
							<td class="ct_list_b" width="150">상품명</td>
							<td class="ct_list_b" width="150">가격</td>
							<td class="ct_list_b">등록일</td>	
							<td class="ct_list_b">쇼핑몰</td>	
							<td class="ct_list_b">현재상태</td>	
						</tr>
						<c:forEach var="prod" items="${list}" >
							<c:set var="i" value="${ i+1 }" />
							<tr class="ct_list_pop">
								<td align="center">${i}</td>
								<td align="left" id="${prod.prodNo}">
									<a style="color:#4285F4">
										${prod.prodName}
									</a>
								</td>
								<td align="left">${prod.price}</td>
								<td align="left">${prod.regDate}</td>
								<td align="left" id="${prod.market.marketNo}">
									${market.marketName}
								</td>
								<td align="left" id="${prod.prodStock}">
									<c:if test="${prod.prodStock <= 0}">
										재고없음
									</c:if>
									<c:if test="${prod.prodStock > 0}">
										<c:if test="${menu != 'manage' || menu != 'bsns'}">
											<c:if test="${prod.prodStock <= 5}">
												품절임박 [ ${prod.prodStock} 개 남음 ]
											</c:if>
											<c:if test="${prod.prodStock > 5}">
												판매중
											</c:if>
										</c:if>
										<c:if test="${menu == 'manage' || menu == 'bsns'}">
											재고 : ${prod.prodStock} 개 남음
										</c:if>
									</c:if>
								</td>	
							</tr>
						</c:forEach>
						<tr>
							<td colspan="13"z bgcolor="D6D7D6" height="1"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageNavi table" >
						<tr>
							<td align="center">
								<input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
								${resultPage.paging}
					    	</td>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:-50px;">
						<tr>
							<td style="float:right;"><a class="button ct_btn01" style="height:40px;">이전</a></td>	
						</tr>
					</table>
				</div>
			</div>
		</form>
		<!-- Article -->
		
		<!-- Footer -->
		<footer>
			<jsp:include page="/layout/footer.jsp"></jsp:include>
		</footer>
		<!-- Footer -->
		
	</body>
</html>