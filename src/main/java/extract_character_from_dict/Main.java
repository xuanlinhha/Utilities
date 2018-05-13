package extract_character_from_dict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			Set<String> cps = new HashSet();
			File data = new File("extractedDictionary.txt");
			reader = new BufferedReader(new FileReader(data));
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String line = "";
			int i = 0;
			while ((line = reader.readLine()) != null) {
				CC cc = gson.fromJson(line, CC.class);
				// cc.setCodePoint(cc.getCharacter().codePointAt(0));
				cps.add(cc.getCharacter());
				i++;
			}
			FileUtils.writeLines(new File("cps.txt"), cps);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
