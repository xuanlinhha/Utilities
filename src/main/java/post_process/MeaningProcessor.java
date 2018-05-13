package post_process;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class MeaningProcessor {
	public static void main(String[] args) throws IOException {
		MeaningProcessor mp = new MeaningProcessor();
		// mp.processCharMeaning();
		mp.processWordMeaning();
	}

	private void processWordMeaning() throws IOException {
		System.out.println("Start ...");
		// get han
		Map<String, String> hans = new HashMap<String, String>();
		List<String> clines = FileUtils.readLines(new File("cwcollector/words_bk.txt"), "UTF-8");
		for (String cline : clines) {
			String id = StringUtils.substringBetween(cline, "\"id\":\"", "\",\"han\"");
			String han = StringUtils.substringBetween(cline, "\"han\":\"", "\",\"viet\"");
			hans.put(id, han);
		}

		// process line by line in pre meanings
		List<String> cmlines = FileUtils.readLines(new File("cwcollector/word_meanings.txt"), "UTF-8");
		Gson gson = new Gson();
		for (String cline : cmlines) {
			Meaning cm = gson.fromJson(cline, Meaning.class);
			PostMeaning pm = new PostMeaning();
			pm.setHan(hans.get(cm.getUnichar()));
			pm.setMeanings(cm.getMeanings());
			FileUtils.write(new File("post_process/word_meanings.txt"), gson.toJson(pm) + "\n", "UTF-8", true);
		}
		System.out.println("Finished!");
	}

	private void processCharMeaning() throws IOException {
		System.out.println("Start ...");
		// get han
		Map<String, String> hans = new HashMap<String, String>();
		List<String> clines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		for (String cline : clines) {
			String id = StringUtils.substringBetween(cline, "\"id\":\"", "\",\"han\"");
			String han = StringUtils.substringBetween(cline, "\"han\":\"", "\",\"viet\"");
			hans.put(id, han);
		}

		// process line by line in pre meanings
		List<String> cmlines = FileUtils.readLines(new File("cwcollector/char_meanings.txt"), "UTF-8");
		Gson gson = new Gson();
		for (String cline : cmlines) {
			Meaning cm = gson.fromJson(cline, Meaning.class);
			PostMeaning pm = new PostMeaning();
			pm.setHan(hans.get(cm.getUnichar()));
			pm.setMeanings(cm.getMeanings());
			FileUtils.write(new File("post_process/char_meanings.txt"), gson.toJson(pm) + "\n", "UTF-8", true);
		}
		System.out.println("Finished!");
	}

	class Meaning {
		private String unichar;
		private List<String> meanings;

		public String getUnichar() {
			return unichar;
		}

		public void setUnichar(String unichar) {
			this.unichar = unichar;
		}

		public List<String> getMeanings() {
			return meanings;
		}

		public void setMeanings(List<String> meanings) {
			this.meanings = meanings;
		}
	}

	class PostMeaning {
		private String han;
		private List<String> meanings;

		public String getHan() {
			return han;
		}

		public void setHan(String han) {
			this.han = han;
		}

		public List<String> getMeanings() {
			return meanings;
		}

		public void setMeanings(List<String> meanings) {
			this.meanings = meanings;
		}
	}
}
