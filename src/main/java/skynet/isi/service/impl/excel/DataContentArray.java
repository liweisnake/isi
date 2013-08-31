package skynet.isi.service.impl.excel;

import java.util.List;

public class DataContentArray {

	private static final int CONTENT_LENGTH = 26;

	static String[] contents = new String[CONTENT_LENGTH];

	static void initArray() {
		for (int i = 0; i < contents.length; i++) {
			contents[i] = "";
		}
	}

	static void fillListToArray(List<String> s) {
		for (int i = 0; i < s.size(); i++) {
			contents[i] = s.get(i);
		}
	}

	static ExcelDataModel createExcelDataModel() {
		return new ExcelDataModel(contents[0], contents[1], contents[2],
				contents[3], contents[4], contents[5], contents[6],
				contents[7], contents[8], contents[9], contents[10],
				contents[11], contents[12], contents[13], contents[14],
				contents[15], contents[16], "有", contents[17], contents[18],
				contents[19], contents[20], contents[21], contents[22],
				contents[23], contents[24], contents[25]);
	}
}
