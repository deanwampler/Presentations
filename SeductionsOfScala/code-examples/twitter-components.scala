trait Tweeter {
  def tweet(message: String)
}

trait TwitterClientUIComponent { 
  val ui: TwitterClientUI
  
  abstract class TwitterClientUI(val client: Tweeter) { 
    def sendTweet(message: String) = client.tweet(message)
    def showTweet(message: String): Unit 
  }
}

trait TwitterServiceComponent {
  val service: TwitterService
  
  trait TwitterService { 
    def sendTweet(message: String): Boolean
  } 
}

trait TwitterClientComponent { 
  self: TwitterClientUIComponent with
        TwitterServiceComponent =>

  val client: TwitterClient

  class TwitterClient(val user: String) 
      extends Tweeter { 
    def tweet(message: String) = {
      if (service.sendTweet(message)) {
        ui.showTweet(message)
      }
    }
  }
}
