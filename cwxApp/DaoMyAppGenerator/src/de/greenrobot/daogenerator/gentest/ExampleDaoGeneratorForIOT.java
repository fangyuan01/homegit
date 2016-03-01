package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * BZP的bean与sql操作工具生成类
 * 
 * @author Chen
 **/
public class ExampleDaoGeneratorForIOT {
	private static final int version = 1;

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(version, "com.internetofthings.dao");
		addInsturtStr(schema);
		addEquipmentData(schema);
		addInsturtHistory(schema);
		new DaoGenerator().generateAll(schema, "../DaoMyAppGenerator/src");
	}

	private static void addInsturtHistory(Schema schema) {
		Entity insturts = schema.addEntity("InsturtHistory");
		insturts.addIdProperty();
		insturts.addStringProperty("DataTime");
		insturts.addStringProperty("IsSuccess");
		insturts.addStringProperty("InsturtType");
		insturts.addStringProperty("EquipmentName");
	}

	private static void addInsturtStr(Schema schema) {
		Entity insturts = schema.addEntity("Insturt");
		insturts.addIdProperty();
		insturts.addStringProperty("Insturt");
	}

	private static void addEquipmentData(Schema schema) {
		Entity insturts = schema.addEntity("EquipmentData");
		insturts.addIdProperty();
		insturts.addStringProperty("account");
		insturts.addStringProperty("name");
		insturts.addStringProperty("status");
		insturts.addIntProperty("imgNum");
		insturts.addStringProperty("waitTimeEnable");
		insturts.addIntProperty("waitTimeLong");
		insturts.addStringProperty("watiTimeOperation");
		insturts.addStringProperty("timingIsEveryday");
		insturts.addIntProperty("timingHour");
		insturts.addIntProperty("timingMinute");
		insturts.addIntProperty("timingSecond");
		insturts.addStringProperty("timingOperation");
	}

}
