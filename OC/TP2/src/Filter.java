import java.util.ArrayList;
import java.util.List;


public class Filter {

	public static List<Integer[]> filtre (List<Integer[]> points) {
		boolean dominated = false;
		ArrayList<Integer[]> nonDominated = new ArrayList<Integer[]>();
		for (Integer[] p : points ) {
			dominated = false;
			for(Integer[] tmp : points) {
				if (p != tmp && !(p[0] > tmp[0]) && !(p[1] > tmp[1])) {
					dominated = true;
					break;
				}
			}
			if (!dominated)
				nonDominated.add(p);
		}
		return nonDominated;
	}
}
