package oasis.aws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="OpenAPI_ServiceResponse")
public class API {
	@XmlElement(name="response")
	private TResponse tResponse;
}
