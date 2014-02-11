package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Prices
{
	@XmlElement(name="price")
	private List<Price> priceList;

	public List<Price> getPriceList()
	{
		return priceList;
	}

	public void setPriceList(List<Price> priceList)
	{
		this.priceList = priceList;
	}
}
