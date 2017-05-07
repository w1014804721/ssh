package me.hagen.ssh.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao<T> extends HibernateDaoSupport{
	
	public Session getSession()
	{
		return this.currentSession();
	}
	public void create(T obj)
	{
	
		Session session = getSession();
		session.persist(obj);
	}
	public void createOrUpdate(T obj)
	{
		Session session = getSession();
		session.saveOrUpdate(obj);
	}
	public void update(T obj)
	{
		Session session  = getSession();

		session.update(obj);
	}
	public void delete(T obj)
	{
		Session session  = getSession();
		session.delete(obj);
	}
	@SuppressWarnings("unchecked")
	public T find(Class<? extends T> clazz,Serializable id)
	{
		Session session  = getSession();
		return (T)session.get(clazz, id);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listSql(Class<? extends T> clazz,String sql)
	{
		Session session  = getSession();
			return session.createSQLQuery(sql).addEntity(clazz).list();

	}
	@SuppressWarnings("unchecked")
	public List<T> listSql(Class<? extends T> clazz,String sql,int start,int pagelistnum)
	{

		Session session = getSession();
	
			return session.createSQLQuery(sql)
					.addEntity(clazz)
					.setFirstResult(start)
					.setMaxResults(pagelistnum)
					.list();
	}
	@SuppressWarnings("unchecked")
	public List<T> listHql(String hql)
	{
		
		Session session = getSession();
			return session.createQuery(hql).list();
			//return session.createSQLQuery(sql).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> listHql(String hql,int start,int pagelistnum)
	{
		
		Session session = getSession();
	
			return session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(pagelistnum)
					.list();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> list( Class clazz,int start,int pagelistnum)
	{
		Session session = getSession();
			return session.createQuery("from "+clazz.getName()+" ")
					.setFirstResult(start)
					.setMaxResults(pagelistnum)
					.list();
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> list( Class clazz)
	{
		
		Session session = getSession();
	
			return session.createQuery("from "+clazz.getName()+" ")
					.list();
			//return session.createSQLQuery(sql).list();
	}
	public int uniqueResult(String hql)
	{
		Session session = getSession();
	
			Number num = (Number)session.createQuery(hql).uniqueResult();
			//return session.createSQLQuery(sql).list();
			return num.intValue();
	}
	public int uniqueResultSql(String sql)
	{

		Session session = getSession();
	
			Number num = (Number)session.createSQLQuery(sql).uniqueResult();
			//return session.createSQLQuery(sql).list();
			return num.intValue();
	
	}
	public void template(SessionProcessor sp)
	{
		Session session = getSession();
			sp.process(session);
			//return session.createSQLQuery(sql).list()
	}

	@Resource
	public void setFactory(SessionFactory factory)
	{
		super.setSessionFactory(factory);
	}
}
