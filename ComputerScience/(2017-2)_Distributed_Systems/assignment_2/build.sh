javac RemoteInterface.java
jar cvf remoteObject.jar RemoteInterface.class
export CLASSPATH=$CLASSPATH:./remoteObject.jar:.
javac RemoteClass.java
rmic -d . RemoteClass
rmiregistry 3000&
java -Djava.rmi.server.hostname=127.0.0.1 RemoteClass 3000 &
javac RemoteClient.java
java RemoteClient 127.0.0.1 3000