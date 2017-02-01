package br.com.controle.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrafegoUtil {
	
    public static final String BASE_URL = "http://swapi.co/api";
    public static final String APPLICATION_NAME = "SWAPI-JAVA";
    public static final String URL_VEICULO = "http://swapi.co/api/vehicles/?format=json";
    public static final String URL_PESSOAS = "http://swapi.co/api/people/?format=json";
    public static final String URL_PLANETAS = "http://swapi.co/api/planets/?format=json";
	
	public static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection request = (HttpURLConnection) url
					.openConnection();
			request.setRequestProperty("User-Agent", "swapi-Java-"
					+ APPLICATION_NAME);
			request.connect();
			
			reader = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
