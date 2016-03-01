package fangyuan;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CompareToTest {
	public static void main(String args[]) {
		Name a = new Name("yuan", "fang");
		Name b = new Name("Jessie", "yuan");
		List l1 = new LinkedList();
		l1.add(new Name("xuan", "xuan"));
		l1.add(a);
		l1.add(b);
		Collections.sort(l1);
		System.out.println(l1);
	}
}
