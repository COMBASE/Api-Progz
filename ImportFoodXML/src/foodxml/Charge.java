package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Charge
{

	private String text;

	@XmlElement(name="date")
	private String date;

	private Labeling_method labeling_method;

	private Labeling_value labeling_value;

	@XmlElement(name="active")
	private Text active;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public String getDate()
	{

		return this.date;

	}

	public void setDate(String arg)
	{

		this.date = arg;

	}

	public Labeling_method getLabeling_method()
	{

		return this.labeling_method;

	}

	public void setLabeling_method(Labeling_method arg)
	{

		this.labeling_method = arg;

	}

	public Labeling_value getLabeling_value()
	{

		return this.labeling_value;

	}

	public void setLabeling_value(Labeling_value arg)
	{

		this.labeling_value = arg;

	}

	public Text getActive()
	{

		return this.active;

	}

	public void setActive(Text arg)
	{

		this.active = arg;

	}

}
