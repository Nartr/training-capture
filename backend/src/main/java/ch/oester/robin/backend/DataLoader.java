package ch.oester.robin.backend;

import ch.oester.robin.backend.club.Club;
import ch.oester.robin.backend.club.ClubRepository;
import ch.oester.robin.backend.group.Group;
import ch.oester.robin.backend.group.GroupRepository;
import ch.oester.robin.backend.user.User;
import ch.oester.robin.backend.user.UserRepository;
import ch.oester.robin.backend.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ClubRepository clubRepository;

  @Autowired
  private GroupRepository groupRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Club allBlacks = new Club();
    allBlacks.setName("All Blacks");
    allBlacks.setLocked(false);
    clubRepository.save(allBlacks);

    Club lcr = new Club();
    lcr.setName("LC Regensdorf");
    lcr.setLocked(false);
    clubRepository.save(lcr);

    Group lang = new Group();
    lang.setName("Langstrecken");
    lang.setClub(allBlacks);
    lang.setLocked(false);
    groupRepository.save(lang);

    Group mittel = new Group();
    mittel.setName("Mittelstrecken");
    mittel.setClub(lcr);
    mittel.setLocked(false);
    groupRepository.save(mittel);

    User admin = new User();
    admin.setUsername("Admin");
    admin.setPassword("pegasos");
    admin.setLocked(false);
    admin.setSurname("Robin");
    admin.setLastname("Oester");
    admin.setType(UserType.ADMIN);
    userRepository.save(admin);

    User test = new User();
    test.setUsername("Tester");
    test.setPassword("test");
    test.setLocked(false);
    test.setSurname("Test");
    test.setLastname("Tester");
    test.setType(UserType.ATHLETE);
    userRepository.save(test);

    User u = userRepository.findByUsername("Admin");
    u.getGroups().add(lang);
    u.getGroups().add(mittel);
    userRepository.save(u);

    User t = userRepository.findByUsername("Tester");
    t.getGroups().add(mittel);
    userRepository.save(t);

    System.out.println("Clubs");
    clubRepository.findAll().forEach(club -> {
      System.out.println("- " + club.getName() + " (" + club.getGroups().size() + ")");
    });

    System.out.println("Users");
    userRepository.findAll().forEach(user -> {
      System.out.println("- " + user.getUsername());
      for(Group g : user.getGroups()) {
        System.out.println("  - " + g.getName() + " (" + g.getClub().getName() + ")");
      }
    });
  }
}
