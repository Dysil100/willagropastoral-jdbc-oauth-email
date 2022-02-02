package duo.cmr.willagropastoral.web.configurations.analyse;

import duo.cmr.willagropastoral.domain.Formular;
import duo.cmr.willagropastoral.web.services.ServiceAgro;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyseConfiguration {

    @Bean
    Formular formular(ServiceAgro serviceAgro){
        Formular formular = new Formular();
        formular.setServiceAgro(serviceAgro);
        return formular;
    }
}
