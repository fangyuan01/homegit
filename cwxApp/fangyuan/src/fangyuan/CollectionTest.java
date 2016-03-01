package fangyuan;

import java.util.ArrayList;

public class CollectionTest {

	public static void main(String[] args) {
		ArrayList<Name> c = new ArrayList<Name>();
		c.add(new Name("f1", "l1"));
		System.out.println(c);
		System.out.println(c.contains(new Name("f1", "l1")));
		c.remove("hello");
		System.out.println(c);
		for (int j = 0; j < c.size(); j++) {
		}
		c.add(new Name("fang", "yuan"));
		System.out.println(c);
		System.out.println(c.contains(new Name("fang", "yuan")));

	}

}
