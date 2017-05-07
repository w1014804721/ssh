package me.hagen.ssh.dao;

import me.hagen.ssh.domain.PartTimeRelation;

import org.springframework.stereotype.Repository;

@Repository("partTimeRelationDao")   //这个值和service层里的那个对象名字一致
public class PartTimeRelationDao extends BaseDao<PartTimeRelation>{

}
