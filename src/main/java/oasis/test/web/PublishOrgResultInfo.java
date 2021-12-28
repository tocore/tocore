package oasis.test.web;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString	
@Getter
@XmlRootElement(name="root")
public class PublishOrgResultInfo {
	@XmlElement(name = "result")
	private List<PublishOrgResult> publishOrgResult;
	
	@XmlElement(name="resultCnt")
	private Integer resultCnt;
	
	@XmlElement(name="resultCode")
	private Integer resultCode;
	
	@XmlElement(name="resultMsg")
	private String resultMsg;
}
