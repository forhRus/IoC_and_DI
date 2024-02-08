package only_annotations.ex1.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {
  @Autowired
  @Qualifier("dogBean")
  private Pet pet;
  @Value("${person.surname}")
  private String surname;
  @Value("${person.age}")
  private int age;

  public Person() {
  }
//  @Autowired
  public Person(Pet pet) {
    this.pet = pet;
  }
//  @Autowired
  public void setPet(Pet pet) {
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

}
