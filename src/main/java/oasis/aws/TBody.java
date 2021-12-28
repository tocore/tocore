package oasis.aws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="body")
public class TBody {
	
	@XmlElement(name="items")
	private Items items;
	
	@XmlElement(name="dataType")
	private String dataType;
	
	@XmlElement(name="numOfRows")
	private Integer numOfRows;
	
	@XmlElement(name="pageNo")
	private Integer pageNo;
	
	@XmlElement(name="totalCount")
	private Integer totalCount;
		
}
