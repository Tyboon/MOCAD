import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;


public class Streaming {
	
	public static List<String> tweets = new ArrayList<String>();
	public static Writer writer = null ;
	
	public static void main(String[] args) throws TwitterException, IOException{
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dico.txt"),"utf-8"));
	    StatusListener listener = new StatusListener() {
	        @SuppressWarnings("resource")
			public void onStatus(Status status) {
	            String st = status.getText() + "\n";
	            Scanner sc = new Scanner(System.in);
	            System.out.println(st + " ?");
	            String s = sc.nextLine();
	            switch (s) {
	            case "+" :
	            	try {
						writer.write("+ " + st);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	            case "-" :
	            	try {
						writer.write("- " + st);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	            case "_" :
	            	try {
						writer.write("_ " + st);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	            case "x" :
	            	try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            default :
	            	break;
	            }
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
	    };
	    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    twitterStream.addListener(listener);
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitterStream.sample("fr");
	}
}
