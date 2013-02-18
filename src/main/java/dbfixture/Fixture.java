package dbfixture;

import java.util.Map;

public class Fixture {

	private Map<String, Object> values;
	
	public Fixture(Map<String, Object> values) {
		this.values = values;
	}

	public Map<String, Object> getValues() {
		return values;
	}

}
