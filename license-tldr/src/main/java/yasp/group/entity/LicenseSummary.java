
package yasp.group.entity;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="licenseSummary")
public class LicenseSummary implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;

	@JoinColumn
	private License license;

	@Column(name="summary")
	@OneToMany(mappedBy="id")
	private List<Summary> summary;

	public LicenseSummary() {
	}

	public LicenseSummary(License license, List<Summary> summary) {
		this.license = license;
		this.summary = summary;
	}

	public License getLicense() { return this.license; }
	public List<Summary> getSummary() { return this.summary; }
	public void setLicense(License license) { this.license = license; }
	public void setSummary(List<Summary> summary) { this.summary = summary; }
}
