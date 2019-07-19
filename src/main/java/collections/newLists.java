package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class newLists {

    public static void main (String arg[]) {

        var itemsGenerics = List.of("a1","a2","a1","a2",5, 5,true, false ,'a' , 'b', 'a',"");
        itemsGenerics.forEach(System.out::println);

        // Here error, way the list is immutable
        //lista.add("other include");

        System.out.println("unic items");
        var unicElements = Set.copyOf(itemsGenerics);
        unicElements.forEach(System.out::println);

        //Double-brace
        var doubleBrace = new ArrayList() {{
          add("");
          add('a');
          add("gru");
        }};

        doubleBrace.forEach(System.out::println);
    }
}
