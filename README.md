
# Spring Container
Хранит в себе *beans* (информацию об объектах), которые получает через настройки конфигурации

**Spring bean** (бин) - это объект, который создаётся и управляется контейнером

**Основные функции контейнера:**
* Inversion of Control (IoC) - инверсия управления
* Dependency Indjection (DI) - внедрение зависимостей

*IoC* - это аутсорсинг создания и управления объектами. 
Т.е. передача программистом прав на создание и управление объектами Spring-у  

**Способы конфигурации контейнера:**
* XML file (устаревший способ)
* Annotations + XML file 
* Java code 

## Конфигурация контейнера через XML-file
#### ex03
Для создания бинов, нам необходим конфигурационный файл 
с предварительными настройками

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd">

    <!--  Здесь наши конфигурации  -->

</beans>
```
Создадим бин имплиминтирующий домашнее животное
```aidl
<!--  applicationContext.xml  -->
<!--  Создание bean  -->
<!--  id - идентификатора(имя) бина, 
      class - полное имя класса (не может быть интерфейсом)  -->
    
<bean id = "myPet" class = "ex1.Dog">

</bean>
```

Для получения бинов нам нужен контейнер, и им является *ApplicationContext*.
Поэтому в приложении мы создаём экземпляр `ClassPathXmlApplicationContext`

```agsl
// создаём контейнер для бинов
ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext_ex03.xml");
// прописываем путь к файлу конфигураций (находится в resources)

// Получаем бин по идентификатору, указанному файлу.
Pet pet = context.getBean("myPet", Pet.class);
pet.say();
// Можем получить класс, из которого создавался бин
Dog dog = context.getBean("myPet", Dog.class);
dog.say();

// закрываем контекст
context.close();
```

#### ex04
Создаём класс `Person`, который хранит экземпляр `Pet`
и обращается к его методам.

```agsl
// Классический пример создания объектов
Pet pet = new Dog();
Person person = new Person(pet);
person.callYourPet();
```

```agsl
// Пример с использованием контекста
Pet petBean = context.getBean("myPet", Pet.class);

// В качестве аргумента передаём полученный из контекста бин
Person person1 = new Person(petBean);
person1.callYourPet();
```

### Dependency Indjection
DI - аутсорсинг добавления/внедрения зависимостей. 
DI делает объекты нашего приложения слабо зависимыми друг от друга

Способы внедрения зависимостей:
* С помощью конструктора
* С помощью сеттеров 
* С помошью аннотации `@Autowiring` (Попозже)

#### Внедрение зависимости с помощью конструктора

```aidl
<!--  applicationContext.xml  -->
<!--  Создаём bean с примером DI  -->
<bean id ="myPerson"
    class="ex04.Person">
    
    <!--   Передаём в конструктор id бинa домашнего животного  -->
    <constructor-arg ref="myPet"/>        
</bean>
```

```agsl
// Создаём Person из бина, в который автоматически подставится
// бин Pet из контейнера
Person personBean = context.getBean("myPerson", Person.class);
personBean.callYourPet();
```
Изменив бин `myPet` с `Dog` на `Cat`, у `Person` автоматически 
в качестве домашнего животного будут подставляться `Cat`

#### Внедрение зависимости с помощью сеттера
#### ex05
Добавляем в `Person` поля   
```agsl
  private String surname;
  private int age;
```
Добавляем к ним геттеры и сеттеры.  
Переопределяем `toString()`.  
Конструктор нам сейчас не нужен, удаляем.  
Редактируем конфигурационный файл.
```aidl
<!--  applicationContext.xml  -->
<!--  Создаём bean с примером DI через сеттер. -->
<!--  Установка значений объекта через сеттеры  -->
<bean id ="myPerson"
    class="ex04.Person">

    <!-- Установка поля Pet через сеттер. 
    ССылка на бин, как и с конструктором -->
    <property name="pet" ref="myPet"/>
    
    <!-- Установка значений через сеттеры. 
    Убирается set и следующая буква делается строчной  -->
    <property name="surname" value="Shkaev"/>
    <property name="age" value="35"/>       

</bean>
```
Создаём человека  
```agsl
// Классический пример
Pet pet = new Dog();
Person person1 = new Person();
person1.setPet(pet);
person1.setSurname("Shkaev");
person1.setAge(35);
System.out.println(person1);
    
