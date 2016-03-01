package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * 
 * 
 * @author Chen
 **/
public class ExampleDaoGeneratorHA {
	private static final int version = 1;

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(version, "com.hbue.homeworkapp.dao");
		addUser(schema);
		addCoures(schema);
		addMsg(schema);
		new DaoGenerator().generateAll(schema, "../DaoMyAppGenerator/src");
	}

	private static void addUser(Schema schema) {
		Entity database = schema.addEntity("User");
		database.addStringProperty("userAccount").primaryKey();
		database.addStringProperty("userName");
		database.addStringProperty("userPassword");
	}

	private static void addCoures(Schema schema) {
		Entity database = schema.addEntity("Course");
		database.addLongProperty("courseNum").primaryKey();
		database.addStringProperty("courseName");
		database.addStringProperty("teacherName");
		database.addStringProperty("imgPath");
		database.addStringProperty("courseTime");
		database.addStringProperty("classRoom");
	}

	private static void addMsg(Schema schema) {
		Entity database = schema.addEntity("Msg");
		database.addIdProperty().autoincrement().primaryKey();
		database.addStringProperty("msgTitle");
		database.addStringProperty("msgSender");
		database.addStringProperty("msgInfo");
		database.addStringProperty("msgPath");
		database.addStringProperty("msgData");
	}
}
