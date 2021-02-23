package pojoClasses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPojo {
	private String firstName;
	private String lastName;
	private String phone;
	private List<String> addresses;
}
	