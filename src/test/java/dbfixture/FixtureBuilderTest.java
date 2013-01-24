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
	
	
	@Before
	public void init() {
		builder = new FixtureBuilder();
		
		when(nullableNumericColumn.getName()).thenReturn("nullableNumericColumn");
		when(nullableNumericColumn.isNullable()).thenReturn(true);
		when(nullableNumericColumn.isNumeric()).thenReturn(true);
		
		when(table.getColumns()).thenReturn(Arrays.asList(nullableNumericColumn));
	}
	
	@Test
	public void  buildFixture() {
		Fixture actualFixture = builder.build(table);
		
		Map<String, Object> expectedValues = new HashMap<String, Object>();
		expectedValues.put("nullableNumericColumn", null);
		
		assertEquals(expectedValues, actualFixture.getValues());
	}
}
