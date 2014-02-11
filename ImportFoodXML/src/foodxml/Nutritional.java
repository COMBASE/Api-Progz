package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Nutritional
{
	@XmlAttribute(name="base")
	private String base;
	
	@XmlAttribute(name="valid_from")
	private String valid_from;
	
	@XmlAttribute(name="valid_till")
	private String valid_till;
	
	@XmlAttribute(name="country")
	private String country;
	
	@XmlAttribute(name="base_weight")
	private String base_weight;
	
	@XmlElement(name="text_title")
	private Texts text_title;
	
	@XmlElement(name="text_text")
	private Texts text_text;

	private String text;

	@XmlElement(name="item")
	private List<Item> itemList;
	
	@XmlElement(name="subunit")
	private Subunit subunit;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public String getBase_weight()
	{

		return this.base_weight;

	}

	public void setBase_weight(String arg)
	{

		this.base_weight = arg;

	}

	public String getBase()
	{

		return this.base;

	}

	public void setBase(String arg)
	{

		this.base = arg;

	}

	public List<Item> getItemList()
	{

		return this.itemList;

	}

	public void setItemList(List<Item> arg)
	{

		this.itemList = arg;

	}

	public String getValid_from()
	{
		return valid_from;
	}

	public void setValid_from(String valid_from)
	{
		this.valid_from = valid_from;
	}

	public String getValid_till()
	{
		return valid_till;
	}

	public void setValid_till(String valid_till)
	{
		this.valid_till = valid_till;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public Texts getText_title()
	{
		return text_title;
	}

	public void setText_title(Texts text_title)
	{
		this.text_title = text_title;
	}

	public Texts getText_text()
	{
		return text_text;
	}

	public void setText_text(Texts text_text)
	{
		this.text_text = text_text;
	}

	public Subunit getSubunit()
	{
		return subunit;
	}

	public void setSubunit(Subunit subunit)
	{
		this.subunit = subunit;
	}

}
