package post_process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import post_process.DataProducer.PhraseInfo;

public class MeaningCombine {
	public static void main(String[] args) throws IOException {
		System.out.println("Start ...");
		MeaningCombine mc = new MeaningCombine();
		mc.combine();
		System.out.println("Finished!");
	}

	private void combine() throws IOException {
		// read dictionary
		Map<String, List<String>> hanviet = new HashMap();
		Gson g = new Gson();
		List<String> dls = FileUtils.readLines(new File("post_process/dict.txt"), "UTF-8");
		for (String dl : dls) {
			DictEntry de = g.fromJson(dl, DictEntry.class);
			hanviet.put(de.getHan(), de.getViet());// put char
			for (PhraseInfo pi : de.getPhrases()) {
				List<String> piviet = new ArrayList();
				piviet.add(pi.getViet());
				hanviet.put(pi.getHan(), piviet);// put phrase
			}
		}
		System.out.println("hanviet size = " + hanviet.size());

		// char meaning
		List<String> cms = FileUtils.readLines(new File("post_process/char_meanings.txt"), "UTF-8");
		for (String cm : cms) {
			PreMeaning m = g.fromJson(cm, PreMeaning.class);
			PostMeaning pm = new PostMeaning();
			pm.setHan(m.getHan());
			pm.setViet(hanviet.get(m.getHan()));
			pm.setMeanings(m.getMeanings());
			// write
			FileUtils.write(new File("post_process/char_meanings_post.txt"), g.toJson(pm) + "\n", "UTF-8", true);
		}
		// System.out.println("hanviet size = " + hanviet.size());

		// char meaning
		List<String> wms = FileUtils.readLines(new File("post_process/word_meanings.txt"), "UTF-8");
		for (String wm : wms) {
			PreMeaning m = g.fromJson(wm, PreMeaning.class);
			PostMeaning pm = new PostMeaning();
			pm.setHan(m.getHan());
			pm.setViet(hanviet.get(m.getHan()));
			pm.setMeanings(m.getMeanings());
			// write
			FileUtils.write(new File("post_process/word_meanings_post.txt"), g.toJson(pm) + "\n", "UTF-8", true);
		}
	}

	class PreMeaning {
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

	class PostMeaning {
		private String han;
		private List<String> viet;
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

		public List<String> getMeanings() {
			return meanings;
		}

		public void setMeanings(List<String> meanings) {
			this.meanings = meanings;
		}
	}

	class DictEntry {
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
}
