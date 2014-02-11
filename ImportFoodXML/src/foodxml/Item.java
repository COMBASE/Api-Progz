package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item
{
	@XmlElement(name="text_text")
	private Texts text_text;
	
	@XmlAttribute(name="is_bio")
	private String is_bio;
	
	@XmlAttribute(name="key")
	private String key;
	
	@XmlElement(name="material_name")
	private Material_name material_name;

	@XmlAttribute(name="value_per_base")
	private String value_per_base;

	@XmlAttribute(name="material_id")
	private String material_id;
	
	@XmlAttribute(name="enumber")
	private String enumber;
	
	@XmlAttribute(name="technologicallyefficient")
	private boolean technologicallyefficient;
	
	@XmlAttribute(name="class_id")
	private String class_id;
	
	@XmlAttribute(name="quota")
	private String quota;
	
	@XmlAttribute(name="quid")
	private String quid;
	
	@XmlAttribute(name="gentec")
	private String gentec;
	
	@XmlAttribute(name="irradiated")
	private String irradiated;

	@XmlAttribute(name="smaller")
	private String smaller;

	@XmlElement(name="status")
	private Text status;
	
	@XmlAttribute(name="gda_100g_percent")
	private String gda_100g_percent;
	
	@XmlAttribute(name="gda_basic_value")
	private String gda_basic_value;
	
	@XmlAttribute(name="value_100g")
	private String value_100g;
	
	@XmlAttribute(name="unit")
	private String unit;

	public String getIs_bio()
	{

		return this.is_bio;

	}

	public void setIs_bio(String arg)
	{

		this.is_bio = arg;

	}

	public String getValue_per_base()
	{

		return this.value_per_base;

	}

	public void setValue_per_base(String arg)
	{

		this.value_per_base = arg;

	}

	public String getMaterial_id()
	{

		return this.material_id;

	}

	public void setMaterial_id(String arg)
	{

		this.material_id = arg;

	}

	public String getSmaller()
	{

		return this.smaller;

	}

	public void setSmaller(String arg)
	{

		this.smaller = arg;

	}

	public String getKey()
	{

		return this.key;

	}

	public void setKey(String arg)
	{

		this.key = arg;

	}

	public Text getStatus()
	{

		return this.status;

	}

	public void setStatus(Text arg)
	{

		this.status = arg;

	}

	public Material_name getMaterial_name()
	{
		return material_name;
	}

	public void setMaterial_name(Material_name material_name)
	{
		this.material_name = material_name;
	}

	public String getQuota()
	{
		return quota;
	}

	public void setQuota(String quota)
	{
		this.quota = quota;
	}

	public String getGentec()
	{
		return gentec;
	}

	public void setGentec(String gentec)
	{
		this.gentec = gentec;
	}

	public String getIrradiated()
	{
		return irradiated;
	}

	public void setIrradiated(String irradiated)
	{
		this.irradiated = irradiated;
	}

	public Texts getText_text()
	{
		return text_text;
	}

	public void setText_text(Texts text_text)
	{
		this.text_text = text_text;
	}

	@Override
	public String toString()
	{
		return "Item [key=" + key + ", value_per_base=" +
			value_per_base +"]";
	}

	public String getGda_100g_percent()
	{
		return gda_100g_percent;
	}

	public void setGda_100g_percent(String gda_100g_percent)
	{
		this.gda_100g_percent = gda_100g_percent;
	}

	public String getGda_basic_value()
	{
		return gda_basic_value;
	}

	public void setGda_basic_value(String gda_basic_value)
	{
		this.gda_basic_value = gda_basic_value;
	}

	public String getValue_100g()
	{
		return value_100g;
	}

	public void setValue_100g(String value_100g)
	{
		this.value_100g = value_100g;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

}
