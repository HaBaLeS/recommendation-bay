package net.projektfriedhof.recbay.dao.impl;

import net.projektfriedhof.recbay.bean.IncomingRecomendation;
import net.projektfriedhof.recbay.dao.RecomendationDao;
import net.projektfriedhof.recbay.model.Recomendation;


public class RecomendationDaoImpl extends GenericBaseDaoImpl<Recomendation> implements RecomendationDao  {



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
