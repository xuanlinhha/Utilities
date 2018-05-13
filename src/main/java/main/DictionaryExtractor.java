package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.CCDictionary;

public class DictionaryExtractor {
	public static void extract(String output) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		try {
			List<String> lines = FileUtils.readLines(new File("dictionary.txt"), "UTF-8");
			List<String> extractedLines = new ArrayList<String>();
			for (String line : lines) {
				extractedLines.add(gson.toJson(gson.fromJson(line, CCDictionary.class)));
			}
			FileUtils.writeLines(new File(output + File.separator + "extractedDictionary.txt"), extractedLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
