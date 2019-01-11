package entity;

import java.util.HashMap;
import java.util.Map;

public class Station {
	public static final String CHI_NAME[] = {"南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營"};
	public static final Map<String, Integer> chineseToIndex = new HashMap<String, Integer>();  
	static {
		// chinese name -----> index mapping 
		for(int i = 0; i < CHI_NAME.length; i++) {
			chineseToIndex.put(CHI_NAME[i], Integer.valueOf(i));
		}
	}
}

