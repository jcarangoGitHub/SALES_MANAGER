Requisitos previos

La aplicación ha sido desarrollada con ayuda de playframework. 
Para poder utilizar las bondades que ofrece este framework, 
se debe tener descargado y configurado en variables de entorno el sbt.
Dentro de la carpeta del proyecto se incluye la versión sbt usada: 
sbt-0.13.17.zip 
Se debe descomprimir y configurar el path en variables de entorno.
Ejemplo en windows: 'C:\Program Files\sbt\sbt-0.13.17\bin'

La versión de Java utilizada fue jdk1.8.0_131.


Pasos para desplegar la aplicación

1. Se debe crear una base de datos llamada 'manager_items' PostgreSql
con el dueño 'postgres'. Se incluye script llamado 
'create-data-base-manager_items.sql'.

2. Desde una consola o terminal, situarse dentro de la carpeta descomprimida llamada
'SALES_MANAGER-master' y ejecutar el comando 'activator run'. Este comando
se encarga de resolver las dependencias necesarias para ejecutar la aplicación.
Cuando termine el proceso, debe mostrar el mensaje 'Server started'.

3. Desde un navegador, cargar la url http://localhost:9000/ 
Al cargar por primera vez, se crean las tablas de la base de datos 
automáticamente.

4. Crear usuario y productos de prueba. Se debe ejecutar los scripts
'insert-user-su.sql' y 'insert-items.sql' dentro de un editor postgres en
la base de datos 'manager_items'

5. Loguearse en la aplicación con el Nombre de usuario 'su' y 
Contraseña 'su'
