package foodxml;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Detail
{
	@XmlElement
	private String path;

	@XmlElement(name="names")
	private Texts nameList;
	
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public Texts getNames()
	{
		return this.nameList;
	}
	public void setNames(Texts names)
	{
		this.nameList = names;
	}

}
