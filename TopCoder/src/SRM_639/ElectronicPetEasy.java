package SRM_639;

public class ElectronicPetEasy {

	public String isDifficult(int st1, int p1, int t1, int st2, int p2, int t2) {
		while (t1 > 0 && t2 > 0) {
			if (st1 == st2) {
				return "Difficult";
			}
			if (st1 < st2) {
				st1 += p1;
				t1--;
			} else {
				st2 += p2;
				t2--;
			}
		}

		return "Easy";
	}
}
