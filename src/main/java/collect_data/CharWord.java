package collect_data;

import java.util.List;

public class CharWord {
	private String han;
	private List<String> viet;
	private List<String> pinyin;
	private List<String> meanings;

	public String getHan() {
		return han;
	}

	public void setHan(String han) {
		this.han = han;
	}

	public List<String> getViet() {
		return viet;
	}

	public void setViet(List<String> viet) {
		this.viet = viet;
	}

	public List<String> getPinyin() {
		return pinyin;
	}

	public void setPinyin(List<String> pinyin) {
		this.pinyin = pinyin;
	}

	public List<String> getMeanings() {
		return meanings;
	}

	public void setMeanings(List<String> meanings) {
		this.meanings = meanings;
	}

}
