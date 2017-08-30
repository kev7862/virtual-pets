import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class MonsterTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

@Test
public void monster_instantiateCorrectly_true() {
  Monster testMonster = new Monster("Bubbles", 1);
  assertEquals(true, testMonster instanceof Monster);
}

@Test
public void Monster_instatiatesWithName_String() {
  Monster testMonster = new Monster("Bubbles", 1);
  assertEquals("Bubbles", testMonster.getName());
}

@Test
public void Monster_instatiatesWithPersonId_int() {
  Monster testonster = new Monster("Bubbles", 1);
  assertEquals(1, testMonster.getPersonId());
}

@Test
Public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
  monster testMonster = new Monster("Bubbles", 1);
  Monster anotherMonster = new Monster("Bubbles", 1);
  assertTrue(testMonster.equals(anotherMonster));
}

@Test
public void save_returnsTrueIfDescriptionsAretheSame() {
  Monster testMonster = new Monster("Bubbles", 1);
  testMonster.save();
  assertTrue(Monster.all().get(0).equals(testMonster));
}
//Testing the monsters id Attribute.
@Test
public void save_assignsIdToMonster() {
  Monster testMonster = new Monster("Bubbles", 1);
  testMonster.save();
  Monster savedMonster = Monster.all().get(0);
  assertEquals(savedMonster.getId(), testMonster.getId());
}

//Using an all() to test the two instances of monsters.
@Test
 public void all_returnsAllInstancesOfMonster_true() {
   Monster firstMonster = new Monster("Bubbles", 1);
   firstMonster.save();
   Monster secondMonster = new Monster("Spud", 1);
   secondMonster.save();
   assertEquals(true, Monster.all().get(0).equals(firstMonster));
   assertEquals(true, Monster.all().get(1).equals(secondMonster));
 }

}
