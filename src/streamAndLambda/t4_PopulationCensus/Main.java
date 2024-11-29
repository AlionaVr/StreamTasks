package src.streamAndLambda.t4_PopulationCensus;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        findMinors(persons);// поиск несовершеннолетних

        findRecruit(persons); // получение списка призывников

        findWorkingPersons(persons);// получение отсортированного по фамилии списка потенциально работоспособных людей с высшим образованием
    }

    private static void findWorkingPersons(Collection<Person> persons) {
        Stream<String> workingPersonsStream = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex() == Sex.WOMAN ? person.getAge() <= 60 : person.getAge() <= 65)
                .sorted(comparing(Person::getFamily))
                .map(Person::toString);

        List<String> workers = workingPersonsStream.toList(); //.collect(Collectors.toList());

        System.out.println("Количество работников: " + workers.size());
        System.out.println("Первые 10 работников:");
        workers.stream().limit(10).forEach(System.out::println);
    }

    private static void findRecruit(Collection<Person> persons) {
        List<String> family = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .limit(10)
                .toList(); //.collect(Collectors.toList());
        System.out.println("Первые 10 призывников:" + family);
    }

    public static void findMinors(Collection<Person> persons) {
        long count = persons.stream()
                .filter(person -> person.getAge() <= 18)
                .count();
        System.out.println("Количество несовершеннолетних:" + count);
    }

}
