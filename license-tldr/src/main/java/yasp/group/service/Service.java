
package yasp.group.service;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import yasp.group.entity.*;

@Local
@Stateless
public class Service {

	@Inject
	private DataAccess dao;

	public List<License> getAllLicenses() {
		return dao.getAllLicenses();
	}

	public List<Summary> getAllSummaries() {
		return dao.getAllSummaries();
	}

	public License getLicenseById(int id) {

		License license = dao.getLicenseById(id);
		if (license == null) throw new ServiceException("license", id, "Row not found");
		return license;
	}

	public Summary getSummaryById(int id) {
		Summary summary = dao.getSummaryById(id);
		if (summary == null) throw new ServiceException("summary", id, "Row not found");
		return summary;
	}

	public List<Summary> getSummariesFromLicense(License license) {
		return this.getSummariesFromLicense(license.getId());
	}

	public List<Summary> getSummariesFromLicense(int id) {
		List<LicenseSummary> list = dao.getAllLicenseSummaries();

		List<Summary> result = new ArrayList<Summary>();
		for (int i = 0; i < list.size(); i++) {
			// Foreign key: will not cause IndexOutOfBoundsException.
			result.add(dao.getSummaryById(list.get(i).getSummary()));
		}
		return result;
	}

	public List<License> getLicensesFromSummary(int id) {
		List<LicenseSummary> list = dao.getAllLicenseSummaries();

		List<License> result = new ArrayList<License>();
		for (int i = 0; i < list.size(); i++) {
			// Foreign key: will not cause IndexOutOfBoundsException.
			result.add(dao.getLicenseById(list.get(i).getLicense()));
		}
		return result;
	}

	public void createLicense(License license) {
		dao.createLicense(license);
	}

	public void createSummary(Summary summary) {
		dao.createSummary(summary);
	}

	public void addSummaryToLicense(License license, Summary summary) {
		dao.createLicenseSummary(new LicenseSummary(license.getId(), summary.getId()));
	}

	public void createLicenseFromSummaries(License license, List<Summary> summaries) {
		dao.createLicense(license);
		for (int i = 0; i < summaries.size(); i++) {
			dao.createLicenseSummary(new LicenseSummary(license.getId(), summaries.get(i).getId()));
		}
	}

	public void applyLicenseChanges(License license) {
		dao.applyLicenseChanges(license);
	}

	public void applySummaryChanges(Summary summary) {
		dao.applySummaryChanges(summary);
	}

	public void deleteLicense(License license) { this.deleteLicense(license.getId()); }

	public void deleteLicense(int id) {
		// Remove all licenseSummaries that corresponds with the to-be-deleted license first.
		dao.deleteLicenseConnections(id);
		dao.deleteLicense(id);
	}

	public void deleteSummary(Summary summary) { this.deleteSummary(summary.getId()); }

	public void deleteSummary(int id) {
		// Remove all licenseSummaries that corresponds with the to-be-deleted summary first.
		dao.deleteSummaryConnections(id);
		dao.deleteSummary(id);
	}
}
