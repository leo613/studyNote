package com.qd.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class jedisTest {
	/**
	 * 单实例连接
	 */
   
	@Test
	public void testjedis() {

		Jedis jedis=new Jedis("192.168.136.128",6379);
    	jedis.set("name", "lisi");
        String name=jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
	
	/**
	 *连接池连接 
	 *
	 */
	@Test
	public void pool() {
		JedisPoolConfig config=new JedisPoolConfig();
		//最大连接数
		config.setMaxTotal(30);
        //最大连接空闲数
		config.setMaxIdle(2);
		
		JedisPool pool=new JedisPool(config,"192.168.136.128",6379);
		Jedis jedis=null;
		
		try {
			jedis=pool.getResource();
			jedis.set("username", "猪八戒");
			String name=jedis.get("username");
			System.out.println(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		
		}
		
		
	}
	
}
