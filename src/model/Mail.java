package model;

/**
 *
 * @author jorge
 */
public class Mail {
    private String email;
    public Mail(String mail){
        this.email = mail;
    }
    
    public String getDomain(){
        for (int i = 0; i < email.length(); i++){
            if (email.charAt(i) == '@') return email.substring(i+1);
        }
        return null;
    }

}
