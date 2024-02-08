package only_xml.classes;

public class Person {
  private Pet pet;
  private String surname;
  private int age;

  public Person() {
  }
  public Person(Pet pet) {
    this.pet = pet;
  }

  public Person(Pet pet, String surname, int age) {
    this.pet = pet;
    this.surname = surname;
    this.age = age;
  }
  public void callYourPet(){
    System.out.println("Hello, my Pet!");
    pet.say();
  }

  @Override
  public String toString() {
    return "Person{" +
            "pet=" + pet +
            ", surname='" + surname + '\'' +
            ", age=" + age +
            '}';
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
