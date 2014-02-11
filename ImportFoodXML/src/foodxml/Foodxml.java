package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Foodxml
{

	private String text;

	private String lang;

	private String version;

	@XmlElement(name="header")
	private Header header;

	private Catalog catalog;

	public Catalog getCatalog()
	{

		return this.catalog;

	}

	public Header getHeader()
	{

		return this.header;

	}

	public String getLang()
	{

		return this.lang;

	}

	public String getText()
	{

		return this.text;

	}

	public String getVersion()
	{

		return this.version;

	}

	public void setCatalog(Catalog arg)
	{

		this.catalog = arg;

	}

	public void setHeader(Header arg)
	{

		this.header = arg;

	}

	public void setLang(String arg)
	{

		this.lang = arg;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public void setVersion(String arg)
	{

		this.version = arg;

	}

}
