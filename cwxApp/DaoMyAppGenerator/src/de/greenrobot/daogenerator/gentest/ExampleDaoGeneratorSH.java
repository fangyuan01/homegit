package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * 
 * 
 * @author Chen
 **/
public class ExampleDaoGeneratorSH {
	private static final int version = 1;

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(version, "com.hbueschoolhelper.dao");
		addCoures(schema);
		addShop(schema);
		new DaoGenerator().generateAll(schema, "../DaoMyAppGenerator/src");
	}

	private static void addCoures(Schema schema) {
		Entity database = schema.addEntity("Course");
		database.addIdProperty().autoincrement().primaryKey();
		database.addStringProperty("courseName");
		database.addStringProperty("teacherName");
		database.addStringProperty("classRoom");
		database.addIntProperty("week");
		database.addIntProperty("classLength");
		database.addIntProperty("oddWeeks");
		database.addIntProperty("startweek");
		database.addIntProperty("lastweek");
	}

	private static void addShop(Schema schema) {
		Entity database = schema.addEntity("Shop");
		database.addIdProperty().autoincrement().primaryKey();
		database.addStringProperty("shopName");
		database.addStringProperty("shopImgPath");
		database.addStringProperty("shopPhone");
	}
}
