package json;

import java.util.List;

public class CCDictionary {
	private String character;
	private String definition;
	private List<String> pinyin;
	private String decomposition;
	private transient String etymology;
	private String radical;
	private transient List<List<List<Integer>>> matches;

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

	public String getEtymology() {
		return etymology;
	}

	public void setEtymology(String etymology) {
		this.etymology = etymology;
	}

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public List<List<List<Integer>>> getMatches() {
		return matches;
	}

	public void setMatches(List<List<List<Integer>>> matches) {
		this.matches = matches;
	}

}
