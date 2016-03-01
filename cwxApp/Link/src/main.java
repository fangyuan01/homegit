public class main {

	public static void main(String[] args) {
		link mlink = new link(-1);
		for (int i = 0; i < 100; i++) {
			link link = new link(i);
			link.next = mlink;
			mlink = link;
		}
		System.out
				.println(mlink.next.next.next.next.next.next.next.next.next.next.value);
	}

}