System.out.println("---------");

// Пример DI
ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext_ex05.xml");

Person person2 = context.getBean("myPerson", Person.class);
System.out.println(person2);

context.close();
```
**Подстановка значений из внешнего файла**
#### ex06
Редактируем файл конфигураций. А код остаётся тем же.
```aidl
<!--  applicationContext.xml  -->

<!--  Прописываем путь к файлу со свойствами  -->
<context:property-placeholder location="classpath:ex06.properties"/>

<!--  Пропущен код  -->

<bean id ="myPerson"
    class="ex06.Person">    
    <property name="pet" ref="myPet"/>
    
    <!--  В качестве значений используем переменный из файла  -->
    <property name="surname" value="${person.surname}"/>
    <property name="age" value="${person.age}"/>
</bean>
```
#### Bean scope
Scope (область видимости) определяет:
* жизненный цикл бина
* возможное количество создаваемых бинов  

Разновидности bean scope:
* `singletone` (по умолчанию) - разрешает создать только один объект,   
остальные будут копиями даже если создавались через фабрику или `new`
* `prototype` - каждый объект созданный через бин будет уникальным.
  * такой бин создаётся только после обращения к контейнеру через `getBean`
  * для каждого такого обращения создаётся новый бин в контейнере
* другие (об этом позже)

```aidl
<!--  applicationContext.xml  -->
<bean id = "myPet"
    class = "ex06.Dog"
    scope="prototype">
</bean>
```

#### init-method and destroy-method
Жизненный цикл бина
1. Запуск приложения 
2. Начало работы Spring Container
3. Создание бина
4. DI внедряются зависимости
5. **init-method** 
6. Бин готов для использования
7. Использование нами этого бина
8. Конец работы Spring Container
9. **destroy-method**
10. Остановка приложения

**Пример**
```agsl
public interface Pet {
  void say();
  void init();
  void destroy();
}
```
```aidl
<bean id = "myPet"
    class = "ex07.Dog"
    init-method="init"
    destroy-method="destroy">
</bean>
```
* Название у методов может быть любым.
* Методы не принимают параметров.
* Модификатор доступа у методов может быть любым.
* return type может быть любым, но из-за того,
что возвращаемое значение мы никак не можем использовать,
чаще всего return type - это void.
* Чаще всего **init-method** используется для открытия
или настройки каких-либо ресурсов, например БД, стримов и т.д.
* **destroy-method** чаще всего используется для их закрытия
* Для бинов со `scope="prototype"` 
  * **destroy-metod** вызываться не будет. Программисту необходимо самостоятельно 
  писать код для закрытия/освобождение русурсов, которые были использованы в бине.
  * **init-method** будет вызываться каждого новосозданного бина


## Конфигурация с помощью Annotations + XML file
*Аннотации* - это специалые комментарии/метки/метаданные, 
которые нужны для передачи определённой информации.

Конфигурация с помощью аннотаций более короткий и быстрый способ, 
чем конфигурация с помощью XML файла.

Процесс состоит из 2-х этапов:
1. Сканирование классов и поиск аннотаций `@Сomponent`
2. Создание (регистрация (`prototype`)) бина в Spring Container-е

#### ex08
Редактируем файл конфигураций, указывая пакет, в котором надо искать бины.
```aidl
<!--  cканирование/поиск классов на наличие аннотаций @Component  -->
<context:component-scan base-package="annotations_and_xml/classes"/>
```
Над классами бинов вешаем аннотацию `@Component`.
```agsl
@Component("catBean")
public class Cat implements Pet {
  /*...*/
}
```
В остальном без изменений. Бины созданются также через `ClassPathXmlApplicationContext`
```agsl
String path = "08";
ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext(
                String.format("annotations_and_xml/applicationContext_ex%s.xml", path));
