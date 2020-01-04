package playground;

import scala.Char;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by User on 25/12/2019
 */
public class JavaPlayground {
    public static void main(String[] args)
    {
        //fieldstest();
        //anonclasstest();
        combitest();
    }


    static void anonclasstest(){
        Baz baz = new Baz(){
            @Override void baz() { System.out.println("bazza!"); }
        };
        baz.baz();

    }

    static void fieldstest() {
        Foo foo = new Foo();
        Foo bar = new Bar();
        System.out.println(foo.s);
        System.out.println(bar.s);
    }

    static void combitest(){
        List<Integer> ints = Arrays.asList(1,2,3,4);
        List<Character> chars = Arrays.asList('a','b','c','d');
        List<String> anotherList = Arrays.asList("AA", "BB", "CC");
        List<String> combis = ints.stream().flatMap(i -> chars.stream().map(c -> "" + i + "==>" + c)).collect(Collectors.toList());
        List<String> combis2 = ints.stream().flatMap(i -> chars.stream().flatMap(c -> anotherList.stream().map(s->"" + i + "==>" + c + ("(" + s + ")\n")))).collect(Collectors.toList());

        System.out.println(combis);
        System.out.println(combis2);

    }

}

class Foo{
    String s = "abc";
}
class Bar extends Foo{
    String s = "def";
}
class Baz{
    void baz(){ System.out.println("baz"); }
}