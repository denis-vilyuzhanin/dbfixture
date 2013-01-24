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
	
	@Before
	public void init() {
		builder = new FixtureBuilder();
		
		mockColumn(nullableNumericColumn, "nullableNumericColumn", true, true);
		
		when(table.getColumns()).thenReturn(Arrays.asList(nullableNumericColumn));
	}

	private void mockColumn(ColumnMetadata column, String name, boolean isNullable, boolean isNumeric) {
		when(column.getName()).thenReturn(name);
		when(column.isNullable()).thenReturn(isNullable);
		when(column.isNumeric()).thenReturn(isNumeric);
	}
	
	@Test
	public void  buildFixture() {
		Fixture actualFixture = builder.build(table);
		
		Map<String, Object> expectedValues = new HashMap<String, Object>();
		expectedValues.put("nullableNumericColumn", null);
		
		assertEquals(expectedValues, actualFixture.getValues());
	}
}
