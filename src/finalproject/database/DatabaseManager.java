package finalproject.database;

import java.util.List;


/**
 * @param <X>
 * @author jnbcb
 */
public interface DatabaseManager<X> {

	boolean insert(X x);

	boolean update(X x);

	X get(int id);

	List<X> getList(int id);

	boolean delete(int id);
}
