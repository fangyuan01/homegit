package fangyuan;

public class Company {
	static String Department = "abcd";
	static String Leader = "xyz";
	public String name;
	private int age;
	static float pay;

	Company() {
		name = " ";
		age = 0;
		pay = 1800;
	}

	public Company(String string, int i, int j) {
		name = string;
		age = i;
		pay = j;
	}

	public static void main(String[] args) {
		Company A;
		A = new Company("wang", 27, 2700);
		Company B = new Company("Zhang", 31, 3500);
		System.out.println("Department is:" + Department + "\t Leader is:"
				+ Leader);
		System.out.println("Name is:" + A.name + "\t age is:" + A.age);
		System.out.println("Name is:" + B.name + "\t age is:" + B.age);
	}
}
