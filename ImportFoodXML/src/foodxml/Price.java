package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Price
{
	@XmlAttribute(name="currency")
	private String currency;
	
	@XmlAttribute(name="valid_from")
	private String valid_from;
	
	@XmlAttribute(name="valid_till")
	private String valid_till;
	
	@XmlAttribute
	private float vat;
	
	@XmlAttribute
	private float deposit;
	
	@XmlAttribute
	private boolean with_vat;
	
	@XmlAttribute
	private boolean advertisement;
	
	@XmlElement(name="item")
	private Item_price item;

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getValid_from()
	{
		return valid_from;
	}

	public void setValid_from(String valid_from)
	{
		this.valid_from = valid_from;
	}

	public String getValid_till()
	{
		return valid_till;
	}

	public void setValid_till(String valid_till)
	{
		this.valid_till = valid_till;
	}

	public float getVat()
	{
		return vat;
	}

	public void setVat(float vat)
	{
		this.vat = vat;
	}

	public float getDeposit()
	{
		return deposit;
	}

	public void setDeposit(float deposit)
	{
		this.deposit = deposit;
	}

	public boolean isWith_vat()
	{
		return with_vat;
	}

	public void setWith_vat(boolean with_vat)
	{
		this.with_vat = with_vat;
	}

	public boolean isAdvertisement()
	{
		return advertisement;
	}

	public void setAdvertisement(boolean advertisement)
	{
		this.advertisement = advertisement;
	}

	public Item_price getItem()
	{
		return item;
	}

	public void setItem(Item_price item)
	{
		this.item = item;
	}
}
