package duo.cmr.willagropastoral.persistence.annotations;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public @interface User {
}
