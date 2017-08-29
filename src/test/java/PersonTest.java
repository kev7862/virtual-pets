import org.junit.*;
import static org.junit.Assert.*;

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

}
