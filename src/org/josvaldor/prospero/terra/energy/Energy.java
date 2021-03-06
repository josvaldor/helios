package org.josvaldor.prospero.terra.energy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.josvaldor.prospero.helios.Coordinate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Energy {
	public List<Coordinate> read(String time) {
		List<Coordinate> list = null;
		try {

			URL url = new URL("http://localhost:8080/sun?time=" + time);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			Gson gson = new Gson();
			while ((output = br.readLine()) != null) {
				list = gson.fromJson(output, new TypeToken<List<Coordinate>>() {
				}.getType());
				// System.out.println(output);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return list;
	} 
}
