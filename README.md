# Ecommerce Load Test

### Visualization of data

``` 
git clone git@github.com:robsonbittencourt/gatling-scaffold.git
cd gatling-scaffold
docker-compose up 
```
The docker-compose of this project will build containers with [InfluxDB](https://www.influxdata.com/time-series-platform/influxdb/) and [Grafana](https://grafana.com/) that will allow us to visualize the simulation data during its execution.

After the build and creation of the containers access http://localhost:3000. There is already a dashboard called Gatling Report.

### Execution

```
docker run -it --rm --net="host" \
    -v "$PWD"/src/main/resources:/opt/gatling/conf \
    -v "$PWD"/src/main/scala:/opt/gatling/user-files \
    -v "$PWD"/build/reports/gatling:/opt/gatling/results \
    denvazh/gatling:3.0.3 -s simulations.BasicSimulation
```