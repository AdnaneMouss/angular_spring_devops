provider "docker" {}
resource "docker_network" "app_network" {
  name = "app_network"
}
resource "docker_container" "angular_app" {
  name  = "angular_app"
  image = "node:14"
  ports {
    internal = 4200
    external = 4200
  }
  volumes {
    host_path      = "./angular-app"
    container_path = "/usr/src/app"
  }
  networks_advanced {
    name = docker_network.app_network.name
  }

  command = "npm start"
}
resource "docker_container" "mysql_db" {
  name  = "mysql_db"
  image = "mysql:5.7"
  env {
    MYSQL_DATABASE = "inventory"
  }
  ports {
    internal = 3306
    external = 3306
  }
  networks_advanced {
    name = docker_network.app_network.name
  }
  volumes {
    host_path      = "./mysql-data"
    container_path = "/var/lib/mysql"
  }
}
