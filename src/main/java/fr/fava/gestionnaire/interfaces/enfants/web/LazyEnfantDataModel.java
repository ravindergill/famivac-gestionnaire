package fr.fava.gestionnaire.interfaces.enfants.web;

import fr.fava.gestionnaire.application.enfant.RetrieveEnfantsDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 */
public class LazyEnfantDataModel extends LazyDataModel<RetrieveEnfantsDTO> {

    private final List<RetrieveEnfantsDTO> datasource;

    public LazyEnfantDataModel(List<RetrieveEnfantsDTO> datasource) {
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public RetrieveEnfantsDTO getRowData(String rowKey) {
        for (RetrieveEnfantsDTO bean : datasource) {
            if (Long.valueOf(rowKey).equals(bean.getId())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(RetrieveEnfantsDTO bean) {
        return bean.getId();
    }

    @Override
    public List<RetrieveEnfantsDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        return datasource.subList(first, max);
    }

}
