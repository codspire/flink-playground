# Running Flink Locally on Windows 10

I had to make few tweaks to Flink scripts inside ```$FLINK_HOME/bin``` folder to make it work locally on my Windows 10 PC.

Thanks to https://stackoverflow.com/questions/62232422/flink-cluster-not-starting-due-to-could-not-find-or-load-main-class-error for the tips.

----
## Let me describe the issues first

Details of my environment
- Flink version: `1.12.0`
- Java `11.0.3`
- GitBash CLI
- Windows 10
- Local install directory: `/C/dev/codebase/flink/flink-1.12.0`, exported as `$FLINK_HOME`

#### Try to get the Flink version

```shell
$FLINK_HOME/bin/flink --version

Error: Could not find or load main class org.apache.flink.client.cli.CliFrontend
Caused by: java.lang.ClassNotFoundException: org.apache.flink.client.cli.CliFrontend
```

#### Try to run the Flink Cluster

```shell
$FLINK_HOME/bin/start-cluster.sh
```
Success message on the console, but it is not functional. 

- Flink Web UI is not accessible at http://localhost:8081
- Examples do not run, see exceptions below

#### Run WordCount Example
```shell
$FLINK_HOME/bin/flink run examples/streaming/WordCount.jar
```
```shell
------------------------------------------------------------
 The program finished with the following exception:

org.apache.flink.client.program.ProgramInvocationException: The main method caused an error: Failed to execute job 'Streaming WordCount'.
        at org.apache.flink.client.program.PackagedProgram.callMainMethod(PackagedProgram.java:330)
        at org.apache.flink.client.program.PackagedProgram.invokeInteractiveModeForExecution(PackagedProgram.java:198)
        at org.apache.flink.client.ClientUtils.executeProgram(ClientUtils.java:114)
        at org.apache.flink.client.cli.CliFrontend.executeProgram(CliFrontend.java:743)
        at org.apache.flink.client.cli.CliFrontend.run(CliFrontend.java:242)
        at org.apache.flink.client.cli.CliFrontend.parseAndRun(CliFrontend.java:971)
        at org.apache.flink.client.cli.CliFrontend.lambda$main$10(CliFrontend.java:1047)
        at org.apache.flink.runtime.security.contexts.NoOpSecurityContext.runSecured(NoOpSecurityContext.java:30)
        at org.apache.flink.client.cli.CliFrontend.main(CliFrontend.java:1047)
Caused by: org.apache.flink.util.FlinkException: Failed to execute job 'Streaming WordCount'.
        at org.apache.flink.streaming.api.environment.StreamExecutionEnvironment.executeAsync(StreamExecutionEnvironment.java:1951)
        at org.apache.flink.client.program.StreamContextEnvironment.executeAsync(StreamContextEnvironment.java:128)
        at org.apache.flink.client.program.StreamContextEnvironment.execute(StreamContextEnvironment.java:76)
        at org.apache.flink.streaming.api.environment.StreamExecutionEnvironment.execute(StreamExecutionEnvironment.java:1822)
        at org.apache.flink.streaming.examples.wordcount.WordCount.main(WordCount.java:96)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.apache.flink.client.program.PackagedProgram.callMainMethod(PackagedProgram.java:316)
        ... 8 more
Caused by: org.apache.flink.runtime.client.JobSubmissionException: Failed to submit JobGraph.
        at org.apache.flink.client.program.rest.RestClusterClient.lambda$submitJob$7(RestClusterClient.java:366)
        at java.base/java.util.concurrent.CompletableFuture.uniExceptionally(CompletableFuture.java:986)
        at java.base/java.util.concurrent.CompletableFuture$UniExceptionally.tryFire(CompletableFuture.java:970)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:506)
        at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2088)
        at org.apache.flink.runtime.concurrent.FutureUtils.lambda$retryOperationWithDelay$9(FutureUtils.java:375)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:859)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:837)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:506)
        at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2088)
        at org.apache.flink.runtime.rest.RestClient.lambda$submitRequest$1(RestClient.java:342)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:577)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.notifyListeners0(DefaultPromise.java:570)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:549)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:490)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.setValue0(DefaultPromise.java:615)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.setFailure0(DefaultPromise.java:608)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.DefaultPromise.tryFailure(DefaultPromise.java:117)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.fulfillConnectPromise(AbstractNioChannel.java:321)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.finishConnect(AbstractNioChannel.java:337)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:702)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:650)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:576)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:493)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
        at org.apache.flink.shaded.netty4.io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: org.apache.flink.runtime.concurrent.FutureUtils$RetryException: Could not complete the operation. Number of retries has been exhausted.
        at org.apache.flink.runtime.concurrent.FutureUtils.lambda$retryOperationWithDelay$9(FutureUtils.java:372)
        ... 21 more
Caused by: java.util.concurrent.CompletionException: org.apache.flink.shaded.netty4.io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: no further information: localhost/127.0.0.1:8081
        at java.base/java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:331)
        at java.base/java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:346)
        at java.base/java.util.concurrent.CompletableFuture$UniCompose.tryFire(CompletableFuture.java:1063)
        ... 19 more
Caused by: org.apache.flink.shaded.netty4.io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: no further information: localhost/127.0.0.1:8081
Caused by: java.net.ConnectException: Connection refused: no further information
        at java.base/sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
        at java.base/sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:779)
        at org.apache.flink.shaded.netty4.io.netty.channel.socket.nio.NioSocketChannel.doFinishConnect(NioSocketChannel.java:330)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.finishConnect(AbstractNioChannel.java:334)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:702)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:650)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:576)
        at org.apache.flink.shaded.netty4.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:493)
        at org.apache.flink.shaded.netty4.io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
        at org.apache.flink.shaded.netty4.io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at java.base/java.lang.Thread.run(Thread.java:834)
```
----
## Steps to fix the above issues

