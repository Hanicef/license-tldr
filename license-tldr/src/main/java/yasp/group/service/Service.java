
package yasp.group.service;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;
import yasp.group.entity.License;

@Local
@Stateless
public class Service {

	@PersistenceContext
	private EntityManager manager;

	public List<License> getAllLicenses() {
		List<License> result = manager.createQuery("SELECT license FROM License license").getResultList();
		return result;
	}
}
