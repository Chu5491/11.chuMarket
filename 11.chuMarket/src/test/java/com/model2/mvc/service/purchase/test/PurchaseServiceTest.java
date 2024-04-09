package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.*;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

/*
 *	FileName :  ProductServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */

@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data �� �پ��ϰ� Wiring ����...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest 
{

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	//�׽�Ʈ �Ϸ�!!
	public void testAddPurchase() throws Exception 
	{
		User user = new User();
		Product prod = new Product();
		Purchase pur = new Purchase();
		
		user = userService.getUser("user05");
		prod = productService.getProduct(10008);
		
		pur.setPurchaseProd(prod);
		pur.setBuyer(user);
		pur.setPaymentOption("1");
		pur.setReceiverName("�߰��");
		pur.setReceiverPhone("010-5491-1508");
		pur.setDivyAddr("�Ÿ���");
		pur.setDivyRequest("������!");
		pur.setTranCode("1");
		pur.setDivyDate("2024-03-06");
		pur.setTranStock(2);
		
		purchaseService.addPurchase(pur);
		
		//==> console Ȯ��
		System.out.println(pur.getTranNo());
		
	}
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetPurchase() throws Exception 
 	 {
		Purchase purchase = purchaseService.getPurchase(10001);
		
		//==> console Ȯ��
		System.out.println(purchase);
	 }
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testUpdatePurchase() throws Exception
	 {
		 
		Purchase purchase = purchaseService.getPurchase(10001);
		
		purchase.setDivyRequest("��� ¯ ������");
		
		System.out.println("������ Purchase : " + purchase);
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10001);
		
		System.out.println("���� �� Purchase : " + purchase);
		
	 }
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetPurchaseListAll() throws Exception
	 {
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search,"user05");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 }
	 
	 @Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testDecreaseStock() throws Exception
	 {
		Purchase purchase = purchaseService.getPurchase(10001);
			
		//==> console Ȯ��
		System.out.println(purchase);
	
		purchaseService.decreaseStock(purchase, 2);
	
		purchase = purchaseService.getPurchase(10001);
			
		//==> console Ȯ��
		System.out.println(purchase);
		
	 }
}