### 1) Update `$FLINK_HOME/bin/flink`
  
  Update`log_setting`as below (replace`\\dev\\codebase\\flink\\flink-1.12.0` with your Flink installation directory)
  
  ```shell
  log_setting=(-Dlog.file="$log" -Dlog4j.configuration="\\dev\\codebase\\flink\\flink-1.12.0\\conf\\log4j-cli.properties" -Dlog4j.configurationFile="\\dev\\codebase\\flink\\flink-1.12.0\\conf\\log4j-cli.properties" -Dlogback.configurationFile="\\dev\\codebase\\flink\\flink-1.12.0\\conf\\logback.xml")
  ```
  
  Remove`INTERNAL_HADOOP_CLASSPATHS`from`exec`, below is what it should look like after the update.
  
  ```shell
  exec $JAVA_RUN $JVM_ARGS $FLINK_ENV_JAVA_OPTS "${log_setting[@]}" -classpath "`manglePathList "$CC_CLASSPATH"`" org.apache.flink.client.cli.CliFrontend "$@"
  ```

### 2) Update`$FLINK_HOME/bin/flink-daemon.sh`

Make the same changes as above.

----

## Verify

### Try to get the Flink version
```shell
$FLINK_HOME/bin/flink --version

Version: 1.12.0, Commit ID: fc00492
```
### Run WordCount Example
```shell
$FLINK_HOME/bin/flink run examples/streaming/WordCount.jar

Executing WordCount example with default input data set.
Use --input to specify file input.
Printing result to stdout. Use --output to specify output path.
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.apache.flink.api.java.ClosureCleaner (file:/C:/dev/codebase/flink/flink-1.12.0/lib/flink-dist_2.12-1.12.0.jar) to field java.lang.String.value
WARNING: Please consider reporting this to the maintainers of org.apache.flink.api.java.ClosureCleaner
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
Job has been submitted with JobID 999a6302a7a5ec99886fc2c40813a98e
Program execution finished
Job with JobID 999a6302a7a5ec99886fc2c40813a98e has finished.
Job Runtime: 534 ms
```
### Open Flink Web UI

http://localhost:8081

You should be able to see the WordCount example job in the UI.