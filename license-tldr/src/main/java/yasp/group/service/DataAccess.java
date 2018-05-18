
package yasp.group.service;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import yasp.group.entity.*;

@Local
@Stateless
public class DataAccess {
	@PersistenceContext
	private EntityManager manager;

	public List<License> getAllLicenses() {
		List<License> result = manager.createQuery("SELECT l FROM License l").getResultList();
		return result;
	}

	public List<Summary> getAllSummaries() {
		List<Summary> result = manager.createQuery("SELECT s FROM Summary s").getResultList();
		return result;
	}

	public List<LicenseSummary> getAllLicenseSummaries() {
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

	public void createLicense(License license) {
		manager.persist(license);
	}

	public void createSummary(Summary summary) {
		manager.persist(summary);
	}

	public void createLicenseSummary(LicenseSummary licenseSummary) {
		LicenseSummary result = manager.createQuery("SELECT ls FROM LicenseSummary ls WHERE ls.license = :lid AND ls.summary = :sid", LicenseSummary.class)
			.setParameter("lid", licenseSummary.license)
			.setParameter("sid", licenseSummary.summary)
			.getResultList();
		if (result.size() == 0) {
			manager.persist(licenseSummary);
		}
	}


	public void applyLicenseChanges(License license) {
		manager.createQuery("UPDATE License l SET l.name = :name, l.sourceURL = :sourceURL WHERE l.id = :id")
			.setParameter("name", license.getName())
			.setParameter("sourceURL", license.getSourceURL())
			.setParameter("id", license.getId())
			.executeUpdate();
	}

	public void applySummaryChanges(Summary summary) {
		manager.createQuery("UPDATE Summary s SET s.name = :name, s.description = :description WHERE s.id = :id")
			.setParameter("name", summary.getName())
			.setParameter("description", summary.getDescription())
			.setParameter("id", summary.getId())
			.executeUpdate();
	}

	public void deleteLicense(int id) {
		manager.createQuery("DELETE FROM License l WHERE l.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteSummary(int id) {
		manager.createQuery("DELETE FROM Summary s WHERE s.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteLicenseSummary(int license, int summary) {
		manager.createQuery("DELETE FROM LicenseSummary ls WHERE ls.license = :lic AND ls.summary = :sum")
			.setParameter("lic", license)
			.setParameter("sum", summary)
			.executeUpdate();
	}

	public void deleteLicenseConnections(int id) {
		manager.createQuery("DELETE FROM LicenseSummary ls WHERE ls.license = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteSummaryConnections(int id) {
		manager.createQuery("DELETE FROM LicenseSummary ls WHERE ls.summary = :id").setParameter("id", id).executeUpdate();
	}
}
