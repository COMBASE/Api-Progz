package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header
{

	private String text;

	@XmlElement(name="created")
	private String created;

	@XmlElement(name="last_modification")
	private String last_modification;

	@XmlElement(name="generator")
	private Generator generator;

	public String getCreated()
	{

		return this.created;

	}

	public Generator getGenerator()
	{

		return this.generator;

	}

	public String getLast_modification()
	{

		return this.last_modification;

	}

	public String getText()
	{

		return this.text;

	}

	public void setCreated(String arg)
	{

		this.created = arg;

	}

	public void setGenerator(Generator arg)
	{

		this.generator = arg;

	}

	public void setLast_modification(String arg)
	{

		this.last_modification = arg;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

}
