package fangyuan;

public class MaxArray {
	int findMax(int a[], int length) {
		int Max = a[0];
		for (int j = 0; j < length; j++) {

			if (a[j] > Max)
				Max = a[j];

		}
		return Max;
	}

}
