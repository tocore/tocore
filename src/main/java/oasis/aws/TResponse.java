package oasis.aws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="response")
public class TResponse {
	
	@XmlElement(name="header")
	private THead theader;
	
	@XmlElement(name="body")
	private TBody tbody;
}
