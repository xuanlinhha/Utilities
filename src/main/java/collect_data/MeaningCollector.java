package collect_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class MeaningCollector {
	public static void main(String[] args) throws IOException {
		MeaningCollector mc = new MeaningCollector();
		System.out.println("Start collecting char meanings ...");
		mc.collectCharsMeanings();
		System.out.println("Finish collecting char meanings!");
		System.out.println("--------");
		System.out.println("Start collecting word meanings ...");
		mc.collectWordMeanings();
		System.out.println("Finish collecting word meanings!");
	}

	private void collectWordMeanings() {
		try {
			List<String> lines = FileUtils.readLines(new File("cwcollector/words_bk.txt"), "UTF-8");
			// char ids
			List<String> wids = new ArrayList<String>();
			for (String line : lines) {
				String han = StringUtils.substringBetween(line, "\"id\":\"", "\",\"han\"");
				wids.add(han);
			}
			// get and write
			Gson gson = new Gson();
			for (int i = 0; i < wids.size(); i++) {
				Meaning m = getWordMeanings(wids.get(i));
				FileUtils.write(new File("cwcollector/word_meanings.txt"), gson.toJson(m) + "\n", "UTF-8", true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Meaning getWordMeanings(String wordid) {
		Meaning m = new Meaning();
		// unichar
		m.setUnichar(wordid);

		// meanings
		List<String> meanings = new ArrayList<String>();
		try {
			String html = Http.getPhraseDetail(wordid);
			Document doc = Jsoup.parse(html);
			Elements lis = doc.select("LI");
			for (Element li : lis) {
				meanings.add(reformat(li));
			}
		} catch (Exception e) {
			System.out.println("Error: " + wordid);
			e.printStackTrace();
		}
		m.setMeanings(meanings);
		return m;
	}

	private void collectCharsMeanings() {
		try {
			List<String> lines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
			// char ids
			List<String> cids = new ArrayList<String>();
			for (String line : lines) {
				String han = StringUtils.substringBetween(line, "\"id\":\"", "\",\"han\"");
				cids.add(han);
			}
			// get and write
			Gson gson = new Gson();
			for (int i = 0; i < cids.size(); i++) {
				Meaning m = getCharMeanings(cids.get(i));
				FileUtils.write(new File("cwcollector/char_meanings.txt"), gson.toJson(m) + "\n", "UTF-8", true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Meaning getCharMeanings(String unichar) {
		Meaning m = new Meaning();
		// unichar
		m.setUnichar(unichar);

		// meanings
		List<String> meanings = new ArrayList<String>();
		try {
			String html = Http.getCharDetail(unichar);
			Document doc = Jsoup.parse(html);
			Elements lis = doc.select("LI");
			for (Element li : lis) {
				meanings.add(reformat(li));
			}
		} catch (Exception e) {
			System.out.println("Error: " + unichar);
			e.printStackTrace();
		}
		m.setMeanings(meanings);
		return m;
	}

	private String reformat(Element element) {
		StringBuilder sb = new StringBuilder();
		for (Node n : element.childNodes()) {
			// System.out.println(n);
			if (n instanceof Element) {
				Element e = (Element) n;
				if (e.tagName().equals("a")) {
					sb.append("<span style='color:blue'>");
					sb.append(e.text());
					sb.append("</span> ");
					// } else if (e.tagName().equals("b")) {
					// sb.append("<i>");
					// sb.append(e.text());
					// sb.append("</i> ");
				} else {
					sb.append(e.toString());
				}
			} else {
				sb.append(n.toString());
			}
		}
		return sb.toString().trim();
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

}
