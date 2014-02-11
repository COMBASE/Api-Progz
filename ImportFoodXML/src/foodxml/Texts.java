package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Texts
{
	@XmlElement(name="text")
	private List<Text> textList;

	public List<Text> gettextList()
	{
		return textList;
	}

	public void setNameList(List<Text> textList)
	{
		this.textList = textList;
	}
	public Text gettextbylang(String lang)
	{
		if(textList.isEmpty()) return new Text();
		for (Text text : textList)
		{
			if(text.getLang().equals(lang)) 
			{
				return text;
			}
		}
		return new Text();
	}
}
