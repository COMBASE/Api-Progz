package foodxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Group 
{
	@XmlAttribute
	private String id;
	
	@XmlAttribute(name="unique_id")
	private String unique_id;
	
	@XmlElement(name="details")
	private Detail details;
	
	@XmlElement(name="group")
	private List<Group> groups;
	
	@XmlElement(name="article")
	private List<Article> articles;
	
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getUnique_id()
	{
		return unique_id;
	}
	public void setUnique_id(String unique_id)
	{
		this.unique_id = unique_id;
	}
	public Detail getDetails()
	{
		return details;
	}
	public void setDetails(Detail details)
	{
		this.details = details;
	}
	public List<Group> getGroups()
	{
		return groups;
	}
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}
	public List<Article> getArticles()
	{
		return articles;
	}
	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}

	
}
