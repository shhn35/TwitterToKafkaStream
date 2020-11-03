package de.shhn.kafka;

import twitter4j.*;
import twitter4j.auth.AccessToken;

public class TwitterListener {
    static Twitter twitter;
    public TwitterListener(){
        this.twitter = new TwitterFactory().getInstance();
        setTwiiterAuth();
    }

    private static void setTwiiterAuth(){
        twitter.setOAuthConsumer("cUZRZcxR9ZQfDXd2kaLNfW1Dl","TDZoP1ZUfZ0NNA9bK1Hpi1oBK4wypsPGcOGwmkEQzVegu2XMcy");
        twitter.setOAuthAccessToken(new AccessToken("1310554368815632384-8ObtC4IujdO2fAHVUD0OS1zBFsQMNK","q1ZxtYYV9lNMXLxMaxElbaTCXNdaPJYmalCrlTvZ3419i"));
    }

    public static void main(String[] args) {
        twitter = new TwitterFactory().getInstance();
        setTwiiterAuth();

        try {
            ResponseList<Status> a = twitter.getUserTimeline(new Paging(1,5));
            for (Status b:a){
                System.out.println(b.getText());
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
