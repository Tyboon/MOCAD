import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Main {

	public static void main(String[] args) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query("Noël");
		query.count(100);
		query.lang("fr");
		QueryResult result = twitter.search(query);
		System.out.println(result.getTweets().size());
		List<String> tweets = new ArrayList<String>();
		for (Status status : result.getTweets() ) {
			tweets.add(status.getText());
			
			System.out.println(status.getText()+ '\n');
		}
	}
}
