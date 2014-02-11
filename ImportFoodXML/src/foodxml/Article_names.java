package foodxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Article_names
{
	@XmlElement(name="name_main")
	private Texts name_main;
	
	@XmlElement(name="name_supplement")
	private Texts name_supplement;
	
	@XmlElement(name="names")
	private Texts names;
	
	@XmlElement(name="unit")
	private Unit unit;
	
	@XmlElement(name="name_weight")
	private String name_weight;

	private String text;

	private Texts declarationname;

	public String getText()
	{

		return this.text;

	}

	public void setText(String arg)
	{

		this.text = arg;

	}

	public Texts getDeclarationname()
	{

		return this.declarationname;

	}

	public void setDeclarationname(Texts arg)
	{

		this.declarationname = arg;

	}

	public Texts getName_main()
	{
		return name_main;
	}

	public void setName_main(Texts name_main)
	{
		this.name_main = name_main;
	}

	public Texts getName_supplement()
	{
		return name_supplement;
	}

	public void setName_supplement(Texts name_supplement)
	{
		this.name_supplement = name_supplement;
	}

	public Texts getNames()
	{
		return names;
	}

	public void setNames(Texts names)
	{
		this.names = names;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public String getName_weight()
	{
		return name_weight;
	}

	public void setName_weight(String name_weight)
	{
		this.name_weight = name_weight;
	}

}
