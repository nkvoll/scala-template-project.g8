import com.typesafe.config.ConfigFactory

class Bootstrap(config = ConfigFactory.load()) {

}

object Bootstrap extends App {
  new Bootstrap().run()
}