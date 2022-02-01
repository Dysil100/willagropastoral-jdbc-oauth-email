package duo.cmr.willagropastoral.domain.service.analysealimentaire;


import duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs.EnergieMethabolisable;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs.Lysine;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs.Methyonine;
import duo.cmr.willagropastoral.domain.service.analysealimentaire.apportNutritifs.ProteineBrute;

public record Standard(String description, Lysine lysine, Methyonine methyonine, ProteineBrute proteineBrute, EnergieMethabolisable energieMethabolisable) {
}
