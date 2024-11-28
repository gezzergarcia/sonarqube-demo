# SonarQube Demo

## Librerías

Para la integración con SonarQube, se usan dos librerías: [`jacoco-maven-plugin`](https://www.eclemma.org/jacoco/trunk/doc/maven.html) y [`sonar-maven-plugin`](https://mvnrepository.com/artifact/org.sonarsource.scanner.maven/sonar-maven-plugin).

```xml
  <build>
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.12</version>
        <executions>
          <execution>
            <id>prepare-agent</id>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
            <!-- El archivo xml de salida jacoco.xml es necesario para la integración con SonarQube -->
            <configuration>
              <formats>
                <format>xml</format>
              </formats>
            </configuration>
          </execution>
          <execution>
            <id>report</id>
            <phase>verify</phase>
            <goals>
              <goal>report</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

      <plugins>
      <plugin>
        <groupId>org.sonarsource.scanner.maven</groupId>
        <artifactId>sonar-maven-plugin</artifactId>
        <version>5.0.0.4389</version>
      </plugin>

    </plugins>
  </build>
```

## Code Coverage con JaCoCo (`jacoco-maven-plugin`)

Code coverage is a software metric used to measure how many lines of our code are executed during automated tests.
The JaCoCo Maven plug-in provides the JaCoCo runtime agent to your tests and allows basic report creation.

En la parte de build, se agrega el plugin `jacoco-maven-plugin` y se configuran dos funciones: `prepare-agent` y `report`.

Para probar el proyecto se ejecuta:

```zsh
mvn clean verify
```

En la fase `clean` se eliminan los archivos generados previamente en la carpeta `target`.
Mientras que `verify` ejecuta todas las fases anteriores del ciclo de vida hasta llegar a `verify` (incluyendo: `validate`, `compile`, `test`, `package` y `verify`). La fase `verify` asegura que todo el ciclo de construcción sea exitoso y que los resultados cumplan con los criterios definidos (como cobertura de pruebas o validaciones personalizadas).

**En `prepare-agent` se configura el formato en `xml`, ya que es el formato requerido por SonarQube.**

La librería JaCoCo también genera un conjunto de archivos html dentro de la carpeta `target/site/jacoco`.

## Integración con SonarQube (`sonar-maven-plugin`)

Para la autenticación en la conexión con SonarQube se debe tener un token que se genera desde el servidor SonarQube. Una vez que se tenga, por seguridad, **establecer el valor del token de SonarQube en una variable de ambiente**. En este ejemplo, la variable de ambiente que contiene el valor del token es `SONARQUBEUSER_TOKEN`.

Los datos para la conexión a SonarQube se encuentran en la sección de propiedades del arcvhivo `pom.xml`:

```xml
  <properties>
  
    <sonar.projectKey>sonarqube-demo</sonar.projectKey>
    <sonar.projectName>SonarQube Demo</sonar.projectName>
    <sonar.projectVersion>1.0-SNAPSHOT</sonar.projectVersion>
    <sonar.sources>src/main/java</sonar.sources>
    <sonar.tests>src/test/java</sonar.tests>
    <sonar.java.binaries>target/classes</sonar.java.binaries>
    <sonar.host.url>http://localhost:9000</sonar.host.url>
    <sonar.login>${env.SONARQUBEUSER_TOKEN}</sonar.login>
    <sonar.scm.provider>git</sonar.scm.provider>
  </properties>
```

Así, podemos ejecutar las pruebas con el servidor de SonarQube el proyecto con las pruebas de SonarQube de la siguiente forma:

```zsh
mvn sonar:sonar 
```

Si no se tuviesen en el pom, se podría agregar esta información como argumentos desde el comando de ejecución. Para esto se necesitan al menos dos valores: `sonar.host.url` y `sonar.login`:

```zsh
mvn sonar:sonar \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=$SONARQUBEUSER_TOKEN
```

Nota: Para que cargue correctamente el archivo logback.xml, éste debe estar en la carpeta `resources`.
