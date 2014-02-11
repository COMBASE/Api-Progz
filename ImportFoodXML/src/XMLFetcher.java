import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class XMLFetcher
{
	private final static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
	{
		public X509Certificate[] getAcceptedIssuers()
		{
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType)
		{
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType)
		{
		}
	} };

	private static void setupConnection()
	{
		try
		{
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static StringBuffer fetchData(String url) throws IOException
	{
		URL obj;
		obj = new URL(url);
		URLConnection con;
		if (url.contains("https"))
		{
			setupConnection();
			con = (HttpsURLConnection)obj.openConnection();
		}
		else
		{
			con = (HttpURLConnection)obj.openConnection();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		in.close();
		return response;
	}
}
