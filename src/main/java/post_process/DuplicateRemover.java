package post_process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class DuplicateRemover {
	public static void main(String[] args) throws IOException {
		DuplicateRemover dr = new DuplicateRemover();
		dr.removeDupWordMeaning();
	}

	private void removeDupWordMeaning() throws IOException {
		List<String> clines = FileUtils.readLines(new File("post_process/word_meanings.txt"), "UTF-8");
		List<String> newLines = new ArrayList<String>();

		Set<String> css = new HashSet<String>();
		for (String line : clines) {
			String han = StringUtils.substringBetween(line, "\"unichar\":\"", "\",\"meanings\"");
			// check id
			if (!css.contains(han)) {
				newLines.add(line);
				css.add(han);
				// System.out.println("dup: " + han);
			} else {
				System.out.println("dup: " + han);
			}
		}
		if (clines.size() != css.size()) {
			System.out.println(clines.size() - css.size() + " dup!");
			FileUtils.writeLines(new File("post_process/word_meanings.txt"), "UTF-8", newLines);
		} else {
			System.out.println("no dup!");
		}
	}

	private void removeDupWord() throws IOException {
		List<String> clines = FileUtils.readLines(new File("cwcollector/words_bk.txt"), "UTF-8");
		List<String> newLines = new ArrayList<String>();

		Set<String> css = new HashSet<String>();
		for (String line : clines) {
			String han = StringUtils.substringBetween(line, "\"id\":\"", "\",\"han\"");
			// check id
			if (!css.contains(han)) {
				newLines.add(line);
				css.add(han);
				// System.out.println("dup: " + han);
			} else {
				System.out.println("dup: " + han);
			}
		}
		if (clines.size() != css.size()) {
			System.out.println(clines.size() - css.size() + " dup!");
			FileUtils.writeLines(new File("cwcollector/compat_words_bk.txt"), "UTF-8", newLines);
		} else {
			System.out.println("no dup!");
		}
	}

	private void removeDupChar() throws IOException {
		List<String> clines = FileUtils.readLines(new File("cwcollector/chars_bk.txt"), "UTF-8");
		List<String> newLines = new ArrayList<String>();

		Set<String> css = new HashSet<String>();
		for (String line : clines) {
			String han = StringUtils.substringBetween(line, "\"id\":\"", "\",\"han\"");
			// check id
			if (!css.contains(han)) {
				newLines.add(line);
				css.add(han);
				// System.out.println("dup: " + han);
			} else {
				System.out.println("dup: " + han);
			}
		}
		if (clines.size() != css.size()) {
			System.out.println(clines.size() - css.size() + " dup!");
			FileUtils.writeLines(new File("cwcollector/compat_chars_bk.txt"), "UTF-8", newLines);
		} else {
			System.out.println("no dup!");
		}
	}
}
