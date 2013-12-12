package net.projektfriedhof.recbay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.projektfriedhof.recbay.dao.RecomendationDao;
import net.projektfriedhof.recbay.model.Recomendation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/index")
public class Home {
	
	@Resource(name="recomendationDao")
	RecomendationDao recomendationDao;
	
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Recomendation> home() {
    	ArrayList<Recomendation> arrayList = new ArrayList<>() ;
    	arrayList.add(new Recomendation());
    	return arrayList;
    }
}