# dynamic-form-generation
Generación de formulario dinámicos usando formly, spring boot y postgresql.


# Como usar

## Prerequisitos

* El sistema usa una base de datos en postgres, en caso de no tener postgres installado la menera mas facil de instalarlo es bajando un imagen de docker como la siguiente [https://hub.docker.com/_/postgres]
* Tener instalado NodeJs
* Tener JDK 1.8
* Eclipse

## Pasos para configurar el proyecto

* **Crear la base de datos:** en la carpeta database se encuntra el archivo `create-database.sql`, por favor ejecutar ese archivo
* **Instalar las dependencias de proyecto front**: en la sub carpeta `dynamic-form` ejecutar el comando `npm i`
* **Importar el proyecto backend:** en el eclipse importar el proyecto como maven, click derecho `maven -> update project`, una vez todas las dependencias sean descargadas ir a la clase `DynamicFormGenerationApiApplication.java` y ejecutarla click derecho `run as -> Java Application`
