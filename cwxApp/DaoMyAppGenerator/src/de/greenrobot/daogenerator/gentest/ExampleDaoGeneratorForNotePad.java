package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * BZP的bean与sql操作工具生成类
 * 
 * @author Chen
 **/
public class ExampleDaoGeneratorForNotePad {
	private static final int version = 1;

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(version, "com.notepad.dao");
		addNote(schema);
		new DaoGenerator().generateAll(schema, "../DaoMyAppGenerator/src");
	}

	private static void addNote(Schema schema) {
		Entity note = schema.addEntity("Note");
		note.addIdProperty().primaryKey();
		note.addStringProperty("ReleaseTime"); // 发布时间
		note.addStringProperty("RemindTime"); // 提醒时间
		note.addStringProperty("NoteTitle"); // 事物标题
		note.addStringProperty("NoteInfo"); // 事物内容
		note.addStringProperty("NoteIcon"); // 事物图标
	}

}
