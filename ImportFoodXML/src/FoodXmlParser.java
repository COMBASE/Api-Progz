import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import link.CloudLink;

import org.codehaus.jettison.json.JSONException;

import domain.Assortment;
import domain.CommodityGroup;
import domain.DataType;
import domain.EconomicZone;
import domain.Pricelist;
import domain.Product;
import domain.ProductText_Type;
import domain.Product_Code;
import domain.Product_Text;
import domain.Rate;
import domain.Sector;
import domain.Tax;
import foodxml.Allergenic;
import foodxml.Article;
import foodxml.Catalog;
import foodxml.Foodxml;
import foodxml.Group;
import foodxml.Item;

/**
 * Copyright 2013 COMBASE AG
 */

public class FoodXmlParser
{
	public static List<Article> articles;
	public static List<Product> articles_final;
	public static Map<String, CommodityGroup> commgroups = new HashMap<String, CommodityGroup>();
	public static Map<Float, Pricelist> priceLists = new HashMap<Float, Pricelist>();
	public static List<Sector> sectorlist = new ArrayList<Sector>();
	private static Assortment assortment = new Assortment.Builder("Bäck2Office").build();
	private static EconomicZone ecozone = new EconomicZone("Deutschland");
	private static final SimpleDateFormat inputDf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

