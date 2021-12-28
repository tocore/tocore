package oasis;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="stockprice")
public class StockPrice {
	
	@XmlAttribute(name="querytime")
	private String querytime;
	
	@XmlElement(name="TBL_DailyStock")
	private TBL_DailyStock tbl_DailyStock;
		
	@ToString
	@Getter
	@XmlRootElement(name="TBL_DailyStock")
	public static class TBL_DailyStock {
		@XmlElement(name = "DailyStock")
		private List<DailyStock> dailyStock;		
	}	
}
