package net.projektfriedhof.recbay.dao.impl;

import java.beans.Transient;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.projektfriedhof.recbay.dao.GenericBaseDao;
import net.projektfriedhof.recbay.model.DBObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @spring-bean="baseDao"
 * 
 * 
 * 
 * 
 */
public class GenericBaseDaoImpl<T extends DBObject> implements GenericBaseDao<T> {

	private static final Logger LOG = LoggerFactory.getLogger(GenericBaseDaoImpl.class);

	@Resource
	private DataSource dataSource;
	//protected JdbcTemplate jdbcTemplate;
	//protected NamedParameterJdbcTemplate namedParamJdbcTemplate;

	private String generatedKeyName = "id";

	List<String> updateParams = new ArrayList<>();


	/**
	 * 
	 */
	@Override
	public final void fullUpdate(T object) {

		Object[] params;

		StringBuilder SQL = new StringBuilder("UPDATE " + object.getClass().getSimpleName() + " SET ");

		if(updateParams.size() == 0) {
			buildUpdateParams(object);
		}
		params = new Object[updateParams.size() + 1];

		int i = 0;
		for (String str : updateParams) {
			SQL.append(" " + str + " = ? ");
			if(i + 1 < updateParams.size()) {
				SQL.append(", ");
			}
			params[i] = getValueForField(str, object);
			i++;
		}

		//Set ID thing
		params[i] = object.getId();
		SQL.append(" where id = ? and deletedAt is null");

		//jdbcTemplate.update(SQL.toString(), params);

	}


	/**
	 * 
	 * @param fn
	 * @param object
	 * @return
	 */
	private Object getValueForField(String fn, T object) {
        /*
		try {
			Method method = object.getClass().getMethod("get" + StringUtils.capitalize(fn), (Class<?>[]) null);
			Object ret = method.invoke(object, (Object[]) null);
			if(ret instanceof Enum) {
				ret = ((Enum) ret).name();
			}
			return ret;
		} catch (Exception e) {
			throw new RuntimeException("Reflection Code is Broken --- or DB does not match entity", e);
		}*/
        return null;
	}


	/**
	 * 
	 * @param object
	 */
	//@Transactional
	private void buildUpdateParams(T object) {

		Method[] methods = object.getClass().getMethods();
		for (Method method : methods) {
			if(method.getName().startsWith("get") && method.getAnnotation(Transient.class) == null) {
				String substring = method.getName().substring(3);
				if(!"Class".equals(substring) && !"Id".equals(substring)) {
					updateParams.add(substring);
				}
			}
		}
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final void delete(T object) {
//		T tee = loadById(object.getId(), (Class<T>) object.getClass());
//		if(tee != null) {
//			fullUpdate(tee);
//		}
		throw new RuntimeException("Don't want to Delete anything!!");
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final T loadById(long id, Class<T> typ) {
		//return jdbcTemplate.queryForObject("select * from " + typ.getSimpleName() + " where id = ? and deletedAt is null", new Long[] {id}, new BeanPropertyRowMapper<T>(typ));
        return null;
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final void execute(String sql, Object[] params) {
		//jdbcTemplate.update(sql, params);
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final void persist(DBObject toPersist) {
	/*	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		String[] schemaTableName = new String[]{"public"};

		insertActor.setSchemaName("public");
		insertActor.setTableName(toPersist.getClass().getSimpleName());
		insertActor.setGeneratedKeyName(generatedKeyName);

		BeanPropertySqlParameterSource pms = new BeanPropertySqlParameterSource(toPersist);
		Integer id = (Integer) insertActor.executeAndReturnKey(pms);
		toPersist.setId(id);*/
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final void persistAll(Class<T> typ, List<? extends DBObject> listToPersist) {

		List<Integer> nextIds = getNextIds(listToPersist.size(), typ);

		Iterator<Integer> idIterator = null;
		if(nextIds != null) {
			idIterator = nextIds.iterator();
		}

		for (DBObject genericEntity : listToPersist) {
			if(idIterator != null) {
				genericEntity.setId(idIterator.next());
			}
		}

		persistAllBatch(typ, listToPersist);

	}

	/**
	 * 
	 * @param typ
	 * @param listToPersist
	 */
	//@Transactional
	private void persistAllBatch(Class<T> typ, List<? extends DBObject> listToPersist) {
	/*	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

		if(listToPersist == null || listToPersist.size() == 0){
			return;
		}
		
		insertActor.setSchemaName("public");
		insertActor.setTableName(listToPersist.get(0).getClass().getSimpleName());

		BeanPropertySqlParameterSource[] dataArray = new BeanPropertySqlParameterSource[listToPersist.size()];

		for (int i = 0; i < listToPersist.size(); i++) {
			DBObject bean = listToPersist.get(i);
			dataArray[i] = new BeanPropertySqlParameterSource(bean);
		}

		insertActor.executeBatch(dataArray);*/
	}


	/**
	 * Invoked by a BeanFactory after it has set all bean properties supplied
	 * (and satisfied BeanFactoryAware and ApplicationContextAware).
	 * <p>
	 * This method allows the bean instance to perform initialization only possible when all bean properties have been set and to throw an
	 * exception in the event of misconfiguration.
	 * 
	 * @throws Exception
	 *            in the event of misconfiguration (such
	 *            as failure to set an essential property) or if initialization fails.
	 */
	//@Transactional
	public void afterPropertiesSet() throws Exception {
	/*	jdbcTemplate = new JdbcTemplate(dataSource);
		namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);*/

	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final List<T> list(long from, long pagesize, Class<T> typ) {
		/*List<T> list = jdbcTemplate.query("select * from " + typ.getSimpleName() + " where  deletedAt is null order by id asc limit ? offset ?", new Long[] {
				pagesize, from
		}, new BeanPropertyRowMapper<T>(typ));
		return list;*/
    return null;
	}


	/**
	 * 
	 */
	@Override
	//@Transactional
	public final long count(Class<T> typ) {
		//return jdbcTemplate.queryForObject("select count(*) from " + typ.getSimpleName() + " where  deletedAt is null", Integer.class);
        return 0;
	}

	/**
	 * 
	 * @param amount
	 * @param type
	 * @return
	 */
	//@Transactional
	public List<Integer> getNextIds(int amount, Class<T> type) {

	/*	//get sequence name
		String sequenceCommand = jdbcTemplate.queryForObject("SELECT column_default from information_schema.columns where  column_name = 'id' AND table_name = '" + type.getName() + "' AND table_schema = 'public'", String.class);

		if(sequenceCommand == null || sequenceCommand.trim().isEmpty() || !sequenceCommand.startsWith("nextval")) {
			return null;
		}

		List<Integer> ids = new ArrayList<Integer>(amount);
		for (int i = 0; i < amount; i++) {
			ids.add(jdbcTemplate.queryForObject("select " + sequenceCommand, Integer.class));
		}
		return ids;
		*/
        return null;
	}



	


	/**
	 * @param SQL 
	 * @param clazz class we want to load
	 * @param Object[] with parameters
	 * @return single result
	 */
//	@Transactional
	public T getByQuery(String sql,Class clazz ,Object ... params) {
		//return (T) jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<T>((Class<T>) clazz) );
        return null;
	}


	/* (non-Javadoc)
	 * @see de.virtualsolution.leasemotion.whitelabel.configuration.service.dao.GenericBaseDao#count(java.lang.String, java.lang.Object[])
	 */
	@Override
	//@Transactional
	public long count(String SQL, Object... params) {
		//return jdbcTemplate.queryForObject(SQL, params, Integer.class);
        return 0;
	}


}
