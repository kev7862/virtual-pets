import java.util.ArrayList;
import java.util.List;
import org.sq12o.*;

public class Monster {
  private String name;
  private int personId;
  private int id;
  private int playLevel;
  private int sleepLevel;
  private int foodLevel;

// Applying constants to represent the maximum and minimum of these levels
  public static final int MAX_FOOD_LEVEL = 3;
  public static final int MAX_SLEEP_LEVEL = 8;
  public static final int MAX_PLAY_LEVEL = 12;
  public static final int MIN_ALL_LEVELS = 0;

  public Monster(String name, int personId) {
    this.name = name;
    this.personId = personId;

    // Instantiating with half-full Levels, that way a user gets to interact with the monster after creation.
    this.playLevel = MAX_PLAY_LEVEL / 2;
    this.sleepLevel = MAX_SLEEP_LEVEL / 2;
    this.foodLevel = MAX_FOOD_LEVEL / 2;
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
//get() method for the constant levels
  public int getPlayLevel() {
    return playLevel;
  }

  public int getSleepLevel() {
    return sleepLevel;
  }

  public int getFoodLevel() {
    return foodLevel;
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
  }
}
// THIS MEANS IF OUR monster'S  FOOD, PLAY, OR SLEEP LEVEL GETS TO O THEY DIE.
public static final int MIN_ALL_LEVELS = 0;

// This method checks if our monster is alive or dead.
public boolean isAlive() {
  if (foodLevel <= MIN_ALL_LEVELS ||
  playLevel <= MIN_ALL_LEVELS ||
  sleepLevel <= MIN_ALL_LEVELS) {
    return false;
  }
  return true;
}
// Confirms the isAlive() method accurately determine when it has died
// Decreases all values by 1 each time it runs
public void depleteLevels(){
    playLevel--;
    foodLevel--;
    sleepLevel--;
  }
// Tests whether the play level has increased beyond the default after the play() method has been called.
  public void play(){
    playLevel++;
  }

}
