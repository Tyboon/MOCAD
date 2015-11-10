import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	
	/***
	 * Parser d'entrée 
	 * @param path
	 * @return list
	 */
	public static List<Integer> parse(String path) {
		List<Integer> list = new ArrayList<Integer>();
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
				list.add(tmp);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return list;
	}
}
