package dbfixture;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixtureBuilder {

	public Fixture build(TableMetadata table) {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for(ColumnMetadata column : table.getColumns()) {
			if (column.isNullable()) {
				values.put(column.getName(), null);
			} else if (column.isNumeric()) {
				values.put(column.getName(), 0);
			}
		}
		return new Fixture(values);
	}

}
