# Flink Playground

**Using maven archetype to create Flink project**

```markdown
mvn archetype:generate \
-DarchetypeGroupId=org.apache.flink \
-DarchetypeArtifactId=flink-quickstart-java \
-DarchetypeVersion=1.11.1 \
-DgroupId=com.codspire \
-DartifactId=flink-playground \
-Dversion=0.0.1-SNAPSHOT \
-Dpackage=com.codspire.flink \
-DinteractiveMode=false
```

**How to run nc / ncat / net cat on Windows**

* Download the nmap from https://nmap.org/download.html
* Start the ncat server
  ```shell
  ncat.exe -lvk -p 9090
  ```
  Optionally, enable multiple clients to connect simultaneously using ```-m``` flag
  ```shell
  ncat.exe -lvk -p 9090 -m 5
  ```

* Test by connecting to this port
  ```shell
  ncat.exe localhost 9090
  ```
  More details on ncat : https://www.varonis.com/blog/netcat-commands/