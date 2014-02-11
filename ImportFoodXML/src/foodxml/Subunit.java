package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subunit
{
	@XmlAttribute(name="show")
	private String show;
	
	@XmlElement(name="weight")
	private String weight;
	
	@XmlElement(name="unit")
	private Unit unit;
	
	@XmlElement(name="text_text")
	private Texts text_text;

	public String getShow()
	{
		return show;
	}

	public void setShow(String show)
	{
		this.show = show;
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public Texts getText_text()
	{
		return text_text;
	}

	public void setText_text(Texts text_text)
	{
		this.text_text = text_text;
	}
}
