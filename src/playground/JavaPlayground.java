package playground;

/**
 * Created by User on 25/12/2019
 */
public class JavaPlayground {
    public static void main(String[] args)
    {
        //fieldstest();
        anonclasstest();
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