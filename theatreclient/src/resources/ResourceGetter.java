/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ResourceBundle;

/**
 *
 * @author dejan
 */
public class ResourceGetter implements java.io.Serializable {

    private ResourceBundle rb;

    private ResourceGetter(){
        rb = ResourceBundle.getBundle("resources.Resources", LocaleContext.getInstance().getLocale());
    }
    
    private static ResourceGetter instance;

    public static ResourceGetter getInstance() {
        if (instance == null) {
            instance = new ResourceGetter();
        }
        return instance;
    }

    public String getValue(String key) {
        return rb.getString(key);
    }

    public ResourceBundle getRb() {
        return rb;
    }

    public void setRb(ResourceBundle rb) {
        this.rb = rb;
    }
    
    public void resetBundle(){
        rb = ResourceBundle.getBundle("resources.Resources", LocaleContext.getInstance().getLocale());
    }
}
