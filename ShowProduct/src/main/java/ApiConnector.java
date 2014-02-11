import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;

import org.json.JSONObject;

/**
 * Diese Klasse regelt uns den Zugriff auf die Cloud bzw deren Schnittstelle durch HTTPS GETs und POSTs
 * @author Gordon Bosch
 *
 */
public class ApiConnector {
	// Trustmanager sorgt dafür, dass wir kein Zertifikat brauchen werden wie
	// sonst bei SSL-verschlüsselungen
	static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
	} };

	private static void setupConnection() {
		//im Folgenden werden wir unsere sehr schwache SecurityPolicy von oben auf unsere Httpsconnection an
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * fetchData holt uns die benötigten Daten per Https-GET-Requests vom Server
	 * @param url stellt die aktuelle URL für die jeweils gesuchten Infos dar
	 * @return Stringbuffer response enthält alles in JSONForm was unter der URL zu finden war
	 * @throws IOException
	 */
	public static StringBuffer fetchData(String url) throws IOException {
		URL obj = new URL(url);
		URLConnection con;
		if(Main.KoronaApiUrl.contains("https"))
		{
			setupConnection();
			con = (HttpsURLConnection) obj.openConnection(); //öffnet die Connection zum Server (Https)
		} else {
			con = (HttpURLConnection) obj.openConnection(); //öffnet die Connection zum Server
		}		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); //kopiert die Daten in einen Buffer		
		String inputLine;
		StringBuffer response = new StringBuffer();
		//die folgende Schleife liest den Buffered Reader den wir oben gefüllt haben line für line in einen Stringbuffer
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}
	/**
	 * postData sendet ein JSON-Objekt per POST zurück an die CLOUD
	 * @param url des Servers
	 * @param obj das JSON-Objekt
	 * @throws IOException
	 */
	public static void postData(String url, JSONObject obj) throws IOException {
		URL posturl = new URL(url);
		HttpURLConnection con;
		if (Main.KoronaApiUrl.contains("https"))
		{
			setupConnection();
			con = (HttpsURLConnection)posturl.openConnection(); // öffnet die Connection zum Server (Https)
		}
		else
		{
			con = (HttpURLConnection)posturl.openConnection(); // öffnet die Connection zum Server
		}	
		JSONObject.testValidity(obj); //testet ob valides JSON
		con.setRequestMethod("POST"); //setzt PostMethode
		con.setDoOutput(true);        //ermöglicht Transferinput
		con.setConnectTimeout(10000);  
        con.setReadTimeout(10000); 
	    con.setUseCaches(false); 
	    con.setRequestProperty("Content-Type","application/json"); //setzt den type auf json
		con.connect(); 
		
		OutputStream out = con.getOutputStream();
	    try {
	      OutputStreamWriter wr = new OutputStreamWriter(out);
	      wr.write(obj.toString()); 
	      wr.flush();
	      wr.close();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    finally {
	      if (out != null)
	        out.close();
	    }
		if(con.getResponseCode()==200)
		{
			UI.throwPopup("Produktname erfolgreich geändert",JOptionPane.INFORMATION_MESSAGE);
		}
		con.disconnect(); //Disconnect
		Main.requestProduct(); //aktualisiere Produktanzeige
	}
}
