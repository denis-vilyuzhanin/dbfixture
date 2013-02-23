package dbfixture;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FixtureBuilder_UniqueConstraintTest {

	@Mock
	TableMetadata table;
	
	@Mock
	ConstraintMetadata singleColumnUniqueConstraint;
	
	FixtureBuilder builder = new FixtureBuilder();
	
	@Test
	public void twoUniqueFixtures() {
		when(table.getConstraints())
			.thenReturn(Arrays.asList(singleColumnUniqueConstraint));
		
		Fixture fixture1 = builder.build(table);
		Fixture fixture2 = builder.build(table);
		
		Object value1 = fixture1.getValues().get("uniqueColumn");
		Object value2 = fixture2.getValues().get("uniqueColumn");
		assertFalse(value1.equals(value2));
	}
	
	
}
