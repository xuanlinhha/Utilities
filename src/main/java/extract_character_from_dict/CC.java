package extract_character_from_dict;

import java.util.List;

public class CC {
	private Integer codePoint;
	private String character;
	private String definition;
	private List<String> pinyin;
	private String decomposition;
	private String radical;
	private String vietnamese;
	private String svgString;

	public Integer getCodePoint() {
		return codePoint;
	}

	public void setCodePoint(Integer codePoint) {
		this.codePoint = codePoint;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public List<String> getPinyin() {
		return pinyin;
	}

	public void setPinyin(List<String> pinyin) {
		this.pinyin = pinyin;
	}

	public String getDecomposition() {
		return decomposition;
	}

	public void setDecomposition(String decomposition) {
		this.decomposition = decomposition;
	}

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public String getVietnamese() {
		return vietnamese;
	}

	public void setVietnamese(String vietnamese) {
		this.vietnamese = vietnamese;
	}

	public String getSvgString() {
		return svgString;
	}

	public void setSvgString(String svgString) {
		this.svgString = svgString;
	}

}
