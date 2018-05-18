
package yasp.group.entity;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="licenseSummary")
public class LicenseSummary implements Serializable {
	@Id
	@JoinColumn(table="license",columnDefinition="id")
	private int license;

	@Id
	@JoinColumn(table="summary",columnDefinition="id")
	private int summary;

	public LicenseSummary() {
	}

	public LicenseSummary(int license, int summary) {
		this.license = license;
		this.summary = summary;
	}

	public int getLicense() { return this.license; }
	public int getSummary() { return this.summary; }

	public void setLicense(int license) { this.license = license; }
	public void setSummary(int summary) { this.summary = summary; }
}
