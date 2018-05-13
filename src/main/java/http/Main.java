package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		String url = "http://nguyendu.com.free.fr/hanviet/ajax.php";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("query", "耂");
		NameValuePair nv2 = new BasicNameValuePair("methode", "normal");
		nvps.add(nv1);
		nvps.add(nv2);
		URI uri = new URIBuilder(request.getURI()).addParameters(nvps).build();
		request.setURI(uri);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
	}

}
