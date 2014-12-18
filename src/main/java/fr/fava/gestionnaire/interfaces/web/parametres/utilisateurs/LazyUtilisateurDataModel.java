package fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs;

import fr.fava.gestionnaire.domain.utilisateur.UtilisateurResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyUtilisateurDataModel extends LazyDataModel<UtilisateurResponseDTO> {

    private final List<UtilisateurResponseDTO> datasource;

    public LazyUtilisateurDataModel(List<UtilisateurResponseDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public UtilisateurResponseDTO getRowData(String rowKey) {
        for (UtilisateurResponseDTO bean : datasource) {
            if (rowKey.equals(bean.getLogin())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(UtilisateurResponseDTO bean) {
        return bean.getLogin();
    }

    @Override
    public List<UtilisateurResponseDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
