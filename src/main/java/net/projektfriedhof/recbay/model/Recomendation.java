package net.projektfriedhof.recbay.model;

import java.util.Date;
import java.util.List;

public class Recomendation {

	Long id;
	Long typeFk;
	String reccomendation;

	RecType type;
	List<BayUser> recomendedBy;
	List<Tag> tags;

}
