import com.typesafe.config.{Config, ConfigFactory}

class Bootstrap(config: Config = ConfigFactory.load()) {
  def run() {

  }
}

object Bootstrap extends App {
  new Bootstrap().run()
}