import java.util.ArrayList;
import java.util.List;
import org.sq12o.*;

public class Monster {
  private String name;
  private int personId;
  private int id;

  public Monster(String name, int personId) {
    this.name = name;
    this.personId = personId;
  }
  public String getName() {
    return name;
  }

  public int getPersonId() {
    return personId
  }

  public int getId() {
    return id;
  }

  @Override
protected void after() {
  try(Connection con = DB.sql2o.open()) {
    String deletePersonsQuery = "DELETE FROM persons *;";
    String deleteMonstersQuery = "DELETE FROM monsters *;";
    con.createQuery(deletePersonsQuery).executeUpdate();
    con.createQuery(deleteMonstersQuery).executeUpdate();
  }
}

public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO monsters (name, personid) VALUES (:name, :personId)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("personId", this.personId)
      .executeUpdate()
      .getKey();
  }
}

public static List<Monster> all() {
    String sql = "SELECT * FROM monsters";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Monster.class);
    }
  }
// Trying to make connection to database and find each monster by its specific id.
  public static Monster find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM monsters where id=:id";
    Monster monster = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Monster.class);
    return monster;
  }
}
}
