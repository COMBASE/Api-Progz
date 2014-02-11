package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Material_name
{
	@XmlElement(name="name")
	private List<Text> materialList;

	public List<Text> getMaterialList()
	{
		return materialList;
	}

	public void setMaterialList(List<Text> materialList)
	{
		this.materialList = materialList;
	}
	public Text getMaterialbylang(String lang)
	{
		if(materialList.isEmpty()) return new Text();
		for (Text text : materialList)
		{
			if(text.getLang().equals(lang)) 
			{
				return text;
			}
		}
		return new Text();
	}
}
