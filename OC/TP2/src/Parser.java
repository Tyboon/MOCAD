import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Parser {
	
	/***
	 * Parser d'entrée 
	 * @param path
	 * @return list
	 */
	public static int[] parse(String path, int size) {
		int[] tab = new int[size];
		try{
			InputStream ips = new FileInputStream(path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			int cpt = -1;
			int tmp = 0;
			
			while ((line = br.readLine()) != null) {
			// TQ : pas en fin de fichier
				cpt++;
				if (cpt < 7) {
					continue;
				}
				
				// cast la ligne				
				tmp = Integer.parseInt(line);
				tab[cpt-7] = tmp;
			}
			br.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		return tab;
	}
}
