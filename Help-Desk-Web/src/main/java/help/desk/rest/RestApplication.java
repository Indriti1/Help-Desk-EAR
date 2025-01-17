package help.desk.rest;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

	  @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<>();
	        classes.add(TaskRessource.class);
	        return classes;
	    }
}
