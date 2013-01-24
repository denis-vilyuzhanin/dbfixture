package dbfixture;

import java.util.Arrays;
import java.util.HashMap;
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
	ColumnMetadata nullableNumericColumn;
	
	@Mock
	ColumnMetadata numericColumn;
	
	@Mock
	ColumnMetadata nullableCharacterColumn;
	
	@Mock
	ColumnMetadata characterColumn;
	
	@Before
	public void init() {
		builder = new FixtureBuilder();
		
		mockColumn(nullableNumericColumn, "nullableNumericColumn", true, true, false);
		mockColumn(numericColumn, "numericColumn", false, true, false);
		mockColumn(nullableCharacterColumn, "nullableCharacterColumn", false, true, true);
		mockColumn(characterColumn, "characterColumn", false, false, true);
		
		when(table.getColumns()).thenReturn(
				Arrays.asList(nullableNumericColumn, 
							  numericColumn, 
							  nullableCharacterColumn, 
							  characterColumn));
	}

	private void mockColumn(ColumnMetadata column, String name, boolean isNullable, boolean isNumeric, boolean isCharacter) {
		when(column.getName()).thenReturn(name);
		when(column.isNullable()).thenReturn(isNullable);
		when(column.isNumeric()).thenReturn(isNumeric);
		when(column.isCharacter()).thenReturn(isCharacter);
	}
	
	@Test
	public void  buildFixture() {
		Fixture actualFixture = builder.build(table);
		
		Map<String, Object> expectedValues = new HashMap<String, Object>();
		expectedValues.put("nullableNumericColumn", null);
		expectedValues.put("numericColumn", 0);
		expectedValues.put("nullableCharacterColumn", null);
		expectedValues.put("characterColumn", "A");
		
		assertEquals(expectedValues, actualFixture.getValues());
	}
}

