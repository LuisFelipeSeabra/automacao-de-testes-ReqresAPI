package br.df.lseabra.suite;

import br.df.lseabra.rest.test.RegisterTest;
import br.df.lseabra.rest.test.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({ 
	UserTest.class, 
	RegisterTest.class
	
})
public class Suite {

}


