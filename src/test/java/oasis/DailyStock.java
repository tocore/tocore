package oasis;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="DailyStock")
public class DailyStock {
	
	@XmlAttribute(name="day_Date")
	private String day_Date;	

	@XmlAttribute(name="day_EndPrice")
	private String day_EndPrice;
}
