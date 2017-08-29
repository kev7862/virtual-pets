import java.util.ArrayList;
import java.util.List;
import org.sq12o.*;

public class Person {
  private String name;
  private String email;
  private int id;

  public Person(String name, String email, int id) {
    this.name = name;
    this.email = email;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
   return id;
 }

  @Override
  public boolean equals(Object otherPerson){
    if (!(otherPerson instanceof Person)) {
      return false;
    } else {
      Person newPerson = (Person) otherPerson;
      return this.getName().equals(newPerson.getName()) &&
             this.getEmail().equals(newPerson.getEmail());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO persons (name, email) VALUES (:name, :email)";
      con.createQuery(sql)
        .addParameter("name", this.name)
        .addParameter("email", this.email)
        .executeUpdate();
    }
  }

  public static List<Person> all() {
   String sql = "SELECT * FROM persons";
   try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Person.class);
   }
 }

 public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO persons (name, email) VALUES (:name, :email)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("email", this.email)
      .executeUpdate()
      .getKey();
  }
}

public static Person find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM persons where id=:id";
     Person person = con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Person.class);
     return person;
   }
 }
}
