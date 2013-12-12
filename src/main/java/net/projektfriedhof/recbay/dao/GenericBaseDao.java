package net.projektfriedhof.recbay.dao;

import java.util.List;

import net.projektfriedhof.recbay.model.DBObject;

/**
 * Created with IntelliJ IDEA.
 * User: falko
 * Date: 08.08.13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public interface GenericBaseDao<T extends DBObject> {

	void persist(T object);


	/**
	 * Does a full update with no Dirty check. if you need special partitial update
	 * if oyu need something else!
	 * 
	 * @param object
	 */
	void fullUpdate(T object);


	/**
	 * do 
	 * @param object
	 */
	void delete(T object);


	T loadById(long id, Class<T> typ);


	void execute(String sql, Object[] params);


	List<T> list(long from, long pagesize, Class<T> typ);


	long count(Class<T> typ);

	long count(String SQL,Object ... params);

	


	/**
	 * To set the ID automatically, set the "sequenceName" in the GenericTableEntity annotation.
	 * 
	 * @param typ
	 * @param listToPersist
	 */
	void persistAll(Class<T> typ, List<? extends DBObject> listToPersist);


	
	
	 /**
	  * Load a SINGLE object form T with a custom SQL and parameters 
	  * 
	  * @param sql
	  * @param clazz
	  * @param params
	  * @return
	  */
	 T getByQuery(String sql,Class clazz ,Object ... params); 
	 
}
