package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Article_text
{
	@XmlElement(name="id")
	private String id;
	
	@XmlElement(name="text_title")
	private String text_title;
	
	@XmlElement(name="text")
	private Text text;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText_title()
	{
		return text_title;
	}

	public void setText_title(String text_title)
	{
		this.text_title = text_title;
	}

	public Text getText()
	{
		return text;
	}

	public void setText(Text text)
	{
		this.text = text;
	}
}
