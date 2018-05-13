package collect_data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Http {


	public static String getCharDetail(String unichar) {
		String url = "http://nguyendu.com.free.fr/hanviet/hv_timchu.php";
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("unichar", unichar);
		nvs.add(nv1);
		return getHtml(url, nvs);
	}

	public static String getPhraseDetail(String wordid) {
		String url = "http://nguyendu.com.free.fr/hanviet/hv_timtukep.php";
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("wordid", wordid);
		nvs.add(nv1);
		return getHtml(url, nvs);
	}

	public static String getAjaxResponse(String han) {
		String url = "http://nguyendu.com.free.fr/hanviet/ajax.php";
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("query", han);
		NameValuePair nv2 = new BasicNameValuePair("methode", "normal");
		nvs.add(nv1);
		nvs.add(nv2);
		return getHtml(url, nvs);
	}

	private static String getHtml(String url, List<NameValuePair> nvs) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			URI uri = new URIBuilder(request.getURI()).addParameters(nvs).build();
			request.setURI(uri);
			HttpResponse response = client.execute(request);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
