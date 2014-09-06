/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author dejan
 */
public class LocaleContext implements java.io.Serializable{
    
    private LocaleContext(){
        localeCode = "ci";
    }
    
    private static LocaleContext instance;
    
    public static LocaleContext getInstance(){
        if (instance == null){
            instance = new LocaleContext();
        }
        return instance;
    }
    
    
    private String localeCode;
    private Locale locale;
    
    private static Map<String,Object> langs;
    static{
            langs = new LinkedHashMap<String,Object>();
            langs.put("sr", new Locale("sr")); //label, value
            langs.put("ci", new Locale("ci"));
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public static Map<String, Object> getLangs() {
        return langs;
    }

    public Locale getLocale() {
        return (Locale) getLangs().get(localeCode);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    
    
}
