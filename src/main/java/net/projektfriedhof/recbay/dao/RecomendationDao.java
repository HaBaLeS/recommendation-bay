package net.projektfriedhof.recbay.dao;

import net.projektfriedhof.recbay.bean.IncomingRecomendation;
import net.projektfriedhof.recbay.model.Recomendation;


public interface RecomendationDao extends GenericBaseDao<Recomendation>{

	Recomendation addRecomendation(IncomingRecomendation rec);

	
	
	
}