Cat cat = context.getBean("catBean", Cat.class);
cat.say();
```
Если к аннотации `@Component` не прописать bean id, то бину будет назначен дефолтный id.  
Дефолтный bean id получается из имени класса, заменяя его первую заглавную букву на прописную.

Исключение - 2+ заглавных букв в имени класса. Тогда бин будет таким же как имя класса.
```agsl
@Component
class SQLTest{ }
```
#### Аннотация `@Autowired`
#### ex09
Для внедрения зависимостей используется аннотация `@Autowired`

Аннотация может быть использована на:
* Конструкторе (Если в классе один конструктор, то аннотацию можно опустить)
* Сеттере
* Поле

```agsl
// Пример использования аннотции на поле
@Autowired
private Pet pet;
```
Процесс внедрения зависимостей при использовании `@Autowired` такой:
1. Сканирование пакета, поиск классов с аннотацией `@Component`
2. При наличии аннотации `@Autowired` начинается поиск подходящего по типу бина
   * Если находится 1 подходящий бин, происходит внедрение зависимости;
   * Если подходящих по типу бинов нет, то выбрасывается исключение: `expected at least 1 bean which qualifies as autowire candidate`;
   * Если подходящих по типу бинов больше одного, тоже, но с другим текстом: `expected single matching bean but found 2: catBean,dogBean`.

#### Аннотация `@Qualifier`
Если сканирование нашло большего одного подходящего бина, то мы можем использовать
аннотациаю `@Qualifier("bean_id")`. И в поле будет подставлен бин с указанным `id`  
```agsl
// В конструкторе ставить надо перед нужным параметром
@Autowired
public Person(@Qualifier("dogBean") Pet pet) {
  this.pet = pet;
}

// у сеттера и поля над ними
@Autowired
@Qualifier("dogBean")
private Pet pet;

@Autowired
@Qualifier("dogBean")
public void setPet(Pet pet) {
  this.pet = pet;
}
```
#### Аннотация `@Value`
Для внедрения строк и других значений можно использовать аннотацию `@Value`  
В этом случа в сеттерах нет необходимости, 
как это было при конфигурации с помощью XML файла.
```agsl
@Value("Shkaev")
private String surname;
@Value("35")
private int age;
```
Мы можем подставлять значения из внешнего файл.  
Дабавим в наш конфигурационный файл строку:
```aidl
<!--  путь к файлу со значенями  -->
<context:property-placeholder location="classpath:myApp.properties"/>
```
Теперь в аннотацию нужно подставить значения из файла
```agsl
 @Value("${person.surname}")
private String surname;
@Value("${person.age}")
private int age;
```
#### Аннотация `@Scope`
Если мы не хотим, чтобы наш бин был со значением "singletone",
то нам надо повесить на класс аннотацию `@Scope("prototype")`
```agsl
@Scope("prototype")
public class Cat implements Pet { ... }
```
#### Аннотации `@PostConstruct` и `@PreDestroy`
Эти аннотации вешаются на те методы, которые мы хотим пометить
как **init-method** и **destroy-method**.

Аннотации считаются устаревшими, и если мы хотим их использовать, 
нам необходимо скачать **javax.annatation-api JAR 1.3.2**


## Конфигурация Spring Container-а с помощью Java кода (только аннотации)
#### ex10

### Способ первый.
Для настроек конфигураций, нам необходим класс с аннотацией `@Configuraton`
```agsl
@Configuration // класс является конфигурацией
@ComponentScan("only_annotations/classes") // пакет с компонентами
public class MyConfig {
}
```
Вместо `ClassPathXmlApplicationContext` теперь мы используем
`AnnotationConfigApplicationContext`
```agsl
// Необходимо передать имя нашего кофигурационного класса
AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MyConfig.class);
```

### Способ второй.

* Данный способ не использует сканирование пакета и поиск бинов.
Поэтому не нужны аннотации `@Component` и `@ComponentScan`
* Данный способ не использует аннотацию `@Autowired`. Здесь
зависимости прописываются вручную в конфиг классе.
* Название метода - это bean id.
* Аннотация `@Bean` перехватывает все обращения к бину и регулирует его создание.

```agsl
@Configuration
@PropertySource("myApp.properties")
public class MyConfig2 {
  @Bean
  @Scope("prototype")
  public Pet catBean(){
    return  new Cat();
  }
  @Bean
  @Scope("prototype")
  public Person personBean(){
    return  new Person(catBean()); // bean id - имя метода
  }
}
```
Для подстановки значений из внешнего файла над классом надо повесить
аннотацию `@PropertySource("myApp.properties")`
(правильнее "classpath:myApp.properties", но так тоже работает)

`@Qualifier` и `@Scope` вешаются над методами-бинами
