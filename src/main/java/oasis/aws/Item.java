package oasis.aws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="item")
public class Item {
	@XmlElement(name="baseDate")
	private String baseDate;
	
	@XmlElement(name="baseTime")
	private String baseTime;
	
	@XmlElement(name="category")
	private String category;
	
	@XmlElement(name="nx")
	private Integer nx;
	
	@XmlElement(name="ny")
	private Integer ny;
	
	@XmlElement(name="obsrValue")
	private Integer obsrValue;
}
