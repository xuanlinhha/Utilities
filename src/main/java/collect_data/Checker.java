package collect_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class Checker {

	public static void main(String[] args) throws IOException {
		Checker c = new Checker();
		c.check3();
	}

	private void check3() throws IOException {
		// chars
		List<String> clines = FileUtils.readLines(new File("cwcollector/words_bk.txt"), "UTF-8");
		Set<String> css = new HashSet<String>();
		for (String line : clines) {
			String han = StringUtils.substringBetween(line, "\"id\":\"", "\",\"han\"");
			if (css.contains(han)) {
				System.out.println("dup: " + han);
			}
			css.add(han);
		}
		System.out.println(css.size());

		// char meaning
		List<String> plines = FileUtils.readLines(new File("post_process/word_meanings.txt"), "UTF-8");
		Set<String> pss = new HashSet<String>();
		for (String line : plines) {
			String han = StringUtils.substringBetween(line, "\"unichar\":\"", "\",\"meanings\"");
			pss.add(han);
		}
		System.out.println(pss.size());

		// check char meanings
		// FileUtils.writeLines(new File("cwcollector/missing_pinyin.txt"), "UTF-8",
		// missing);
	}

	private void check2() throws IOException {
		// chars
		List<String> clines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		Set<String> css = new HashSet<String>();
		for (String line : clines) {
			String han = StringUtils.substringBetween(line, "\"han\":\"", "\",\"viet\"");
			if (css.contains(han)) {
				System.out.println("dup: " + han);
			}
			css.add(han);
		}
		System.out.println(css.size());

		// pinyin
		List<String> plines = FileUtils.readLines(new File("cwcollector/pinyin.txt"), "UTF-8");
		Set<String> pss = new HashSet<String>();
		for (String line : plines) {
			String han = StringUtils.substringBetween(line, "\"c\":\"", "\",\"pinyin\"");
			// System.out.println(han);
			pss.add(han);
		}
		System.out.println(pss.size());

		// check
		List<String> missing = new ArrayList<String>();
		for (String s : css) {
			if (!pss.contains(s)) {
				missing.add(s);

			}
		}
		FileUtils.writeLines(new File("cwcollector/missing_pinyin.txt"), "UTF-8", missing);
	}

	private void check1() throws IOException {
		List<String> lines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		Set<String> ss = new HashSet<String>();
		for (String line : lines) {
			String han = StringUtils.substringBetween(line, "\"han\":\"", "\",\"viet\"");
			ss.add(han);
		}
		List<String> nores = FileUtils.readLines(new File("cwcollector/noresult_bk.txt"), "UTF-8");
		ss.addAll(nores);

		List<String> origins = FileUtils.readLines(new File("cwcollector/input.txt"), "UTF-8");
		for (int i = 0; i < origins.size(); i++) {
			if (!ss.contains(origins.get(i))) {
				System.out.println("not found: " + i + ": " + origins.get(i));
			}
		}
	}

}
