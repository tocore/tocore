package oasis.aws;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name="items")
public class Items {
	@XmlElement(name = "item")
	private List<Item> item;
}
