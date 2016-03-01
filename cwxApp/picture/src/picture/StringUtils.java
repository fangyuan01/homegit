package picture;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 提取字符串中数字
	 * 
	 * @param str
	 * @return
	 */
	public static int getNumberFromString(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return Integer.parseInt(m.replaceAll("").trim());
	}
}
