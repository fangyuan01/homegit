package picture;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCompare implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		String[] strs1 = o1.split("\\\\");
		String[] strs2 = o2.split("\\\\");

		String str1 = strs1[strs1.length - 1];
		String str2 = strs2[strs2.length - 1];

		String str3 = strs1[strs1.length - 3];
		String str4 = strs2[strs2.length - 3];

		int num1 = Integer.valueOf(str1.substring(0, str1.indexOf(".")));
		int num2 = Integer.valueOf(str2.substring(0, str2.indexOf(".")));

		// System.out.println(str1 + ".." + str3 + ".." + str1.substring(0,
		// str1.indexOf(".")) + ".." + str3.substring(4, str3.length()));

		if (!str3.equals(str4)) {
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str3);
			str3 = m.replaceAll("").trim();

			Matcher m2 = p.matcher(str4);
			str4 = m2.replaceAll("").trim();

			int num3 = Integer.valueOf(str3);
			int num4 = Integer.valueOf(str4);
			return Integer.valueOf(num3 - num4);
		}

		return num1 - num2;
	}

	@Override
	public Comparator<String> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<String> thenComparing(Comparator<? super String> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<String> thenComparingInt(ToIntFunction<? super String> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<String> thenComparingLong(ToLongFunction<? super String> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<String> thenComparingDouble(ToDoubleFunction<? super String> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

}