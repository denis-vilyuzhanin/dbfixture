package dbfixture;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixtureBuilder {

	public Fixture build(TableMetadata table) {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for (ColumnMetadata column : table.getColumns()) {
			TypeMetadata type = column.getType();
			Object value = null;
			
			if (!column.isNullable()) {
				if (type.isNumeric()) {
					value = 0;
				} else if (type.isCharacter()) {
					value = "A";
				}
			}
			values.put(column.getName(), value);
		}
		return new Fixture(values);
	}

}
