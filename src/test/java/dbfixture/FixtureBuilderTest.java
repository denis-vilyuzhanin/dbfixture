package dbfixture;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FixtureBuilderTest {

	FixtureBuilder builder;
	
	@Mock
	TableMetadata table;
	
	@Before
	public void init() {
		builder = new FixtureBuilder();
	}
	
	@Test
	public void  buildFixtureForNullableColumn() {
		Fixture actualFixture = builder.build(table);
		Assert.assertNotNull(actualFixture);
	}
}
