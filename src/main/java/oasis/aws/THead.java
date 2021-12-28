package oasis.aws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="header")
public class THead {
	@XmlElement(name="resultCode")
	private String resultCode;
	
	@XmlElement(name="resultMsg")
	private String resultMsg;
}
