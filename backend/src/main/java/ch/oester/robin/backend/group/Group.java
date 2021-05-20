package ch.oester.robin.backend.group;

import ch.oester.robin.backend.club.Club;
import ch.oester.robin.backend.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUPS")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="club_id", referencedColumnName = "id")
  private Club club;

  @Column(length = 50, nullable = false)
  private String name;

  @ManyToMany(mappedBy = "groups")
  private List<User> users;

  private boolean locked;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Club getClub() {
    return club;
  }

  public void setClub(Club club) {
    this.club = club;
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

  public List<User> getUsers() {
    return users;
  }
}
