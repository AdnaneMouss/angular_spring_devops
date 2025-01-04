terraform {
  required_providers {
    docker = {
      source = "kreuzwerker/docker"
      version = "~> 2.0"
    }
  }
}

provider "docker" {}

# Définir un réseau Docker pour les conteneurs
resource "docker_network" "app_network" {
  name = "app_network"
}

# Créer un conteneur pour l'application Angular
resource "docker_container" "angular" {
  name  = "angular"
  image = "node:14"
  ports {
    internal = 4200
    external = 4200
  }
  volumes {
    host_path      = "${path.cwd}/angular"
    container_path = "/usr/src/app"
  }
  networks_advanced {
    name = docker_network.app_network.name
  }
  command = ["sh", "-c", "npm install && npm start"]  # Installer d'abord les dépendances, puis démarrer Angular
  depends_on = [docker_container.mysql_db]  # Assurer que MySQL est lancé avant Angular
}

# Variable pour le mot de passe root de MySQL
variable "mysql_root_password" {
  description = "Le mot de passe root de MySQL"
  type        = string
  sensitive   = true
}

# Créer un conteneur pour MySQL
resource "docker_container" "mysql_db" {
  name  = "mysql_dbb"
  image = "mysql:8.0"

  env = [
    "MYSQL_ROOT_PASSWORD=${var.mysql_root_password}",
    "MYSQL_DATABASE=inventory"
  ]

  ports {
    internal = 3306
    external = 3306  # Assurez-vous que ce port est disponible sur votre machine
  }

  networks_advanced {
    name = docker_network.app_network.name
  }

  volumes {
    host_path      = "${path.cwd}/mysql-data"
    container_path = "/var/lib/mysql"
  }
}
