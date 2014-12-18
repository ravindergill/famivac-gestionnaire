package fr.fava.gestionnaire.interfaces.web.utils;

import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import fr.fava.gestionnaire.domain.referentiel.CommuneService;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ApplicationScoped
@FacesConverter("communeConverter")
public class CommuneConverter implements Converter {

    @Inject
    private CommuneService communeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
            return communeService.retrieve(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((CommuneDTO) value).getCode();
    }

}
