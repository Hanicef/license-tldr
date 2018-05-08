
package yasp.group.service;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;
import yasp.group.entity.*;

@Local
@Stateless
public class Service {

	@PersistenceContext
	private EntityManager manager;

	public List<License> getAllLicenses() {
		List<License> result = manager.createQuery("SELECT license FROM License license").getResultList();
		return result;
	}

	public List<Summary> getAllSummary() {
		List<Summary> result = manager.createQuery("SELECT summary FROM Summary summary").getResultList();
		return result;
	}

	public List<LicenseSummary> getAllLicenseSummary() {
		List<LicenseSummary> result = manager.createQuery("SELECT licenseSummary FROM LicenseSummary licenseSummary").getResultList();
		return result;
	}

	public License getLicenseById(int id) {
		License result = manager.createQuery("SELECT license FROM License license WHERE license.id = :id", License.class).setParameter("id", id).getResultList().get(0);
		return result;
	}

	public Summary getSummaryById(int id) {
		Summary result = manager.createQuery("SELECT summary FROM Summary summary WHERE summary.id = :id", Summary.class).setParameter("id", id).getResultList().get(0);
		return result;
	}

	public LicenseSummary getLicenseSummaryById(int id) {
		LicenseSummary result = manager.createQuery("SELECT licenseSummary FROM LicenseSummary licenseSummary WHERE LicenseSummary.id = :id", LicenseSummary.class).setParameter("id", id).getResultList().get(0);
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
		manager.createQuery("DELETE FROM License license WHERE license.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteSummary(int id) {
		manager.createQuery("DELETE FROM Summary summary WHERE summary.id = :id").setParameter("id", id).executeUpdate();
	}

	public void deleteLicenseSummary(int id) {
		manager.createQuery("DELETE FROM LicenseSummary licenseSummary WHERE licenseSummary.id = :id").setParameter("id", id).executeUpdate();
	}
}
