package com.qd.test;

import org.junit.Test;

import com.qd.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class StringTest {

	@Test
	public void test() {
		Jedis  jedis=RedisUtil.getJedis();
		
		try {
//			jedis.set("name", "张三");
//			String name=jedis.get("name");
//			System.out.println(name);
//			//append 追加
//			jedis.append("name", "hello");
//			System.out.println(jedis.get("name"));
//			//刪除key對應的記錄
//			jedis.del("name");
//			System.out.println(jedis.get("name"));
			//mset
			jedis.mset("name","悟空","age","18");
			System.out.println(jedis.mget("name"));
			System.out.println(jedis.mget("age"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			RedisUtil.returnResource(jedis);
		}
	}
	
	@Test
	public void test2() {
		Jedis  jedis=RedisUtil.getJedis();
		
		try {
			jedis.set("name", "张三");
			String name=jedis.get("name");
			System.out.println(name);
            
			//清空數據
			System.out.println(jedis.flushDB());

			//存儲數據
			jedis.set("uname", "wukong123");
			String name1=jedis.get("uname");
			System.out.println(name1);
			
			//setnx 如果key不存在,則存儲
			jedis.setnx("uname", "18");
			System.out.println(jedis.get("age"));	
			
			//設置key的有效期,並存儲
			jedis.setex("username", 3, "feifei");
			System.out.println(jedis.get("username"));
			
			Thread.sleep(5000);
			
			System.out.println(jedis.get("username"));
			
			System.out.println(jedis.getrange("uname", 1, 2));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			RedisUtil.returnResource(jedis);
		}
	}
	
}
