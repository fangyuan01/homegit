package picture;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class AddFile {

	public static void main(String[] args) {
		List<String> paths = FileUtils.loadFiles("source");
		Collections.sort(paths, new FileCompare());
		for (String item : paths) {

			System.out.println(item);
			String[] strs = item.split("\\\\");
			String number = strs[strs.length - 3];
			int sizeInt = Integer.valueOf(strs[strs.length - 2]);
			int num = Integer.valueOf(number.substring(5, number.length()));
			String fileName = strs[strs.length - 1];
			if (num % 2 == 0) {
				int fileNum = Integer.valueOf(fileName.substring(0, fileName.indexOf(".")));
				if (sizeInt != 800) {
					fileNum += 16;
				} else {
					fileNum += 4;
				}
				fileName = new DecimalFormat("00").format(fileNum) + ".jpg";
			}

			if (!item.endsWith("zip")) {
				String newDir = "result\\" + strs[4] + "\\" + strs[4] + ((num + 1) / 2) + "\\" + strs[6] + "\\" + fileName;
				FileUtils.copyFile(item, newDir);
			}
		}
	}
}
