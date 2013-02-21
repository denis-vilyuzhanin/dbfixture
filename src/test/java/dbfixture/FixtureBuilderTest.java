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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FixtureBuilderTest {
	
	private static final int ANY_INT = 100;

	FixtureBuilder builder;

	@Mock
	TableMetadata table;
	
	@Mock
	TypeMetadata anyNumericType;
	
	@Mock
	TypeMetadata anyCharacterType;
	
	@Mock
	TypeMetadata anyDateType;
	
	Map<String, Object> expectedFixtureWithDefaultValues = new LinkedHashMap<String, Object>();
	Map<String, Object> expectedFixtureWithOnePredefinedValue = new LinkedHashMap<String, Object>();

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
		
		expectedFixtureWithDefaultValues.put("nullableNumericColumn", null);
		expectedFixtureWithDefaultValues.put("numericColumn", 0);
		expectedFixtureWithDefaultValues.put("nullableCharacterColumn", null);
		expectedFixtureWithDefaultValues.put("characterColumn", "A");
		expectedFixtureWithDefaultValues.put("nullableDateColumn", null);
		expectedFixtureWithDefaultValues.put("dateColumn", new Date());
		
		expectedFixtureWithOnePredefinedValue.putAll(expectedFixtureWithDefaultValues);
		expectedFixtureWithOnePredefinedValue.put("nullableNumericColumn", ANY_INT);
	}

	@Test
	public void buildFixture() {
		Fixture actualFixture = builder.build(table);
		assertEquals(expectedFixtureWithDefaultValues.toString(), actualFixture.getValues().toString());
	}
	
	@Test
	public void buildFixtureWithPredefinedValue() {
		Map<String, Object> predefinedValues = new HashMap<String, Object>();
		predefinedValues.put("nullableNumericColumn", ANY_INT);
		
		Fixture actualFixture = builder.build(table, predefinedValues);
		assertEquals(expectedFixtureWithOnePredefinedValue.toString(), actualFixture.getValues().toString());
		
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
