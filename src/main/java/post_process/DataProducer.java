package post_process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class DataProducer {

	public static void main(String[] args) throws IOException {
		DataProducer c = new DataProducer();
		c.produce();
	}

	private void produce() throws IOException {
		// read chars
		Gson gson = new Gson();
		// {"id":"32456","han":"终","viet":["chung"],"wordIds":[]}
		List<String> clines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		Map<Integer, CChar> ccharMap = new HashMap<Integer, CChar>();
		for (String c : clines) {
			CChar cchar = gson.fromJson(c, CChar.class);
			ccharMap.put(cchar.getHan().codePointAt(0), cchar);
		}

		// read pinyin
		List<String> plines = FileUtils.readLines(new File("cwcollector/pinyin.txt"), "UTF-8");
		Map<Integer, CPinyin> pinMap = new HashMap<Integer, CPinyin>();
		for (String c : plines) {
			CPinyin pin = gson.fromJson(c, CPinyin.class);
			pinMap.put(pin.getC().codePointAt(0), pin);
		}

		// read phrases
		List<String> phlines = FileUtils.readLines(new File("cwcollector/words_bk.txt"), "UTF-8");
		Map<String, CPhrase> phraseMap = new HashMap<String, CPhrase>();
		for (String pl : phlines) {
			CPhrase ph = gson.fromJson(pl, CPhrase.class);
			phraseMap.put(ph.getId(), ph);
		}

		// process
		List<String> data = new ArrayList<String>();

		Collection<CChar> charCol = ccharMap.values();
		for (CChar cc : charCol) {
			CharInfo charInfo = new CharInfo();
			// han-viet
			charInfo.setHan(cc.getHan());
			charInfo.setViet(cc.getViet());

			// pinyin
			charInfo.setPinyin(pinMap.get(cc.getHan().codePointAt(0)).getPinyin());

			// phrases
			List<PhraseInfo> phinfos = new ArrayList<PhraseInfo>();
			for (String wid : cc.getWordIds()) {
				PhraseInfo pi = new PhraseInfo();
				pi.setHan(phraseMap.get(wid).getHan());
				pi.setViet(phraseMap.get(wid).getViet());
				phinfos.add(pi);
			}
			charInfo.setPhrases(phinfos);

			// add
			data.add(gson.toJson(charInfo));
		}
		FileUtils.writeLines(new File("post_process/dict.txt"), "UTF-8", data);
		System.out.println("Finished!");
	}

	class CharInfo {
		private String han;
		private List<String> viet;
		private List<String> pinyin;
		private List<PhraseInfo> phrases;

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

		public List<PhraseInfo> getPhrases() {
			return phrases;
		}

		public void setPhrases(List<PhraseInfo> phrases) {
			this.phrases = phrases;
		}

	}

	class PhraseInfo {
		private String han;
		private String viet;

		public String getHan() {
			return han;
		}

		public void setHan(String han) {
			this.han = han;
		}

		public String getViet() {
			return viet;
		}

		public void setViet(String viet) {
			this.viet = viet;
		}

	}

	class CPhrase {
		// {"id":"171664","han":"皮脂","viet":"bì chi"}
		private String id;
		private String han;
		private String viet;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getHan() {
			return han;
		}

		public void setHan(String han) {
			this.han = han;
		}

		public String getViet() {
			return viet;
		}

		public void setViet(String viet) {
			this.viet = viet;
		}

	}

	class CPinyin {
		// {"c":"者","pinyin":["zhě","zhū"]}
		private String c;
		private List<String> pinyin;

		public String getC() {
			return c;
		}

		public void setC(String c) {
			this.c = c;
		}

		public List<String> getPinyin() {
			return pinyin;
		}

		public void setPinyin(List<String> pinyin) {
			this.pinyin = pinyin;
		}

	}

	class CChar {
		// {"id":"32456","han":"终","viet":["chung"],"wordIds":[]}
		private String id;
		private String han;
		private List<String> viet;
		private List<String> wordIds;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

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

		public List<String> getWordIds() {
			return wordIds;
		}

		public void setWordIds(List<String> wordIds) {
			this.wordIds = wordIds;
		}

	}

}
