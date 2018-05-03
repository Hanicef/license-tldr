
package yasp.group.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="summary")
public class Summary implements Serializable {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false)
   private int id;

   @Column(name="name", length=64)
   private String name;

   @Column(name="description", length=256)
   private String description;

   public Summary() {
   }

   public Summary(int id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
   }

   public int getID() { return this.id; }
   public String getName() { return this.name; }
   public String getDescription() { return this.description; }

   public void setID(int id) { this.id = id; }
   public void setName(String name) { this.name = name; }
   public void setDescription(String description) { this.description = description; }
}
