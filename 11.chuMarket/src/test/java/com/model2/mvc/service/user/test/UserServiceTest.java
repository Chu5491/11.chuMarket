package com.model2.mvc.service.user.test;

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
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

/*
 *	FileName :  UserServiceTest.java
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
public class UserServiceTest 
{
	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	//@Test
	//�׽�Ʈ �Ϸ�!!
	public void testAddUser() throws Exception 
	{
		User user = new User();
		user.setUserId("chu");
		user.setUserName("chu");
		user.setPassword("1234");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("��⵵");
		user.setEmail("chu@chu.com");
		
		userService.addUser(user);
		
		user = userService.getUser("chu");

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("chu", user.getUserId());
		Assert.assertEquals("chu", user.getUserName());
		Assert.assertEquals("1234", user.getPassword());
		Assert.assertEquals("111-2222-3333", user.getPhone());
		Assert.assertEquals("��⵵", user.getAddr());
		Assert.assertEquals("chu@chu.com", user.getEmail());
	}
	
	//@Test
	//�׽�Ʈ �Ϸ�!!
	public void testGetUser() throws Exception 
	{
		
		User user = new User();
		
		user = userService.getUser("chu");

		//==> console Ȯ��
		System.out.println(user);
		
	}
	
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testUpdateUser() throws Exception
	 {
		 
		User user = userService.getUser("chu");
		Assert.assertNotNull(user);
		
		Assert.assertEquals("chu", user.getUserName());
		Assert.assertEquals("111-2222-3333", user.getPhone());
		Assert.assertEquals("��⵵", user.getAddr());
		Assert.assertEquals("chu@chu.com", user.getEmail());
		
		user.setUserName("chuchu");
		user.setPhone("777-7777-7777");
		user.setAddr("change");
		user.setEmail("change@change.com");
		
		userService.updateUser(user);
		
		user = userService.getUser("chu");
		Assert.assertNotNull(user);
		
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("chuchu", user.getUserName());
		Assert.assertEquals("777-7777-7777", user.getPhone());
		Assert.assertEquals("change", user.getAddr());
		Assert.assertEquals("change@change.com", user.getEmail());
	 }
	 
	//@Test
	//�׽�Ʈ �Ϸ�!! 
	public void testCheckDuplication() throws Exception
	{
		
		//==> console Ȯ��
		System.out.println(userService.checkDuplication("chu"));
		System.out.println(userService.checkDuplication("chu"+System.currentTimeMillis()) );
	 	
		//==> API Ȯ��
		Assert.assertFalse( userService.checkDuplication("chu") );
	 	Assert.assertTrue( userService.checkDuplication("chu"+System.currentTimeMillis()) );
		 	
	}
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 @Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetUserListAll() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = userService.getUserList(search);
	 	
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
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 //�׽�Ʈ �Ϸ�!!
	 public void testGetUserListByUserId() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("chu");
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	//�׽�Ʈ �Ϸ�!!
	 public void testGetUserListByUserName() throws Exception
	 {
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("SCOTT");
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}