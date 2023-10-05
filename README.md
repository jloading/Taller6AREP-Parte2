# TALLER 6: PATRONES ARQUITECTURALES

### Demo del proyecto (por si no corre): https://youtu.be/5xfDRMQtiXI

En este taller se aprenderá a crear instancias EC2 en AWS, crear imágenes compatibles y desplegarlas en la misma.

### Prerrequisitos

- Java: Es un lenguaje de programación de propósito general orientado a objetos, portátil y muy versátil.
- Maven: Es una herramienta que maneja el ciclo de vida del programa.
- Git: Es un sistema de control de versiones distribuido (VCS).
- Docker: Es un proyecto de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software.

### Qué se implementó
- Round Robin: La aplicación consta de dos partes: un cliente web y un servicio REST. El cliente web incluye un campo y un botón, y cuando el usuario envía un mensaje, este se transmite al servicio REST. Luego, la pantalla se actualiza con la información devuelta por el servicio en formato JSON. El servicio REST recibe la cadena y emplea un algoritmo de equilibrio de carga Round Robin para distribuir el procesamiento del mensaje y la obtención de la respuesta entre las tres instancias del servicio.

- MongoDB: El servicio MongoDB es una instancia de MongoDB que se ejecuta en un contenedor de Docker dentro de una máquina virtual de EC2. En este entorno, se almacenan los mensajes enviados por el usuario.
  
- Se implementó la siguiente arquitectura para la aplicación:
<img width="671" alt="Captura de pantalla 2023-10-05 a la(s) 2 06 24 a m" src="https://github.com/jloading/Taller6AREP-App/assets/65261708/43786346-30bb-47bd-bc43-3c2f2a0ee951">


### Instalación

1. Clonar los dos repositorios:

```
https://github.com/jloading/Taller6AREP-App.git
```

```
https://github.com/jloading/Taller6AREP-Service.git
```

2. Se construye el proyecto con Maven

```
mvn package
```

### Corriendo la aplicación

### * Usando Docker:
Ingrese los siguientes comandos en su terminal (en el orden que se muestran)

```
docker network create my_network
```

```
docker run -d -p 36000:4567 --name logger-app --network my_network jloading9/taller6arep-app
```

```
docker run -d -p 36001:4568 --name logger-service1 --network my_network jloading9/taller6-service
```

```
docker run -d -p 36002:4568 --name logger-service2 --network my_network jloading9/taller6-service
```

```
docker run -d -p 36003:4568 --name logger-service3 --network my_network jloading9/taller6-service
```

```
docker run -d -p 27017:27017 -v mongodb:/data/db -v mongodb_config:/data/configdb --name db --network my_network mongo:7.0.2 mongod
```

Luego de ejecutar los comandos correctamente, se ingresa a la dirección

```
http://localhost:36000
```

<img width="677" alt="Captura de pantalla 2023-10-05 a la(s) 2 26 33 a m" src="https://github.com/jloading/Taller6AREP-App/assets/65261708/a92835e8-3749-4ac5-b6a1-73b8df355455">

### * A través de AWS:

Se ingresa a la siguiente dirección:

```
http://ec2-54-92-196-10.compute-1.amazonaws.com:36000/
```

Luego de haber ajustado la configuración de seguridad de la instancia para permitir el paso del tráfico a través de los puertos que requerimos, nos conectamos a la instancia mediante SSH y ejecutamos los comandos que se detallaron anteriormente para poner en marcha las instancias de los recursos que necesitamos utilizar. Una vez completado este proceso, fue posible acceder al servicio a través de la URL proporcionada.

<img width="617" alt="Captura de pantalla 2023-10-05 a la(s) 2 27 01 a m" src="https://github.com/jloading/Taller6AREP-App/assets/65261708/a1cf2562-0b86-4c25-b2d0-1b88ea726fcb">



## Hecho con

* Java: Lenguaje de programación de propósito general con enfoque a el paradigma de orientado a objetos, y con un fuerte tipado de variables.
* Git: Es un sistema de control de versiones distribuido (VCS).
* Maven: Es una herramienta que estandariza la configuración del ciclo de vida del proyecto.
* HTML: Es un lenguaje de marcado que estructura una página web y su contenido.
* JavaScript: Lenguaje de programación que los desarrolladores utilizan para hacer paginas web dinamicas.
* Docker: Es un proyecto de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software, proporcionando una capa adicional de abstracción y automatización de virtualización de aplicaciones en múltiples sistemas operativos.

## Autor

* **Juan Carlos Acosta**

