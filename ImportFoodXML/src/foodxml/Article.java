package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Article
{

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "url")
	private String url;

	private String group_idref;

	private String group_unique_idref;

	private int plu_no;

	private boolean assortment;

	private boolean no_discount;

	private int unit_no;

	private String ean;

	private Text status;

	@XmlElement(name = "available_from")
	private String available_from;

	@XmlElement(name = "prices")
	private Prices prices;

	@XmlElement(name = "article_texts")
	private List<Article_text> article_texts;

	@XmlElement(name = "nutritional")
	private Nutritional nutritional;

	@XmlElement(name = "allergenics")
	private Allergenics allergenics;

	@XmlElement(name = "matchcode")
	private Matchcode matchcode;

	@XmlElement(name = "article_no")
	private Text article_no;

	@XmlElement(name = "article_names")
	private Article_names article_names;

	@XmlElement(name = "manufacturer")
	private Manufacturer manufacturer;

	@XmlElement(name = "ingredients")
	private Ingredients ingredients;

	@XmlElement(name = "charges")
	private Charges charges;

	public Allergenics getAllergenics()
	{

		return this.allergenics;

	}

	public Article_names getArticle_names()
	{

		return this.article_names;

	}

	public Text getArticle_no()
	{

		return this.article_no;

	}

	public List<Article_text> getArticle_texts()
	{
		return this.article_texts;
	}

	public String getAvailable_from()
	{
		return this.available_from;
	}

	public Charges getCharges()
	{

		return this.charges;

	}

	public String getEan()
	{
		return this.ean;
	}

	public String getGroup_idref()
	{
		return this.group_idref;
	}

	public String getGroup_unique_idref()
	{
		return this.group_unique_idref;
	}

	public String getId()
	{

		return this.id;

	}

	public Ingredients getIngredients()
	{

		return this.ingredients;

	}

	public Manufacturer getManufacturer()
	{

		return this.manufacturer;

	}

	public Matchcode getMatchcode()
	{

		return this.matchcode;

	}

	public Nutritional getNutritional()
	{

		return this.nutritional;

	}

	public int getPlu_no()
	{
		return this.plu_no;
	}

	public Prices getPrices()
	{
		return this.prices;
	}

	public Text getStatus()
	{
		return this.status;
	}

	public int getUnit_no()
	{
		return this.unit_no;
	}

	public String getUrl()
	{
		return this.url;
	}

	public boolean isAssortment()
	{
		return this.assortment;
	}

	public boolean isNo_discount()
	{
		return this.no_discount;
	}

	public void setAllergenics(final Allergenics arg)
	{

		this.allergenics = arg;

	}

	public void setArticle_names(final Article_names arg)
	{

		this.article_names = arg;

	}

	public void setArticle_no(final Text arg)
	{

		this.article_no = arg;

	}

	public void setArticle_texts(final List<Article_text> article_texts)
	{
		this.article_texts = article_texts;
	}

	public void setAssortment(final boolean assortment)
	{
		this.assortment = assortment;
	}

	public void setAvailable_from(final String available_from)
	{
		this.available_from = available_from;
	}

	public void setCharges(final Charges arg)
	{

		this.charges = arg;
	}

	public void setEan(final String ean)
	{
		this.ean = ean;
	}

	public void setGroup_idref(final String group_idref)
	{
		this.group_idref = group_idref;
	}

	public void setGroup_unique_idref(final String group_unique_idref)
	{
		this.group_unique_idref = group_unique_idref;
	}

	public void setId(final String arg)
	{

		this.id = arg;

	}

	public void setIngredients(final Ingredients arg)
	{

		this.ingredients = arg;

	}

	public void setManufacturer(final Manufacturer arg)
	{

		this.manufacturer = arg;

	}

	public void setMatchcode(final Matchcode arg)
	{

		this.matchcode = arg;

	}

	public void setNo_discount(final boolean no_discount)
	{
		this.no_discount = no_discount;
	}

	public void setNutritional(final Nutritional arg)
	{

		this.nutritional = arg;

	}

	public void setPlu_no(final int plu_no)
	{
		this.plu_no = plu_no;
	}

	public void setPrices(final Prices prices)
	{
		this.prices = prices;
	}

	public void setStatus(final Text status)
	{
		this.status = status;
	}

	public void setUnit_no(final int unit_no)
	{
		this.unit_no = unit_no;
	}

	public void setUrl(final String url)
	{
		this.url = url;
	}

}
