package duo.cmr.willagropastoral.domain;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class CustomEmailValidator implements Predicate<String> {
    //        TODO: Regex to validate email
       Pattern pattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    /*
     * regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
     *         regMatcher   = regexPattern.matcher(emailAddress);
     */
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
