package oasis.test.web;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="document")
public class PublishOrgInfo {
	@XmlElement(name="root")
	private PublishOrgResultInfo publishOrgResultInfo;
}
