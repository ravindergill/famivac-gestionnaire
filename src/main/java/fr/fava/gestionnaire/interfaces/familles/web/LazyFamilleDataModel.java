package fr.fava.gestionnaire.interfaces.familles.web;

import fr.fava.gestionnaire.application.famille.RetrieveFamillesResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyFamilleDataModel extends LazyDataModel<RetrieveFamillesResponseDTO> {

    private final List<RetrieveFamillesResponseDTO> datasource;

    public LazyFamilleDataModel(List<RetrieveFamillesResponseDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public RetrieveFamillesResponseDTO getRowData(String rowKey) {
        for (RetrieveFamillesResponseDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RetrieveFamillesResponseDTO bean) {
        return bean.getId();
    }

    @Override
    public List<RetrieveFamillesResponseDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
