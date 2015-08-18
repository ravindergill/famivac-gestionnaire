package fr.fava.gestionnaire.interfaces.web.utils;

import fr.fava.gestionnaire.domain.utils.AlphanumComparator;
import fr.fava.gestionnaire.interfaces.web.sejours.LazySejourDataModel;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.SortOrder;

/**
 * @author paoesco
 * @param <O>
 */
public class LazySorter<O> implements Comparator<O> {

    private final Class<O> clazz;

    private final String sortField;

    private final SortOrder sortOrder;

    private final AlphanumComparator alphanumComparator;

    public LazySorter(Class<O> clazz, String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
        this.clazz = clazz;
        this.alphanumComparator = new AlphanumComparator();
    }

    @Override
    public int compare(O o1, O o2) {
        try {
            String methodName = "get" + Character.toUpperCase(this.sortField.charAt(0)) + this.sortField.substring(1);
            Object value1 = clazz.getMethod(methodName).invoke(o1);
            Object value2 = clazz.getMethod(methodName).invoke(o2);
            int value;
            if (value1 instanceof String && value2 instanceof String) {
                String s1 = (String) value1;
                String s2 = (String) value2;
                value = alphanumComparator.compare(s1.toLowerCase(), s2.toLowerCase());
            } else {
                value = ((Comparable) value1).compareTo(value2);
            }
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(LazySejourDataModel.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}