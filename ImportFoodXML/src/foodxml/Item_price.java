package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item_price
{
	@XmlValue
	private float value;
	
	@XmlAttribute(name="quantity_scale")
	private String quantity_scale;
	
	@XmlAttribute(name="unit_factor")
	private String unit_factor;
	
	@XmlAttribute(name="discount")
	private String discount;
	
	
	public String getQuantity_scale()
	{
		return quantity_scale;
	}

	public void setQuantity_scale(String quantity_scale)
	{
		this.quantity_scale = quantity_scale;
	}

	public String getUnit_factor()
	{
		return unit_factor;
	}

	public void setUnit_factor(String unit_factor)
	{
		this.unit_factor = unit_factor;
	}

	public String getDiscount()
	{
		return discount;
	}

	public void setDiscount(String discount)
	{
		this.discount = discount;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(float value)
	{
		this.value = value;
	}
}
