package duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.analyseconfiguration;

import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.domain.CustomAnalyseForm;
import duo.cmr.willagropastoral.boundedContexts.analysealimentaire.web.services.AnalyseAlimentaireService;
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
