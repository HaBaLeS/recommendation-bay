package net.projektfriedhof.recbay.dao.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.projektfriedhof.recbay.bean.IncomingRecomendation;
import net.projektfriedhof.recbay.dao.ReccomendationDao;
import net.projektfriedhof.recbay.model.Recomendation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ReccomendationDaoImpl implements ReccomendationDao , InitializingBean {

	@Resource
	private DataSource dataSource;
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
//	@Transactional
//	@Override
//	public List<SomeTest> listData() {
//		List<SomeTest> list = jdbcTemplate.query("select * from sometest", new Long[] {}, new BeanPropertyRowMapper<SomeTest>(SomeTest.class));
//		return list;
//	}

	
	
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
	@Override
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public Recomendation addRecomendation(IncomingRecomendation rec) {

		Recomendation toStore = new Recomendation();
		
		toStore.setUri(rec.getUri());
		toStore.setId(121);
		toStore.setViews(12);
		toStore.setCount(234);
		
		
		return toStore;
	}

	
}
