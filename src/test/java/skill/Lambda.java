package skill;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by lipeng on 15/6/25.
 */
public class Lambda {


    class Person {
        public String name;
        public int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public void predicate(List<Person> ps) {
        Predicate<Person> pre_old = e -> e.age > 80;
        ps.stream().filter(pre_old).forEach(e -> System.out.println(e));
        ps.stream().filter(e -> e.age > 80).forEach(e -> System.out.println(e));
    }

    public void function(List<Person> ps) {
        Function<Person, Boolean> f_old = e -> e.age > 80;
        Function<Person, Boolean> f_oldx = e -> {
            return e.age > 80;
        };
        ps.stream().map(f_old);
    }

    public Callable<String> closure(String name) {
        String hello = "Hello";
        return () -> (hello + ", " + name);
    }

    public void comparator(List<Person> ps) {
        Collections.sort(ps, Comparator.comparing((Person p) -> p.age));
        //降序reversed
        Collections.sort(ps, Comparator.comparing(Person::getAge).reversed());
        ps.sort(Comparator.comparing(Person::getAge));
    }

    public void map(List<Person> ps) {
        int sum = ps.stream().mapToInt(Person::getAge).sum();
    }


    public <T> void functionInvoke(T d, Function<T, String> f) {
        System.out.println(f.apply(d));
    }

    public <T> void predicateInvoke(T d, Predicate<T> p) {
        System.out.println(p.test(d) + "\t" + d);
    }

    @Test
    public void ttetet() {
        List<Integer> l = Lists.newArrayList(1, 4, 5, 23, 5, 3, 33);
        l.sort(Comparator.comparing(e -> e % 3));
        System.out.println(l.stream().mapToInt(e -> e).sum());
        l.forEach(e -> {
//            if (e == 23)
//                return;
            System.out.println(e);
        });
        functionInvoke("sdfsdf", e -> "[fuck]" + e);
        predicateInvoke("sdfs", e -> e.length() > 5);
    }

}
