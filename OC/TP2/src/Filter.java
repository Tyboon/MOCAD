import java.util.ArrayList;
import java.util.List;


public class Filter {

	/**
	 * Filtre permettant de garder les solutions non-dominées, le tout en offline (avec toutes les données )
	 * @param points les entrées
	 * @return les points non dominés
	 */
	public static List<Integer[]> filtreOff (List<Integer[]> points) { // TOSEE sortie List<int> avec position seulement
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
	
	/**
	 * Filtre permettant de garder les solutions non-dominées, le tout en online (un point à la fois)
	 * @param points les entrées
	 * @return les points non dominés
	 */
	public static List<Integer[]> filtreOn (List<Integer[]> points) {
		ArrayList<Integer[]> archive = new ArrayList<Integer[]>();
		archive.add(points.get(0));
		boolean dominated = false;
		for (Integer[] p : points) {
			dominated = false;
			for (int i = 0; i< archive.size(); i++) {
				Integer[] a = archive.get(i);
				if (p != a && !(p[0] < a[0]) && !(p[1] < a[1])){ // Si a est dominé
					archive.remove(i);
				}
				if (p != a && !(p[0] > a[0]) && !(p[1] > a[1])) {
					dominated = true;
				}
				if (! dominated) {
					archive.add(p);
				}
			}
		}
		return archive;
	}
	
}
