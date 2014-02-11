package foodxml;

public class Generator
{

	private String text;

	private String software;

	private String version;

	private String customerid;

	private Developer developer;

	public String getCustomerid()
	{

		return this.customerid;

	}

	public Developer getDeveloper()
	{

		return this.developer;

	}

	public String getSoftware()
	{

		return this.software;

	}

	public String getText()
	{

		return this.text;

	}

	public String getVersion()
	{

		return this.version;

	}

	public void setCustomerid(String arg)
	{

		this.customerid = arg;

	}

	public void setDeveloper(Developer arg)
	{

		this.developer = arg;

	}

	public void setSoftware(String arg)
	{

		this.software = arg;

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
