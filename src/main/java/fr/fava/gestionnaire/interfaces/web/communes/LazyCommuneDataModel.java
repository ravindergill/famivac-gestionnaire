package fr.fava.gestionnaire.interfaces.web.communes;

import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import fr.fava.gestionnaire.interfaces.web.utils.AlphanumComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author paoesco
 */
public class LazyCommuneDataModel extends LazyDataModel<CommuneDTO> {

    private final List<CommuneDTO> datasource;

    private Comparator alphanumComparator;

    public LazyCommuneDataModel(List<CommuneDTO> datasource) {
        alphanumComparator = new AlphanumComparator();
        this.datasource = new ArrayList<>(datasource);
    }

    @Override
    public CommuneDTO getRowData(String rowKey) {
        for (CommuneDTO bean : datasource) {
            if (rowKey.equals(bean.getCode())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(CommuneDTO bean) {
        return bean.getCode();
    }

    @Override
    public List<CommuneDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
        setRowCount(datasource.size());
        datasource.sort((CommuneDTO o1, CommuneDTO o2) -> {
            return alphanumComparator.compare(o1.getCode(), o2.getCode());
        });
        return datasource.subList(first, max);
    }

    public boolean contains(CommuneDTO bean) {
        return datasource.contains(bean);
    }

}
