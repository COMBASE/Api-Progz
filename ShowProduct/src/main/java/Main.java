import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Dieses Programm liest über die Koronacloud-Api Produkte vom Server aus Dependendencies: AWT,
 * SWING, ORG.JSON, NET.SSL org.json -> https://github.com/douglascrockford/JSON-java hier separat
 * eingebunden
 * 
 * @author Gordon Bosch
 * 
 */
public class Main
{
	// AppID, AppSecret und Apikey dienen zur Generierung des Tokens,
	// welches uns wiederum Data-Access auf die Cloud-Daten gewährt
	private final static String AppID = new String("ccd293da-130a-4710-9f42-753dca3bd56e");
	private final static String AppSecret = new String("frjy");
	private final static String ApiKey = new String("dde2052f-849b-40ab-9fd0-90eb3b6427e1");
	private static String Token = new String();
	public final static String KoronaApiUrl = new String("http://localhost:8080/api/"); //unbedingt mit Slash schliessen
	private final static String KoronaApiVersion = new String("v1");
	private final static File tokenFile = new File("./token.txt");

	/**
	 * Die Main-Methode: da die Swing-GUI etwas träge sein kann, stecken wir alles in ein Runnable,
	 * dass per invokeLater erst gestartet wird, wenn alles geladen ist jeweils dann wird geprüft ob
	 * das Token schon generiert und falls ja lädt er ihn falls nicht kümmert es sich um das
	 * Generieren
	 * 
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws IOException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // Screensize bestimmen
					UI window = new UI(); // MainWindow erstellen
					int x = (int)((dimension.getWidth() - window.getFrmShowProduct().getWidth()) / 2);
					int y = (int)((dimension.getHeight() - window.getFrmShowProduct().getHeight()) / 2);
					window.getFrmShowProduct().setVisible(true); // Fenster sichtbar machen
					window.getFrmShowProduct().setLocation(x, y); // Fenster zentrieren auf dem Bildschirm
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
		if (!tokenFile.exists())
		{
			generateToken();
		}
		else
		{
			FileReader fr = new FileReader(tokenFile);
			BufferedReader textReader = new BufferedReader(fr);
			Token = textReader.readLine();
			textReader.close();
		}
	}

	/**
	 * formatOutput sorgt für die Formatierung der erstellten Produktdaten, die dann fertig an das
	 * Textfeld zurückgegeben werden können
	 * 
	 * @param s ist der String in JSON-Syntax, den wir für unsere Ausgabe durchparsen
	 * @return den formatierten Fensterinhalt als String
	 * @throws JSONException
	 * @throws IOException
	 */
	private static String formatOutput(JSONObject obj)
	{
		String ausgabe = "";
		JSONObject result = new JSONObject();
		try
		{
			result = obj.getJSONObject("result"); // result ist unser Referenz
													// JSON-Objekt. von da aus
													// erreichen wir alle Daten
			ausgabe = getStringOutputLine("Produktname", "name", result) +
				getStringOutputLine("Revision", "revision", result) +
				getStringOutputLine("Rabattfähig", "discountable", result) +
				getStringOutputLine("Preisänderbarkeit", "priceChangeable", result) +
				getStringOutputLine("Gelöscht", "deleted", result) +
				getStringOutputLine("Type", "@xsi.type", result) +
				getStringOutputLine("Warengruppe", "commodityGroup", result) +
				getStringOutputLine("Sektor", "sector", result);
			
			if (result.has("prices")) // nicht jedes Produkt besitzt einen Preis
			{							// und falls kann es ein einzelner Preis oder eine Preisliste sein
				Object price = result.get("prices");
				if(price instanceof JSONObject)
				{
					ausgabe = ausgabe + "Preis: " + ((JSONObject)price).get("value") +" €";
				}
				if(price instanceof JSONArray)
				{
					ausgabe = ausgabe + "Preis: "+ ((JSONObject)((JSONArray)price).get(0)).get("value") + " €";
				}
			}

		}
		catch (JSONException e)
		{
			UI.throwPopup("Kein Produkt unter dieser Nummer",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (IOException e)
		{
		}
		return ausgabe;
	}

	/**
	 * Diese Methode prüft ob die Referenz (zB.: "name" oder "Revision") gültig ist und falls ja
	 * generiert es eine Ausgabezeile daraus
	 * 
	 * @param titel Der Zeilenname
	 * @param referenz die Referenz im JSON-Objekt
	 * @param obj das zu untersuchende JSON-Objekt
	 * @return Ausgabe wie: Produktname: Coca Cola
	 * @throws JSONException
	 * @throws IOException
	 */
	private static String getStringOutputLine(String titel, String referenz, JSONObject obj)
		throws JSONException, IOException
	{
		if (obj.has(referenz))
		{
			if (!isUUID(obj.get(referenz).toString()))
			{
				return titel + ": " + obj.get(referenz) + "\n";
			}
			else
			{
				JSONObject childobj = fetchObject(referenz + "s", obj.get(referenz).toString());
				JSONObject result = childobj.getJSONObject("result");
				return getStringOutputLine(titel, "name", result);
			}
		}
		return null;
	}

	/**
	 * requestProduct prüft ob wir eine Eingabe im Textfeld haben, und ob die auch eine Zahl ist und
	 * erfragt die Daten lässt sie formatieren und setzt am Ende das Textfeld
	 * 
	 * @throws IOException
	 */
	public static void requestProduct() throws IOException
	{
		if (UI.getTextField().getText().length() > 0 &&
			UI.getTextField().getText().matches("[0-9]+"))
		{
			UI.getTextArea().setText(
				formatOutput(fetchObject("products", UI.getTextField().getText())));
//			System.out.println(fetchObject("products", UI.getTextField().getText()));
		}
		else
		{
			if (UI.getTextField().getText() == null)
				UI.throwPopup("Bitte geben sie eine Zahl im Feld Productnr an",JOptionPane.ERROR_MESSAGE);
			if (!UI.getTextField().getText().matches("[0-9]+"))
				UI.throwPopup("Die Eingabe darf nur eine Zahl sein",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Hier wird der Namechange des Produktes implementiert Das entsprechende Produkt ausgelesen,
	 * dessen Name ersetzt und an den Connector versandt
	 * 
	 * @throws IOException
	 */
	public static void postProduct() throws IOException
	{
		if (!UI.getTextArea().getText().isEmpty() && UI.getTextField_name().getText().length() > 0)
		{
			JSONObject postData = fetchObject("products", UI.getTextField().getText());
			JSONObject result = postData.getJSONObject("result");
			String name = result.get("name").toString();
			JSONObject new_product = new JSONObject(postData.toString().replace(name, UI.getTextField_name().getText()));
			ApiConnector.postData(KoronaApiUrl + KoronaApiVersion + "/" + Token + "/products/save/", new_product.getJSONObject("result"));
		}
		else
		{
			if (UI.getTextField_name().getText().length() == 0)
				UI.throwPopup("Sie müssen einen neuen Produktnamen eingaben",JOptionPane.ERROR_MESSAGE);
			if (UI.getTextArea().getText().isEmpty())
				UI.throwPopup("Sie müssen zuerst ein Produkt auswählen",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * fetchObject zieht das gesuchte Object anhand
	 * 
	 * @param objType dem ObjectType
	 * @param referenz und der Referenz, in dem Fall number oder id
	 * @return das gesuchte JSON Object
	 * @throws IOException
	 */
	public static JSONObject fetchObject(String objType, String referenz) throws IOException
	{
		String indicator; // dieser String weist zu ob wir unsere gesuchtes Object per number oder id referenzieren wollen
		if (isID(referenz))
		{
			indicator = "id";
		}
		else
		{
			indicator = "number";
		}
		String url = KoronaApiUrl + KoronaApiVersion + "/" + Token + "/" + objType + "/" +indicator + "/" + referenz;
		String obj = ApiConnector.fetchData(url).toString();
		return new JSONObject(obj);
	}

	/**
	 * Untersucht ob es sich bei dem String um eine UUID handelt
	 * @param s der zu untersuchende String
	 * @return true falls UUID
	 */
	private static boolean isUUID(String s)
	{
		if(s.length()>35)
		{
			if(s.charAt(8)=='-'&&s.charAt(13)=='-'&&s.charAt(18)=='-'&&s.charAt(23)=='-')
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * isID prüft einen String ob es sich um eine ID handelt oder Nummer
	 * @param s der Eingabestring
	 * @return true falls ID, false fals Nummer
	 */
	private static boolean isID(String s)
	{
		try
		{
			Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			return true;
		}
		return false;
	}

	/**
	 * diese Methode fordert das Token-Authentifizierungselement vom Cloudserver an und speichert es
	 * in die token.txt
	 * @throws IOException
	 */
	private static void generateToken() throws IOException
	{
		FileWriter writer = new FileWriter(tokenFile);
		String url = KoronaApiUrl + KoronaApiVersion + "/auth/" + AppID + "/" + AppSecret + "/" +ApiKey;
		Token = ApiConnector.fetchData(url).toString();
		writer.write(Token);
		writer.flush();
		writer.close();
	}
}
