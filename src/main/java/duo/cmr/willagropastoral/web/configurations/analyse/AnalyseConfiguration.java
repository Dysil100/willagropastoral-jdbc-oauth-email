package duo.cmr.willagropastoral.web.configurations.analyse;

import duo.cmr.willagropastoral.web.services.subservices.CustomAnalyseForm;
import duo.cmr.willagropastoral.web.services.subservices.AnalyseAlimentaireService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyseConfiguration {

    @Bean
    CustomAnalyseForm formular(AnalyseAlimentaireService analyseAlimentaireService){
        CustomAnalyseForm customAnalyseForm = new CustomAnalyseForm();
        customAnalyseForm.setAnalyseAlimentaireService(analyseAlimentaireService);
        return customAnalyseForm;
    }
}
