package collect_data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class CharWordCollector {

	public static void main(String[] args) {
		System.out.println("Start word and phrases ...");
		CharWordCollector cwc = new CharWordCollector();
		cwc.collect();
		System.out.println("Finished!");
	}

	private void collect() {
		Map<String, CChar> cChars = new HashMap<String, CChar>();
		Map<String, CWord> cWords = new HashMap<String, CWord>();
		try {
			List<String> ss = FileUtils.readLines(new File("cwcollector/input.txt"), "UTF-8");
			for (int i = 3500; i <= 3500; i++) {
				getCharWords(ss.get(i), cChars, cWords);
			}
			// for (String s : ss) {
			// getCharWords(s, cChars, cWords);
			// }
			// write
			Gson g = new Gson();
			for (CChar cc : cChars.values()) {
				FileUtils.write(new File("cwcollector/chars.txt"), g.toJson(cc) + "\n", "UTF-8", true);
			}
			for (CWord cw : cWords.values()) {
				FileUtils.write(new File("cwcollector/words.txt"), g.toJson(cw) + "\n", "UTF-8", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCharWords(String han, Map<String, CChar> cChars, Map<String, CWord> cWords) {
		try {
			String resp = Http.getAjaxResponse(han);
			List<String> noresult = new ArrayList<String>();
			if (!StringUtils.isBlank(resp)) {
				CChar c = new CChar();

				String[] ss = resp.split("\\|");

				// char
				c.setHan(han);// han
				String[] ss0ss = ss[0].split(":");
				c.setId(ss0ss[1]); // id
				String[] vs = ss0ss[2].split("=")[1].split(",");
				List<String> viets = new ArrayList<String>();
				for (String v : vs) {
					viets.add(v.trim());
				}
				c.setViet(viets);// viet

				// words
				List<CWord> words = new ArrayList<CWord>();
				for (int i = 1; i < ss.length; i++) {
					String[] ssiss = ss[i].split(":");
					CWord w = new CWord();
					w.setId(ssiss[1]); // id
					String[] ssiss3ss = ssiss[2].split("=");
					w.setHan(ssiss3ss[0]); // han
					w.setViet(ssiss3ss[1]); // viet
					words.add(w);
				}

				// relation
				List<String> wids = new ArrayList<String>();
				for (CWord w : words) {
					wids.add(w.getId());
				}
				c.setWordIds(wids);

				// add to result
				cChars.put(c.getId(), c);
				for (CWord w : words) {
					cWords.put(w.getId(), w);
				}
			} else {
				noresult.add(han);
			}
			FileUtils.writeLines(new File("cwcollector/noresult.txt"), "UTF-8", noresult, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class CChar {
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

	class CWord {
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
}
