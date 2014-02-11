import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import link.CloudLink;

import org.codehaus.jettison.json.JSONException;

import domain.Product;


public class Main
{
	// AppID, AppSecret und Apikey dienen zur Generierung des Tokens,
	// welches uns wiederum Data-Access auf die Cloud-Daten gewährt
	public static String Token = new String();
	public static String KoronaApiUrl;
	private final static File propertiesFile = new File("./api_acess.properties");
	private static UserInterface window;
	public static Properties properties = new Properties();
	@SuppressWarnings("unused")
	private static CloudLink link;

	public static void main(String[] args) throws IOException, JSONException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // Screensize
// bestimmen
					window = new UserInterface(); // MainWindow erstellen
					int x = (int)((dimension.getWidth() - window.frmImportFromFoodxml.getWidth()) / 2);
					int y = (int)((dimension.getHeight() - window.frmImportFromFoodxml.getHeight()) / 2);
					window.frmImportFromFoodxml.setVisible(true); // Fenster sichtbar machen
					window.frmImportFromFoodxml.setLocation(x, y); // Fenster zentrieren auf dem
// Bildschirm
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		/**
		 * Existiert noch keine Token zur Authentifizierung generiere eins, ansonsten lies das
		 * bestehende aus der lokalen token.txt
		 */
		if (!propertiesFile.exists())
		{
			setupProperties();
		}
		else
		{
			FileReader fr = new FileReader(propertiesFile);
			BufferedReader textReader = new BufferedReader(fr);
			properties.load(textReader);
			textReader.close();
			Token = properties.getProperty("token");
			KoronaApiUrl = properties.getProperty("url") + "/api/v1";

		}
		if(KoronaApiUrl!=null && Token!=null)
			link = new CloudLink(KoronaApiUrl, Token);
	}

	public static void export()
	{
		if(Token==null || Token.length()<1)
		{
			UserInterface.throwPopup("Bitte geben sie in den Einstellungen einen gültigen Token an", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		@SuppressWarnings("unchecked")
		final List<Product> selectedlist = (List<Product>)window.getList().getSelectedValuesList();

		final ProgressMonitor progressMonitor = new ProgressMonitor(window.frmImportFromFoodxml,
			"", "", 0, selectedlist.size() - 1);
		progressMonitor.setProgress(0);
		final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
		{
			int progress = 0;

			@Override
			protected Void doInBackground()
			{
				for (Product prod : selectedlist)
				{
					try
					{
						prod.post();
					}
					catch (IOException e)
					{
						UserInterface.throwPopup(
							"Entweder stimmen die Einstellungen nicht oder es konnte keine Verbindung hergestellt werden. Überprüfen sie bitte beides.",
							JOptionPane.ERROR_MESSAGE);
						this.cancel(true);
						progressMonitor.close();
					}
					progressMonitor.setProgress(progress);
					progress++;
				}
				return null;
			}

			@Override
			public void done()
			{
				progressMonitor.close();
				UserInterface.throwPopup(
					selectedlist.size() + " Artikel wurde" +
						((selectedlist.size() > 1) ? "n" : "") + " exportiert",
					JOptionPane.INFORMATION_MESSAGE);
			}
		};
		worker.addPropertyChangeListener(new PropertyChangeListener()
		{

			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if ("progress" == evt.getPropertyName())
				{
					int progress = (Integer)evt.getNewValue();
					progressMonitor.setProgress(progress);
					if (progressMonitor.isCanceled() || worker.isDone())
					{
						Toolkit.getDefaultToolkit().beep();
						if (progressMonitor.isCanceled())
						{
							worker.cancel(true);
						}
					}
				}

			}
		});
		worker.execute();
	}

	public static boolean testSetup()
	{
		try
		{
			XMLFetcher.fetchData(KoronaApiUrl+"/"+Token+"/products/updates/0");
			return true;
		}
		catch (IOException e)
		{
			UserInterface.throwPopup(
				"Entweder stimmen die Einstellungen nicht oder es konnte keine Verbindung hergestellt werden. Überprüfen sie bitte beides.",
				JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	private static void setupProperties()
	{
		FileWriter writer;
		try
		{
			writer = new FileWriter(propertiesFile);
			properties.setProperty("url", "https://www.koronacloud.com");
			properties.store(writer, "");
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			UserInterface.throwPopup("Kann Konfigurationsdatei api_acess.properties nicht bearbeiten", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public static boolean saveProps(String token, String url_api)
	{
		try
		{
			URL url = new URL(url_api);
			URLConnection con;
			if (url_api.contains("https"))
			{
				setupConnection();
				con = (HttpsURLConnection)url.openConnection();
			}
			else
			{
				con = (HttpURLConnection)url.openConnection();
			}
			con.connect();
		}
		catch (MalformedURLException e)
		{
			UserInterface.throwPopup("Keine gültige URL", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch (IOException e)
		{
			UserInterface.throwPopup("Keine Verbindung möglich", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		properties.setProperty("token", token);
		properties.setProperty("url", url_api);
		Token = properties.getProperty("token");
		KoronaApiUrl = properties.getProperty("url") + "/api/v1";
		try
		{
			FileWriter writer = new FileWriter(propertiesFile);
			properties.store(writer, "");
			writer.flush();
			writer.close();
		}catch (IOException e)
		{
			UserInterface.throwPopup("Kann Konfigurationsdatei api_acess.properties nicht bearbeiten", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		link = new CloudLink(KoronaApiUrl, Token);
		return true;
	}
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
}
