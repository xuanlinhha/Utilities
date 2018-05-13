package json;

import java.util.List;

public class CCGraphic {
	private String character;
	private List<String> strokes;
	private List<List<List<Double>>> medians;

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public List<String> getStrokes() {
		return strokes;
	}

	public void setStrokes(List<String> strokes) {
		this.strokes = strokes;
	}

	public List<List<List<Double>>> getMedians() {
		return medians;
	}

	public void setMedians(List<List<List<Double>>> medians) {
		this.medians = medians;
	}

}
