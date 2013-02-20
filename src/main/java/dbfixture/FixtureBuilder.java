package dbfixture;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class FixtureBuilder {

	public Fixture build(TableMetadata table, Map<String, Object> predefinedValues) {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for (ColumnMetadata column : table.getColumns()) {
			TypeMetadata type = column.getType();
			Object value = null;
			
			if (!column.isNullable()) {
				if (type.isNumeric()) {
					value = 0;
				} else if (type.isCharacter()) {
					value = "A";
				} else if (type.isDate()) {
					value = new Date();
				} else {
					throw new IllegalArgumentException("Can't provide fixture for type: " + type);
				}
			}
			values.put(column.getName(), value);
		}
		return new Fixture(values);
	}

	public Fixture build(TableMetadata table) {
		return build(table, Collections.<String, Object> emptyMap());
	}

}
