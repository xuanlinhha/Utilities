package post_process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class SVGProcessor {
	public static void main(String[] args) throws IOException {
		System.out.println("Start ...");
		SVGProcessor sp = new SVGProcessor();
		sp.processSvg();
		System.out.println("Finished!");
	}

	private void processSvg() {
		try {
			// char set
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

			Gson g = new Gson();
			BufferedReader bufferedReader = new BufferedReader(new FileReader("cwcollector/svg.txt"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Data d = g.fromJson(line, Data.class);
				SVG svg = new SVG();
				Character c = (char) Integer.parseInt(d.getCodePoint());
				if (css.contains(c.toString())) {
					// System.out.println("han=" + c.toString());
					svg.setHan(c.toString());
					svg.setSvg(d.getSvgString());
					FileUtils.write(new File("post_process/svg.txt"), g.toJson(svg) + "\n", "UTF-8", true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class Data {
		String codePoint;
		String svgString;

		public String getCodePoint() {
			return codePoint;
		}

		public void setCodePoint(String codePoint) {
			this.codePoint = codePoint;
		}

		public String getSvgString() {
			return svgString;
		}

		public void setSvgString(String svgString) {
			this.svgString = svgString;
		}

	}

	class SVG {
		String han;
		String svg;

		public String getHan() {
			return han;
		}

		public void setHan(String han) {
			this.han = han;
		}

		public String getSvg() {
			return svg;
		}

		public void setSvg(String svg) {
			this.svg = svg;
		}
	}
}
