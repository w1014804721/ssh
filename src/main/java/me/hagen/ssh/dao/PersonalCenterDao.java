package me.hagen.ssh.dao;

import me.hagen.ssh.domain.User;

import org.springframework.stereotype.Repository;

@Repository("personalCenterDao")   //这个值和service层里的那个对象名字一致
public class PersonalCenterDao extends BaseDao<User> {

	
	
}
