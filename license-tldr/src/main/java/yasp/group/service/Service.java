
package yasp.group.service;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import yasp.group.entity.*;

@Local
@Stateless
public class Service {

	@PersistenceContext
	private EntityManager manager;

	public List<License> getAllLicenses() {
		List<License> result = manager.createQuery("SELECT l FROM License l").getResultList();
		return result;
	}

	public List<Summary> getAllSummary() {
		List<Summary> result = manager.createQuery("SELECT l FROM Summary s").getResultList();
		return result;
	}

	public List<LicenseSummary> getAllLicenseSummary() {
		List<LicenseSummary> result = manager.createQuery("SELECT ls FROM LicenseSummary ls").getResultList();
		return result;
	}

	public License getLicenseById(int id) {
		try {
			License result =
				manager.createQuery("SELECT l FROM License l WHERE l.id = :id", License.class)
				.setParameter("id", id)
				.getResultList().get(0);
			return result;
		} catch (IndexOutOfBoundsException e) {
			// No result; return NULL.
			return null;
		}
	}

	public Summary getSummaryById(int id) {
		try {
			Summary result =
				manager.createQuery("SELECT s FROM Summary s WHERE s.id = :id", Summary.class)
				.setParameter("id", id)
				.getResultList().get(0);
			return result;
		} catch (IndexOutOfBoundsException e) {
			// No result; return NULL.
			return null;
		}
	}

	public LicenseSummary getLicenseSummaryById(int id) {
		try {
			LicenseSummary result =
				manager.createQuery("SELECT ls FROM LicenseSummary ls WHERE ls.id = :id", LicenseSummary.class)
				.setParameter("id", id)
				.getResultList().get(0);
			return result;
		} catch (IndexOutOfBoundsException e) {
			// No result; return NULL.
			return null;
		}
	}

	public List<Summary> getSummaryFromLicense(License license) {
		return this.getSummaryFromLicense(license.getId());
	}

	public List<Summary> getSummaryFromLicense(int id) {
		List<LicenseSummary> list =
			manager.createQuery("SELECT ls FROM LicenseSummary ls WHERE ls.license = :id", LicenseSummary.class)
			.setParameter("id", id)
			.getResultList();
		List<Summary> result = new ArrayList<Summary>();
		for (int i = 0; i < list.size(); i++) {
			result.add(
				manager.createQuery("SELECT s FROM Summary s WHERE s.id = :id", Summary.class)
					.setParameter("id", list.get(i).getSummary())
					.getResultList().get(0));
		}
		return result;
	}

	public void createLicense(License license) {
		manager.persist(license);
	}

	public void createSummary(Summary summary) {
		manager.persist(summary);
	}

	public void createLicenseSummary(LicenseSummary licenseSummary) {
		manager.persist(licenseSummary);
	}

	public void deleteLicense(int id) {
		manager.createQuery("DELETE FROM License l WHERE l.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteSummary(int id) {
		manager.createQuery("DELETE FROM Summary s WHERE s.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteLicenseSummary(int id) {
		manager.createQuery("DELETE FROM LicenseSummary ls WHERE ls.id = :id").setParameter("id", id).executeUpdate();
	}
}
