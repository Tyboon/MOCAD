import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sun.net.httpserver.Authenticator.Failure;


public class Parser {
	
	@SuppressWarnings("null")
	public Instance[] parse(String path, int nb_jobs) {
		Instance[] instances = new Instance[125];
		try{
			InputStream ips = new FileInputStream(path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			
			int cpt = 0;
			int param = 0; //0 = j, 1 = w, 2 = d 
			int inst = 0;
			String[] tmp = null; 
			instances[inst] = new Instance(nb_jobs);
			
			while (((line = br.readLine())!=null) && (cpt < nb_jobs*3*125)) {
			// TQ : pas en fin de fichier 
				
				// cast la ligne
				line = line.replaceAll(" +", " ").trim();				
				tmp = line.split(" ");
				
				for (int i = 0; i< tmp.length; i++) {
				// FOR : chaque valeur
					
					if ((cpt%nb_jobs) != 0 && (inst <= 124)) { // cas interne d'un paramètre
					// SI : Dans une instance 
						switch (param) {
							case 0 : 
								instances[inst].add_process(cpt%nb_jobs, Integer.parseInt(tmp[i]));
								break;
							case 1 :
								instances[inst].add_weigth(cpt%nb_jobs, Integer.parseInt(tmp[i]));
								break;
							case 2 :
								instances[inst].add_due(cpt%nb_jobs, Integer.parseInt(tmp[i]));
								break;
							default :
								System.out.println("Error : invalid parameter index");
								System.exit(0);
						}
						
					} else if ((cpt%(nb_jobs*3) == 0) && (inst < 124)) { // cas : nouvelle instance sauf à la dernière ligne
						instances[++inst] = new Instance(nb_jobs);
						instances[inst].add_process(cpt%nb_jobs, Integer.parseInt(tmp[i]));
					} else { // cas : changement paramètre
						param = (param+1)%3;
						switch (param) {
						case 0 : 
							instances[inst].add_process(cpt%nb_jobs, Integer.parseInt(tmp[i]));
							break;
						case 1 :
							instances[inst].add_weigth(cpt%nb_jobs, Integer.parseInt(tmp[i]));
							break;
						case 2 :
							instances[inst].add_due(cpt%nb_jobs, Integer.parseInt(tmp[i]));
							break;
						default :
							System.out.println("Error : invalid parameter index");
							System.exit(0);
					}
					}
					cpt++;
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return instances;
	}
}
