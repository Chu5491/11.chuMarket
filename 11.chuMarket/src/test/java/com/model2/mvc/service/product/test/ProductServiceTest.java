package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

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
public class ProductServiceTest 
{

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	//�׽�Ʈ �Ϸ�!!
	public void testAddProduct() throws Exception 
	{
		Product product = new Product();
		product.setProdName("���߸�");
		product.setFileName("���߸�.jpg");
		product.setManuDate("20240304");
		product.setProdDetail("������ �ְ�!");
		product.setPrice(90000);
		product.setProdStock(10);
		
		product = productService.addProduct(product);
		
		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("���߸�", product.getProdName());
		Assert.assertEquals("���߸�.jpg", product.getFileName());
		Assert.assertEquals("20240304", product.getManuDate());
		Assert.assertEquals("������ �ְ�!", product.getProdDetail());
		Assert.assertEquals(90000, product.getPrice());
		Assert.assertEquals(10, product.getProdStock());
	}
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProduct() throws Exception 
 	 {
		Product product = new Product();
		product = productService.getProduct(10008);
		
		//==> console Ȯ��
		System.out.println(product);
		
	 }
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testUpdateProduct() throws Exception
	 {
		 
		Product product = productService.getProduct(10008);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("���߸�", product.getProdName());
		Assert.assertEquals("���߸�.jpg", product.getFileName());
		Assert.assertEquals("20240304", product.getManuDate());
		Assert.assertEquals("������ �ְ�!", product.getProdDetail());
		Assert.assertEquals(90000, product.getPrice());
		
		System.out.println(product);
		
		product.setProdStock(11);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10008);
		
		System.out.println(product);
		
		Assert.assertNotNull(product);
		
		//==> API Ȯ��
		Assert.assertEquals("���߸�", product.getProdName());
		Assert.assertEquals("���߸�.jpg", product.getFileName());
		Assert.assertEquals("20240304", product.getManuDate());
		Assert.assertEquals("������ �ְ�!", product.getProdDetail());
		Assert.assertEquals(90000, product.getPrice());
		Assert.assertEquals(11, product.getProdStock());
	 }
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProductListAll() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProductListByProdNo() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10008");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProductListByProdName() throws Exception
	 {
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("���߸�");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProductListByPrice() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("10000");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetProductTotal() throws Exception
	 {
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	int totalCount = productService.getProductTotal(search);
	 	
	 	System.out.println(totalCount);
	 }
}