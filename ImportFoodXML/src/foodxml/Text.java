package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;



@XmlAccessorType(XmlAccessType.FIELD)
public class Text
{	
	@XmlValue
	private String text;

	@XmlAttribute(name="type")
	private String type;
	
	@XmlAttribute(name="lang")
	private String lang;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public String getLang()
	{

		return this.lang;

	}

	public void setLang(String arg)
	{

		this.lang = arg;

	}

}
