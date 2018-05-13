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
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class PinyinCollector {
	public static void main(String[] args) throws IOException {
		System.out.println("Start collect pinyin ...");
		PinyinCollector dc = new PinyinCollector();
		dc.getPinyins();
		System.out.println("Finished!");
	}

	private void getPinyins() throws IOException {
		// load data
		List<String> lines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		List<String> ss = new ArrayList<String>();
		for (String line : lines) {
			String han = StringUtils.substringBetween(line, "\"han\":\"", "\",\"viet\"");
			ss.add(han);
		}

		// crawl
		Gson g = new Gson();
		for (int i = 0; i < ss.size(); i++) {
			String s = ss.get(i);
			List<String> pinyin = getPinyin(s);
			Pinyin p = new Pinyin();
			p.setC(s);
			p.setPinyin(pinyin);
			FileUtils.write(new File("cwcollector/pinyin.txt"), g.toJson(p) + "\n", "UTF-8", true);
		}
	}

	public static List<String> getPinyin(String unichar) {
		List<String> pinyin = new ArrayList<String>();
		try {
			String html = Http.getCharDetail(unichar);
			Document doc = Jsoup.parse(html);
			Elements as = doc.select("a[href~=javascript:mandarin.+]");
			for (Element a : as) {
				pinyin.add(a.text().trim());
			}
		} catch (Exception e) {
			System.out.println("Error: " + unichar);
			e.printStackTrace();
		}
		return pinyin;
	}

	class Pinyin {
		String c;
		List<String> pinyin;

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

}
