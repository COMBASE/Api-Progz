package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Article
{

	@XmlAttribute(name="id")
	private String id;
	
	@XmlAttribute(name="url")
	private String url;
	
	private String group_idref;
	
	private String group_unique_idref;
	
	private int plu_no;
	
	private boolean assortment;
	
	private boolean no_discount;
	
	private int unit_no;
	
	private String ean;
	
	private Text status;
	
	@XmlAttribute(name="available_from")
	private String available_from;
	
	@XmlElement(name="prices")
	private Prices prices;
	
	@XmlElement(name="article_texts")
	private List<Article_text> article_texts;
	
	@XmlElement(name="nutritional")
	private Nutritional nutritional;
	
	@XmlElement(name="allergenics")
	private Allergenics allergenics;
	
	@XmlElement(name="matchcode")
	private Matchcode matchcode;

	@XmlElement(name="article_no")
	private Text article_no;

	@XmlElement(name="article_names")
	private Article_names article_names;

	@XmlElement(name="manufacturer")
	private Manufacturer manufacturer;

	@XmlElement(name="ingredients")
	private Ingredients ingredients;

	@XmlElement(name="charges")
	private Charges charges;

	public String getId()
	{

		return this.id;

	}

	public void setId(String arg)
	{

		this.id = arg;

	}

	public Nutritional getNutritional()
	{

		return this.nutritional;

	}

	public void setNutritional(Nutritional arg)
	{

		this.nutritional = arg;

	}

	public Allergenics getAllergenics()
	{

		return this.allergenics;

	}

	public void setAllergenics(Allergenics arg)
	{

		this.allergenics = arg;

	}

	public Matchcode getMatchcode()
	{

		return this.matchcode;

	}

	public void setMatchcode(Matchcode arg)
	{

		this.matchcode = arg;

	}

	public Text getArticle_no()
	{

		return this.article_no;

	}

	public void setArticle_no(Text arg)
	{

		this.article_no = arg;

	}

	public Article_names getArticle_names()
	{

		return this.article_names;

	}

	public void setArticle_names(Article_names arg)
	{

		this.article_names = arg;

	}

	public Manufacturer getManufacturer()
	{

		return this.manufacturer;

	}

	public void setManufacturer(Manufacturer arg)
	{

		this.manufacturer = arg;

	}

	public Ingredients getIngredients()
	{

		return this.ingredients;

	}

	public void setIngredients(Ingredients arg)
	{

		this.ingredients = arg;

	}

	public Charges getCharges()
	{

		return this.charges;

	}

	public void setCharges(Charges arg)
	{

		this.charges = arg;
	}

	public String getGroup_idref()
	{
		return group_idref;
	}

	public void setGroup_idref(String group_idref)
	{
		this.group_idref = group_idref;
	}

	public String getGroup_unique_idref()
	{
		return group_unique_idref;
	}

	public void setGroup_unique_idref(String group_unique_idref)
	{
		this.group_unique_idref = group_unique_idref;
	}

	public int getPlu_no()
	{
		return plu_no;
	}

	public void setPlu_no(int plu_no)
	{
		this.plu_no = plu_no;
	}

	public String getEan()
	{
		return ean;
	}

	public void setEan(String ean)
	{
		this.ean = ean;
	}

	public Text getStatus()
	{
		return status;
	}

	public void setStatus(Text status)
	{
		this.status = status;
	}

	public boolean isAssortment()
	{
		return assortment;
	}

	public void setAssortment(boolean assortment)
	{
		this.assortment = assortment;
	}

	public boolean isNo_discount()
	{
		return no_discount;
	}

	public void setNo_discount(boolean no_discount)
	{
		this.no_discount = no_discount;
	}

	public int getUnit_no()
	{
		return unit_no;
	}

	public void setUnit_no(int unit_no)
	{
		this.unit_no = unit_no;
	}

	public List<Article_text> getArticle_texts()
	{
		return article_texts;
	}

	public void setArticle_texts(List<Article_text> article_texts)
	{
		this.article_texts = article_texts;
	}

	public Prices getPrices()
	{
		return prices;
	}

	public void setPrices(Prices prices)
	{
		this.prices = prices;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getAvailable_from()
	{
		return available_from;
	}

	public void setAvailable_from(String available_from)
	{
		this.available_from = available_from;
	}

}