	public static void parse(String filePath) throws JAXBException, JSONException, IOException, ParseException
	{
		articles = new ArrayList<Article>();
		articles_final = new ArrayList<Product>();
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		Scanner scanner = new Scanner(fis, "UTF-8");
		String xml = scanner.useDelimiter("\\A").next().replaceAll("<br>", "");
		scanner.close();
		StringBuffer xmlBuf = new StringBuffer(xml);
		final JAXBContext context = JAXBContext.newInstance(Foodxml.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		final Foodxml fx = (Foodxml)unmarshaller.unmarshal(new StreamSource(new StringReader(
			xmlBuf.toString())));
		collectArticles(fx.getCatalog());
		getSubHTTPData();
		articles.clear();
	}

	private static void getSubHTTPData() throws IOException, JAXBException, ParseException
	{
		for (Article prod : articles)
		{
			if (prod.getUrl() != null)
			{
				StringBuffer xml = XMLFetcher.fetchData(prod.getUrl());
				final JAXBContext context = JAXBContext.newInstance(Foodxml.class);
				final Unmarshaller unmarshaller = context.createUnmarshaller();
				final Foodxml fx = (Foodxml)unmarshaller.unmarshal(new StreamSource(
					new StringReader(xml.toString().replaceAll("<br>", ""))));
				prod = fx.getCatalog().getArticles().get(0);
			}
			articles_final.add(transform(prod));
		}

	}

	private static Product transform(Article elem) throws ParseException
	{
		Product prod = new Product.Builder(elem.getArticle_names().getNames().gettextbylang("DEU").getText()).activeAssortment(
			elem.isAssortment())
			.activeAssortmentFrom(((elem.getAvailable_from() != null) ? inputDf.parse(elem.getAvailable_from()) : new Date()))
			.discountable(!elem.isNo_discount())
			.commodityGroup(commgroups.get(elem.getGroup_unique_idref()))
			.assortment(assortment)
			.build();
		
		if(elem.getEan() != null)
		{
			List<Product_Code> codes = new ArrayList<Product_Code>();
			codes.add(new Product_Code(elem.getEan(), BigDecimal.ONE));
			prod.setCodes(codes);
		}
			

		if (elem.getPrices() != null && elem.getPrices().getPriceList() != null)
		{
			List<domain.Price> priceitems = new ArrayList<domain.Price>();
			int sec1 = 0;
			int sec2 = 0;
			for (foodxml.Price p : elem.getPrices().getPriceList())
			{
				if (!priceLists.containsKey(p.getVat()))
				{
					String currencyUUID = getCurrency();
					if(currencyUUID.equals(null))
						return null;
					Pricelist list = new Pricelist.Builder(String.valueOf(p.getVat()),
						currencyUUID).build();
					priceLists.put(p.getVat(), list);
					Rate rate = new Rate(new BigDecimal(String.valueOf(p.getVat() * 100)),
						new Date());
					Tax tax = new Tax.Builder(String.valueOf(p.getVat()), ecozone).rateList(rate)
						.build();
					Sector sec = new Sector.Builder(String.valueOf(p.getVat())).taxlist(tax)
						.build();
					sectorlist.add(sec);
				}
				if (String.valueOf(p.getVat()).equals(sectorlist.get(0).getName()))
					sec1++;
				if (sectorlist.toArray().length > 1 &&
					String.valueOf(p.getVat()).equals(sectorlist.get(1).getName()))
					sec2++;

				Date date = null;
				try
				{
					date = inputDf.parse(p.getValid_from());
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				domain.Price item = new domain.Price(priceLists.get(p.getVat()), date,
					new BigDecimal(String.valueOf(p.getItem().getValue())));
				priceitems.add(item);
			}
			if (sec1 >= sec2)
			{
				if (!sectorlist.isEmpty())
					prod.setSector(sectorlist.get(0));
				if (sectorlist.toArray().length > 1 && sec2 > 0)
					prod.setAltsector(sectorlist.get(1));
			}
			else
			{
				if (sectorlist.toArray().length > 1)
					prod.setSector(sectorlist.get(1));
				if (!sectorlist.isEmpty() && sec1 > 0)
					prod.setAltsector(sectorlist.get(0));
			}
			prod.setPrices(priceitems);
		}
		List<Product_Text> texts = new ArrayList<Product_Text>();
		texts.add(new Product_Text(elem.getArticle_names()
			.getNames()
			.gettextbylang("DEU")
			.getText(), ProductText_Type.description));
		if (elem.getNutritional() != null && elem.getNutritional().getItemList() != null)
		{
			String nuts = "";
			for (Item item : elem.getNutritional().getItemList())
			{
				nuts = nuts + item.getText_text().gettextbylang("DEU").getText() + "=" +
					item.getValue_100g() + item.getUnit() + " ";
			}
			texts.add(new Product_Text(nuts, ProductText_Type.nutritionals));
		}
		if (elem.getIngredients() != null && elem.getIngredients().getItemList() != null)
		{
			String ings = "";
			for (Item item : elem.getIngredients().getItemList())
			{
				ings = ings + item.getMaterial_name().getMaterialbylang("DEU").getText() + " ";
			}
			texts.add(new Product_Text(ings, ProductText_Type.ingredients));
		}
		if (elem.getAllergenics() != null && elem.getAllergenics().getAllergenic() != null)
		{
			String allergs = "";
			for (Allergenic allg : elem.getAllergenics().getAllergenic())
			{
				if (allg.getItemList() != null)
				{
					for (Item item : allg.getItemList())
					{
						allergs = allergs + item.getText_text().gettextbylang("DEU").getText() +
							" ";
					}
				}
			}
			texts.add(new Product_Text(allergs, ProductText_Type.allergens));
		}
		prod.setTexts(texts);
		return prod;
	}

	private static void collectArticles(Catalog cog)
	{
		if (cog.getArticles() != null)
		{
			for (Article elem : cog.getArticles())
			{
				articles.add(elem);
			}
		}
		if (cog.getGroups() != null)
		{
			for (Group group : cog.getGroups())
			{
				CommodityGroup grp = new CommodityGroup.Builder(group.getDetails()
					.getNames()
					.gettextbylang("DEU")
					.getText()).build();
				commgroups.put(group.getUnique_id(), grp);
				collectArticlesfromGroup(group, grp);
			}
		}
	}

	private static void collectArticlesfromGroup(Group group, CommodityGroup parent)
	{
		if (group.getArticles() != null)
		{
			for (Article elem : group.getArticles())
			{
				articles.add(elem);
			}
		}
		if (group.getGroups() != null)
		{
			for (Group subgroup : group.getGroups())
			{
				CommodityGroup grp = new CommodityGroup.Builder(subgroup.getDetails()
					.getNames()
					.gettextbylang("DEU")
					.getText()).parent(parent).build();
				parent.setHasChildren(true);
				commgroups.put(subgroup.getUnique_id(), grp);
				collectArticlesfromGroup(subgroup, grp);
			}
		}
	}

	private static String getCurrency()
	{
		String s = new String();
		try
		{
			 s = CloudLink.getUUID(DataType.currency, "Euro");
		}
		catch (IOException ex)
		{
			UserInterface.throwPopup(
				"Entweder stimmen die Einstellungen nicht oder es konnte keine Verbindung hergestellt werden. Überprüfen sie bitte beides.",
				JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return s;
	}

}
