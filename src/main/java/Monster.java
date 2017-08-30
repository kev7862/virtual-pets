import org.sq12o.*;

public class Monster {
  private String name;
  private int personId;

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
}
