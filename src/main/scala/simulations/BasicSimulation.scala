package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http.baseUrl("http://k8s-ecommerc-ingresse-3643cfd7de-358455931.us-west-2.elb.amazonaws.com")

  val scenario1 = scenario("Create order")
    .exec(
      http("Create order")
        .post("/order-receiver/")
        .body(
          StringBody(
            """
            {
              "clientId": "123",
              "paymentPlan": "12x",
              "products": "123, 456, 789"
            }
            """
          )
        ).asJson
      )

  val scenario2 = scenario("Get product")
    .exec(http("Get product").get("/productviewer/product/2"))  

  setUp(
    scenario1.inject(constantUsersPerSec(20) during(30 minutes)).protocols(httpProtocol)
  )

}
