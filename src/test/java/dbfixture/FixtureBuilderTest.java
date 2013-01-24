package dbfixture;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FixtureBuilderTest {

	FixtureBuilder builder;

	@Mock
	TableMetadata table;
	
	@Mock
	TypeMetadata anyNumericType;
	
	@Mock
	TypeMetadata anyCharacterType;
	
	@Mock
	TypeMetadata anyDateType;

	@Before
	public void init() {
		when(anyNumericType.isNumeric()).thenReturn(true);
		when(anyCharacterType.isCharacter()).thenReturn(true);
		when(anyDateType.isDate()).thenReturn(true);
		
		builder = new FixtureBuilder();
		List<ColumnMetadata> columns = Arrays.asList(
				nullable(numeric(column("nullableNumericColumn"))),
				required(numeric(column("numericColumn"))),
				nullable(character(column("nullableCharacterColumn"))),
				required(character(column("characterColumn"))),
				nullable(date(column("nullableDateColumn"))),
				required(date(column("dateColumn"))));
		when(table.getColumns()).thenReturn(columns);
	}

	@Test
	public void buildFixture() {
		Fixture actualFixture = builder.build(table);

		Map<String, Object> expectedValues = new LinkedHashMap<String, Object>();
		expectedValues.put("nullableNumericColumn", null);
		expectedValues.put("numericColumn", 0);
		expectedValues.put("nullableCharacterColumn", null);
		expectedValues.put("characterColumn", "A");
		expectedValues.put("nullableDateColumn", null);
		expectedValues.put("dateColumn", new Date());

		assertEquals(expectedValues, actualFixture.getValues());
	}

	private ColumnMetadata column(String name) {
		ColumnMetadata column = mock(ColumnMetadata.class);
		when(column.getName()).thenReturn(name);
		return column;
	}

	private ColumnMetadata numeric(ColumnMetadata column) {
		when(column.getType()).thenReturn(anyNumericType);
		return column;
	}

	private ColumnMetadata character(ColumnMetadata column) {
		when(column.getType()).thenReturn(anyCharacterType);
		return column;
	}
	
	private ColumnMetadata date(ColumnMetadata column) {
		when(column.getType()).thenReturn(anyDateType);
		return column;
	}

	private ColumnMetadata nullable(ColumnMetadata column) {
		when(column.isNullable()).thenReturn(true);
		return column;
	}

	private ColumnMetadata required(ColumnMetadata column) {
		when(column.isNullable()).thenReturn(false);
		return column;
	}
}
