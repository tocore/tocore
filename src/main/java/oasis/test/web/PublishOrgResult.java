package oasis.test.web;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@XmlRootElement(name="result")
public class PublishOrgResult {
	
	@XmlElement(name="publishOrg")
	private String publishOrg;
	
	@XmlElement(name="repcategory")
	private String repcategory;
	
	@XmlElement(name="contentId")
	private String contentId;
	
	@XmlElement(name="subject")
	private String subject;
		
	@XmlElement(name="originUrl")
	private String originUrl;
	
	@XmlElement(name="regDate")
	private String regDate;
	
	@XmlElement(name="inactiveYn")
	private String inactiveYn;
	
	@XmlElement(name="deleteYn")
	private String deleteYn;
	
	@XmlElement(name="thumnail")
	private String thumnail;
}
