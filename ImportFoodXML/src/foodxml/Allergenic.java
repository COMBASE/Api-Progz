package foodxml;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Allergenic
{

	private String text;

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String code;
	
	@XmlAttribute
	private String incomplete;

	@XmlElement(name="text_title")
	private Texts text_title;
	
	@XmlElement(name="text_text")
	private Texts text_text;
	
	@XmlElement(name="item")
	private List<Item> itemList;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public String getId()
	{

		return this.id;

	}

	public void setId(String arg)
	{

		this.id = arg;

	}

	public String getIncomplete()
	{

		return this.incomplete;

	}

	public void setIncomplete(String arg)
	{

		this.incomplete = arg;

	}

	public List<Item> getItemList()
	{

		return this.itemList;

	}

	public void setItemList(List<Item> arg)
	{

		this.itemList = arg;

	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
