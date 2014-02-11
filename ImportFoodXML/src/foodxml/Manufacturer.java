package foodxml;

public class Manufacturer
{

	private String text;

	private Foodxml_company foodxml_company;

	private Text article_no;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public Foodxml_company getFoodxml_company()
	{

		return this.foodxml_company;

	}

	public void setFoodxml_company(Foodxml_company arg)
	{

		this.foodxml_company = arg;

	}

	public Text getArticle_no()
	{

		return this.article_no;

	}

	public void setArticle_no(Text arg)
	{

		this.article_no = arg;

	}

}
