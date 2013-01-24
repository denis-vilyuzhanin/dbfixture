package dbfixture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FixtureBuilderTest {

	FixtureBuilder builder;
	
	@Before
	public void init() {
		builder = new FixtureBuilder();
	}
	
	@Test
	public void  buildFixtureForNullableColumn() {
		
	}
}
