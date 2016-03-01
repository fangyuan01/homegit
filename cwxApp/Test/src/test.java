public class test {

	public static void main(String[] args) {

		while (true) {

			int[] J1 = { 2, 2, 4, 4, 0 };
			int[] J2 = { 1, 2, 2, 3, 0 };
			int[] J3 = { 4, 2, 3, 1, 0 };
			int[] J4 = { 5, 8, 8, 3, 0 };
			int[] J5 = { 5, 2, 3, 2, 0 };

			// 0A 1B 2C 3D 4空闲
			for (int j1 = 0; j1 < 5; j1++) {
				for (int j2 = 0; j2 < 5; j2++) {
					for (int j3 = 0; j3 < 5; j3++) {
						for (int j4 = 0; j4 < 5; j4++) {
							for (int j5 = 0; j5 < 5; j5++) {
								if (j1 == j2 || j1 == j3 || j1 == j4
										|| j1 == j5 || j2 == j3 || j2 == j4
										|| j2 == j5 || j3 == j4 || j3 == j5
										|| j4 == j5) {
								} else {
									int time = J1[j1] + J2[j2] + J3[j3]
											+ J4[j4] + J5[j5];
									System.out.println("time->" + time);
									System.out.println("J1->" + j1 + " J2->"
											+ j2 + " J3->" + j3 + " J4->" + j4
											+ " J5->" + j5);
								}
							}
						}
					}
				}
			}
		}
	}
}
