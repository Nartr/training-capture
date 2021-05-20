package ch.oester.robin.backend.club;

import ch.oester.robin.backend.group.Group;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLUBS")
public class Club {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
  private List<Group> groups;

  private boolean locked;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public List<Group> getGroups() {
    return groups;
  }
}
