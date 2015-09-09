import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Parser {

	
	@SuppressWarnings("null")
	public int[][] parse(String path, int gap) {
		int[][] tab = null;
		try{
			
			InputStream ips = new FileInputStream(path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			String[] tmp = null;
			int cpt = 0;
			int l = 0;
			int[] k = {0,0,0};
			
			
			while ((line = br.readLine())!=null) {
				line = line.replaceAll(" +", " ");				
				tmp = line.split(" ");
				
				for (int i = 0; i< tmp.length; i++) {
					cpt ++;
					
					if (cpt < gap) {
						tab[l][k[l]] = Integer.parseInt(tmp[i]);
						k[l]++;
					} else {
						l = (l+1)%3 ;
						cpt = 0 ; 
						tab[l][k[l]] = Integer.parseInt(tmp[i]);
						k[l]++;
					}
				}
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return tab;
	}
}
