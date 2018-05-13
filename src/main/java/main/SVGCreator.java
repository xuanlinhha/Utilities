package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.CCGraphic;
import svg.SVGWriter;

public class SVGCreator {

	public static void create(String folder) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		try {
			List<String> lines = FileUtils.readLines(new File("graphics.txt"), "UTF-8");
			List<CCGraphic> ccgs = new ArrayList<CCGraphic>();
			for (String line : lines) {
				ccgs.add(gson.fromJson(line, CCGraphic.class));
			}
			for (CCGraphic ccg : ccgs) {
				SVGWriter.write(folder, ccg.getCharacter(), ccg.getStrokes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
