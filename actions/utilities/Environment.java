package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config {
	String url();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("request_url")
    String request_url();

    @Key("request_url_manage")
    String request_url_manage();

    @Key("dashboard_url")
    String dashboard_url();

    @Key("url_fe_store")
    String url_fe_store();

    @Key("password_fe_store")
    String password_fe_store();
    
    @Key("fe_store_url")
    String fe_store_url();
    
    @Key("fe_store_password")
    String fe_store_password();
    
    @Key("admin_app_url")
    String admin_app_url();
    
    @Key("fe_store_preview_url")
    String fe_store_preview_url();
    
    @Key("admin_settings_url_string")
    String admin_settings_url_string();
    
    @Key("admin_settings_authToken")
    String admin_settings_authToken();

}
