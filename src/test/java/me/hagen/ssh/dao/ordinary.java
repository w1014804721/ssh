package me.hagen.ssh.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import antlr.collections.List;

public class ordinary {



	
	public static void main(String[] args) throws IOException {
//
//			String test = "["
//					+ "18,25,3"
//					+ "]";
//			String result = test.replaceAll("[\\pP\\p{Punct}]","a");
//			String[] r = result.split("a");
//			
//			for(int i =1;i<r.length;i++){
//				String q = r[i];
//				int a = Integer.valueOf(q);
//				System.out.println(a);
//			}

	}

	
	 public static String  createRandomCharData(int length)
	    {
	        StringBuilder sb=new StringBuilder();
	        Random rand=new Random();//随机用以下三个随机生成器
	        Random randdata=new Random();
	        int data=0;
	        for(int i=0;i<length;i++)
	        {
	            int index=rand.nextInt(3);
	            //目的是随机选择生成数字，大小写字母
	            switch(index)
	            {
	            case 0:
	                 data=randdata.nextInt(10);//仅仅会生成0~9
	                 sb.append(data);
	                break;
	            case 1:
	                data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
	                sb.append((char)data);
	                break;
	            case 2:
	                data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
	                sb.append((char)data);
	                break;
	            }
	        }
	        String result=sb.toString();
	       return result;
	    }

}
