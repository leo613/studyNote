package com.qd.test;

import java.util.List;

import com.qd.po.Dept;
import com.qd.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
    /**
     *   使用list 储存所有部门的编号
     *   lpush dept:de_id 1 2
     *  使用hash 存储每一个部门 
     *   hmset dept:1 de_id 1 de_name zhangsan
     *   hmset detp:1 de_id 2 de_name lisi
     */
		//查询所有的部门信息
		Jedis jedis=RedisUtil.getJedis();
		
		try {
	    List<String>list=jedis.lrange("dept:de_id", 0, -1);
			for (String deId : list) {
				String id=jedis.hget("dept:"+deId, "de_id");
				String name=jedis.hget("dept:"+deId,"de_name");
				Dept de=new Dept();
				if(id!=null) {
					de.setDeId(Integer.parseInt(id));
				}		
				de.setDeName(name);
				System.out.println(de);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			RedisUtil.returnResource(jedis);
		}
		
	}
}
