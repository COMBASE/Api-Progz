package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ingredients
{
	@XmlElement(name="item")
	private List<Item> itemList;
	
	@XmlAttribute(name="valid_from")
	private String valid_from;
	
	@XmlAttribute(name="valid_till")
	private String valid_till;

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

	public List<Item> getItemList()
	{
		return itemList;
	}

	public void setItemList(List<Item> itemList)
	{
		this.itemList = itemList;
	}
	

}
