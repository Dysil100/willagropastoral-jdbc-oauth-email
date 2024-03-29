package duo.cmr.willagropastoral.boundedContexts.dasandere.domain;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class CustomEmailValidator implements Predicate<String> {
    //        TODO: Regex to validate email
    // TODO: 03.02.22 find the best java email validator
       Pattern pattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");

    @Override
    public boolean test(String email) {
         return pattern.matcher(email).matches();
        /*boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;*/
    }
}
