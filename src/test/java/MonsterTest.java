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
// Testing to find monsters by id.
 @Test
public void find_returnsMonsterWithSameId_secondMonster() {
  Monster firstMonster = new Monster("Bubbles", 1);
  firstMonster.save();
  Monster secondMonster = new Monster("Spud", 3);
  secondMonster.save();
  assertEquals(Monster.find(secondMonster.getId()), secondMonster);
}

//Testing one to many relationship between Person and Monster class.
@Test
public void save_savesPersonIdIntoDB_true() {
Person testPerson = new Person ("Henry", "henry@henry.com");
testPerson.save();
Monster testMonster = new Monster ("Bubbles", testPerson.getId());
testMonster.save();
Monster savedMonster = Monster.find(testMonster.getId());
assertEquals(savedMonster.getPersonId(), testPerson.getId());
}
//instantiating with half full playLevel.
@Test
  public void monster_instantiatesWithHalfFullPlayLevel(){
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2));
  }
//instatiating with half full sleep level
  @Test
 public void monster_instantiatesWithHalfFullSleepLevel(){
   Monster testMonster = new Monster("Bubbles", 1);
   assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2));
 }
// Instatiating with half-full foodLevel.
 @Test
 public void monster_instantiatesWithHalfFullFoodLevel(){
   Monster testMonster = new Monster("Bubbles", 1);
   assertEquals(testMonster.getFoodLevel(), (Monster.MAX_FOOD_LEVEL / 2));
 }
// Running a test method to check if our monster is alive or dead.
 @Test
 public void isAlive_confirmsMonsterIsAliveIfAllLevelsAboveMinimum_true(){
   Monster testMonster = new Monster("Bubbles", 1);
   assertEquals(testMonster.isAlive(), true);
 }
// Confirms the isAlive() method accurately determine when it has died
// Decreases all values by 1 each time it runs
 @Test
 public void depleteLevels_reducesAllLevels(){
   Monster testMonster = new Monster("Bubbles", 1);
   testMonster.depleteLevels();
   assertEquals(testMonster.getFoodLevel(), (Monster.MAX_FOOD_LEVEL / 2) - 1);
   assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2) - 1);
   assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2) - 1);
 }

 @Test
public void isAlive_recognizesMonsterIsDeadWhenLevelsReachMinimum_false(){
  Monster testMonster = new Monster("Bubbles", 1);
  for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_FOOD_LEVEL; i++){
    testMonster.depleteLevels();
  }
  assertEquals(testMonster.isAlive(), false);
}
// Method that allows Users interact with their pets.
@Test
 public void play_increasesMonsterPlayLevel(){
   Monster testMonster = new Monster("Bubbles", 1);
   testMonster.play();
   assertTrue(testMonster.getPlayLevel() > (Monster.MAX_PLAY_LEVEL / 2));
 }

}
