package commons;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({"file:environmentList/${server}.properties"})
public interface IEnvironment extends Config {
	String url();
	
	@Key("db.url")
	String getDBHostname();
	
	@Key("db.username")
	String getDBUsername();
	
	@Key("db.password")
	String getDBPassword();
	
	@Key("UserUrl")
	String getUserUrl();
	
	@Key("AdminUrl")
	String getAdminUrl();
	

}
