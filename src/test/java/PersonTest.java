import org.junit.*;
import static org.junit.Assert.*;
import org.sq120.*;

public class PersonTest {

@Test
public void person_InstantiatesCorrectly() {
  Person testPerson = new Person ("Henry", "[email protected]");
  assertEquals(true, testPerson instanceof Person);
}

public void getName_InstantiatesWithName_Henry() {
  Person testPerson = new Person("Henry", "[email protected]");
  assertEquals("Henry", testPerson.getName());
}

@Test
public void getEmail_InstantiatesWithEmail_String() {
  Person testPerson = new Person ("Henry", "[email protected]");
  assertEquals(true, testPerson.getEmail());
}

@Test
public void equals_returnsTrueIfNameAndEmailAreSame_true() {
  Person firstPerson = new Person("Henry", "[email protected]");
  Person anotherPerson = new Person("Henry", "[email protected]");
  assertTrue(firstPerson.equals(anotherPerson));
}

@Test
 public void save_insertsObjectIntoDatabase_Person() {
   Person testPerson = new Person("Henry", "[email protected]");
   testPerson.save();
   assertTrue(Person.all().get(0).equals(testPerson));
 }

 public class PersonTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

}
