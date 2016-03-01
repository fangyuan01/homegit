package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * BZP的bean与sql操作工具生成类
 * 
 * @author Chen
 **/
public class ExampleDaoGenerator {
	private static final int version = 40;

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(version, "com.bzp.dap");
		addCity(schema);
		addFlavorHouse(schema);
		addHousePubRecord(schema);
		addHouseShare(schema);
		addPayOrder(schema);
		addPayOrderType(schema);
		addRentHouse(schema);
		addSellHouse(schema);
		addShare(schema);
		addSite(schema);
		addUser(schema);
		addUserRss(schema);
		addUserSiteInfo(schema);
		addVersion(schema);
		addWebHouse(schema);
		new DaoGenerator().generateAll(schema, "../DaoMyAppGenerator/src");
	}

	private static void addCity(Schema schema) {
		Entity type = schema.addEntity("City");
		type.addStringProperty("site").primaryKey();
		type.addStringProperty("cityName");
		type.addStringProperty("street");
		type.addIntProperty("isHot");
		type.addStringProperty("block");
		type.addStringProperty("code");
	}

	private static void addFlavorHouse(Schema schema) {
		Entity type = schema.addEntity("FlavorHouse");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addIntProperty("houseType");
		type.addStringProperty("siteId").primaryKey();
		type.addIntProperty("saleOrRent");
		type.addStringProperty("houseId");
	}

	private static void addHousePubRecord(Schema schema) {
		Entity type = schema.addEntity("HousePubRecord");
		type.addStringProperty("site").primaryKey();
		type.addStringProperty("houseSiteId");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addIntProperty("sellOrRent");
		type.addStringProperty("houseId");
	}

	private static void addHouseShare(Schema schema) {
		Entity type = schema.addEntity("HouseShare");
		type.addStringProperty("creatorUsername");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addIntProperty("houseType");
		type.addStringProperty("shareUsername").primaryKey();
		type.addIntProperty("saleOrRent");
		type.addStringProperty("houseId");
	}

	private static void addPayOrder(Schema schema) {
		Entity type = schema.addEntity("PayOrder");
		type.addIntProperty("amount");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("username");
		type.addStringProperty("additionInfo");
		type.addStringProperty("orderId").primaryKey();
		type.addDoubleProperty("totalPrice");
		type.addStringProperty("payOrderTypeId");
	}

	private static void addPayOrderType(Schema schema) {
		Entity type = schema.addEntity("PayOrderType");
		type.addStringProperty("orderName");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addIntProperty("time");
		type.addStringProperty("orderDistributor");
		type.addStringProperty("orderDescribe");
		type.addDoubleProperty("price");
		type.addStringProperty("orderTypeId").primaryKey();
	}

	private static void addRentHouse(Schema schema) {
		Entity type = schema.addEntity("RentHouse");
		type.addStringProperty("contactor");
		type.addStringProperty("phone");
		type.addStringProperty("ownerName");
		type.addStringProperty("street");
		type.addIntProperty("gardenArea");
		type.addIntProperty("type");
		type.addStringProperty("city");
		type.addStringProperty("ownerPhone");
		type.addIntProperty("totalFloor");
		type.addStringProperty("username");
		type.addIntProperty("houseType");
		type.addStringProperty("payType");
		type.addStringProperty("describe");
		type.addIntProperty("roomNum");
		type.addStringProperty("housePurpose");
		type.addStringProperty("houseDirection");
		type.addIntProperty("transferFee");
		type.addIntProperty("rentType");
		type.addIntProperty("canCut");
		type.addIntProperty("balconyNum");
		type.addStringProperty("gmtCreate");
		type.addDoubleProperty("gpsX");
		type.addDoubleProperty("gpsY");
		type.addIntProperty("cookNum");
		type.addStringProperty("houseNo");
		type.addIntProperty("garageCount");
		type.addStringProperty("images");
		type.addIntProperty("basementArea");
		type.addFloatProperty("houseArea");
		type.addStringProperty("houseProperty");
		type.addIntProperty("carCount");
		type.addStringProperty("block");
		type.addFloatProperty("rentPrice");
		type.addStringProperty("id").primaryKey();
		type.addStringProperty("gmtModify");
		type.addIntProperty("houseFloor");
		type.addStringProperty("title");
		type.addIntProperty("restNum");
		type.addIntProperty("houseYear");
		type.addIntProperty("washroomNum");
		type.addIntProperty("houseRenovation");
		type.addStringProperty("supportingFacilities");
		type.addStringProperty("community");
		type.addIntProperty("officeDegree");
		type.addIntProperty("rentUnit");
		type.addFloatProperty("houseFee");
		type.addStringProperty("isDeleted");
		type.addStringProperty("address");
		type.addStringProperty("houseStatus");
		type.addIntProperty("hallNum");
		type.addIntProperty("canRegister");
	}

	private static void addSellHouse(Schema schema) {
		Entity type = schema.addEntity("SellHouse");
		type.addStringProperty("contactor");
		type.addIntProperty("carNum");
		type.addIntProperty("priceCondition");
		type.addStringProperty("houseProperty");
		type.addStringProperty("phone");
		type.addIntProperty("canDivide");
		type.addFloatProperty("useableArea");
		type.addStringProperty("street");
		type.addFloatProperty("gardenArea");
		type.addStringProperty("block");
		type.addIntProperty("type");
		type.addIntProperty("propertyRight");
		type.addStringProperty("city");
		type.addIntProperty("totalFloor");
		type.addStringProperty("id").primaryKey();
		type.addStringProperty("gmtModify");
		type.addStringProperty("title");
		type.addStringProperty("username");
		type.addIntProperty("houseFloor");
		type.addIntProperty("houseYear");
		type.addIntProperty("houseType");
		type.addIntProperty("restNum");
		type.addStringProperty("describe");
		type.addIntProperty("washroomNum");
		type.addIntProperty("roomNum");
		type.addIntProperty("houseRenovation");
		type.addIntProperty("priceDescribe");
		type.addFloatProperty("housePrice");
		type.addStringProperty("housePurpose");
		type.addStringProperty("supportingFacilities");
		type.addIntProperty("officeDegree");
		type.addStringProperty("community");
		type.addStringProperty("houseDirection");
		type.addFloatProperty("houseFee");
		type.addIntProperty("balconyNum");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtCreate");
		type.addDoubleProperty("gpsX");
		type.addDoubleProperty("gpsY");
		type.addStringProperty("address");
		type.addIntProperty("cookNum");
		type.addStringProperty("houseNo");
		type.addStringProperty("images");
		type.addIntProperty("garageNum");
		type.addFloatProperty("basementArea");
		type.addIntProperty("hallNum");
		type.addIntProperty("canRegister");
		type.addFloatProperty("houseArea");
	}

	private static void addShare(Schema schema) {
		Entity type = schema.addEntity("Share");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("username").primaryKey();
		type.addStringProperty("status");
		type.addStringProperty("shareUsername");
	}

	private static void addSite(Schema schema) {
		Entity type = schema.addEntity("Site");
		type.addStringProperty("siteRules");
		type.addStringProperty("siteIntroduce");
		type.addStringProperty("siteHomeUrl");
		type.addStringProperty("siteDeleteUrl");
		type.addStringProperty("siteName");
		type.addStringProperty("id").primaryKey();
		type.addStringProperty("siteRefreshUrl");
		type.addStringProperty("isDeleted");
		type.addStringProperty("siteLoginUrl");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("gmtModify");
		type.addStringProperty("sitePublicUrl");
		type.addStringProperty("siteRegisterUrl");
	}

	private static void addUser(Schema schema) {
		Entity type = schema.addEntity("User");
		type.addStringProperty("sex");
		type.addStringProperty("phone");
		type.addStringProperty("password");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("username").primaryKey();
		type.addDoubleProperty("gpsX");
		type.addDoubleProperty("gpsY");
		type.addStringProperty("address");
		type.addStringProperty("email");
		type.addStringProperty("realName");
		type.addStringProperty("company");
		type.addStringProperty("licenseDate");
		type.addStringProperty("deviceId");
	}

	private static void addUserRss(Schema schema) {
		Entity type = schema.addEntity("UserRss");
		type.addStringProperty("street");
		type.addStringProperty("block");
		type.addIntProperty("areaMax");
		type.addIntProperty("areaMin");
		type.addStringProperty("type");
		type.addStringProperty("city");
		type.addStringProperty("id").primaryKey();
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("username");
		type.addIntProperty("time");
		type.addStringProperty("source");
		type.addIntProperty("priceMin");
		type.addIntProperty("priceMax");
	}

	private static void addUserSiteInfo(Schema schema) {
		Entity type = schema.addEntity("UserSiteInfo");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addStringProperty("username").primaryKey();
		type.addStringProperty("sitePassword");
		type.addStringProperty("siteId");
		type.addStringProperty("siteName");
		type.addStringProperty("siteUsername");
	}

	private static void addVersion(Schema schema) {
		Entity type = schema.addEntity("Version");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtModify");
		type.addStringProperty("gmtCreate");
		type.addIntProperty("b");
		type.addStringProperty("releaseNote");
		type.addIntProperty("c");
		type.addIntProperty("a");
		type.addStringProperty("iosDownloadUrl");
		type.addStringProperty("androidDownloadUrl");
	}

	private static void addWebHouse(Schema schema) {
		Entity type = schema.addEntity("WebHouse");
		type.addStringProperty("phone");
		type.addStringProperty("street");
		type.addStringProperty("houseMap");
		type.addStringProperty("sourceUrl");
		type.addStringProperty("block");
		type.addStringProperty("type");
		type.addStringProperty("city");
		type.addStringProperty("id").primaryKey();
		type.addStringProperty("gmtModify");
		type.addStringProperty("title");
		type.addIntProperty("area");
		type.addStringProperty("sourceSite");
		type.addStringProperty("describe");
		type.addIntProperty("value");
		type.addStringProperty("priceUnit");
		type.addStringProperty("contacter");
		type.addStringProperty("community");
		type.addStringProperty("hashcode");
		type.addStringProperty("isDeleted");
		type.addStringProperty("gmtCreate");
		type.addFloatProperty("price");
		type.addDoubleProperty("gpsX");
		type.addDoubleProperty("gpsY");
		type.addStringProperty("address");
		type.addStringProperty("floorInfo");
		type.addStringProperty("dellWay");
	}
}
