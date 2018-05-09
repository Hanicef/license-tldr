
package yasp.group.entity;

import javax.persistence.*;
import java.util.List;
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

	@Column(name="sourceURL", length=256)
	private String sourceURL;

	public License() {
	}

	public License(String name, String sourceURL) {
		this.name = name;
		this.sourceURL = sourceURL;
	}

	public int getId() { return this.id; }
	public String getName() { return this.name; }
	public String getSourceURL() { return this.sourceURL; }

	public void setName(String name) { this.name = name; }
	public void setSourceURL(String sourceURL) { this.sourceURL = sourceURL; }
}
