package net.projektfriedhof.recbay.controller;

import javax.annotation.Resource;

import net.projektfriedhof.recbay.bean.IncomingRecomendation;
import net.projektfriedhof.recbay.dao.ReccomendationDao;
import net.projektfriedhof.recbay.model.Recomendation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/recomend", method = RequestMethod.POST  )
public class RecomendController {

	@Resource(name = "reccomendationDao")
	ReccomendationDao reccomendationDao;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Recomendation recomend(@RequestBody IncomingRecomendation rec) {
		Recomendation resp = reccomendationDao.addRecomendation(rec);
		return resp;
	}

}
