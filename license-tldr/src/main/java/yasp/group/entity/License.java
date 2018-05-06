
package yasp.group.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="license")
public class License implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;

	@Column(name="name", length=64)
	private String name;

	@ManyToOne
	@JoinColumn
	private Summary summary;

	@Column(name="sourceURL", length=256)
	private String sourceURL;

	public License() {
	}

	public License(int id, String name, Summary summary, String sourceURL) {
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.sourceURL = sourceURL;
	}

	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public Summary getSummary() { return this.summary; }
	public String getSourceURL() { return this.sourceURL; }

	public void setID(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setSummary(Summary summary) { this.summary = summary; }
	public void setSourceURL(String sourceURL) { this.sourceURL = sourceURL; }
}
