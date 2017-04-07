package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;
import play.mvc.Result;
import play.libs.Json;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

/**
 * Created by hafsa on 3/18/2017.
 */
public class EmailController {
    public Result checkEmail() {

        String eid = "hafsahuda10@gmail.com";
        String ccEid = " aishwaryamurthy96@gmail.com";
        SimpleEmail email = new SimpleEmail();
        try {
            Logger.debug("in try");
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("garbagemonitoring1.mail@gmail.com", "code4route"));
            Logger.debug("auth");
            email.setSSLOnConnect(true);
            email.setFrom("hungerquest.mail@gmail.com");
            Logger.debug("from");
            email.addTo(eid);
            Logger.debug("to");
            email.setSubject("Notification");
            email.setMsg("The Garbage is Full.\nLocation : Street: 402 Shaikpet" );
            email.send();
        } catch (Exception e) {
            return badRequest("mail error");
        }
        return ok();
    }
}
