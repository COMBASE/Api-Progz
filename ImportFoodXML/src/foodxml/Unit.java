package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Unit
{
	@XmlElement(name="text")
	private List<Text> unit;

	public List<Text> getunit()
	{
		return unit;
	}

	public void setunit(List<Text> unit)
	{
		this.unit = unit;
	}
	
	
}
