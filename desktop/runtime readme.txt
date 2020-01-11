https://download.java.net/java/GA/jdk13.0.1/cec27d702aa74d5a8630c65ae61e4305/9/GPL/openjdk-13.0.1_windows-x64_bin.zip



https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_windows-x64_bin.zip




jdeps --regex "(java.*|jdk.*)" --ignore-missing-deps --list-deps C:\Users\blair\code\warcraft\desktop\build\libs\Warcraft2-1.0-all.jar
   java.base
   java.datatransfer
   java.desktop
   java.logging
   java.management
   java.naming
   java.sql
   java.xml


jlink --no-header-files --no-man-pages --compress=2 --add-modules java.base,java.datatransfer,java.desktop,java.logging,java.management,java.naming,java.sql,java.xml --output java-runtime





java.base@13.0.1
java.compiler@13.0.1
java.datatransfer@13.0.1
java.desktop@13.0.1
java.instrument@13.0.1
java.logging@13.0.1
java.management@13.0.1
java.management.rmi@13.0.1
java.naming@13.0.1
java.net.http@13.0.1
java.prefs@13.0.1
java.rmi@13.0.1
java.scripting@13.0.1
java.se@13.0.1
java.security.jgss@13.0.1
java.security.sasl@13.0.1
java.smartcardio@13.0.1
java.sql@13.0.1
java.sql.rowset@13.0.1
java.transaction.xa@13.0.1
java.xml@13.0.1
java.xml.crypto@13.0.1
jdk.accessibility@13.0.1
jdk.aot@13.0.1
jdk.attach@13.0.1
jdk.charsets@13.0.1
jdk.compiler@13.0.1
jdk.crypto.cryptoki@13.0.1
jdk.crypto.ec@13.0.1
jdk.crypto.mscapi@13.0.1
jdk.dynalink@13.0.1
jdk.editpad@13.0.1
jdk.hotspot.agent@13.0.1
jdk.httpserver@13.0.1
jdk.internal.ed@13.0.1
jdk.internal.jvmstat@13.0.1
jdk.internal.le@13.0.1
jdk.internal.opt@13.0.1
jdk.internal.vm.ci@13.0.1
jdk.internal.vm.compiler@13.0.1
jdk.internal.vm.compiler.management@13.0.1
jdk.jartool@13.0.1
jdk.javadoc@13.0.1
jdk.jcmd@13.0.1
jdk.jconsole@13.0.1
jdk.jdeps@13.0.1
jdk.jdi@13.0.1
jdk.jdwp.agent@13.0.1
jdk.jfr@13.0.1
jdk.jlink@13.0.1
jdk.jshell@13.0.1
jdk.jsobject@13.0.1
jdk.jstatd@13.0.1
jdk.localedata@13.0.1
jdk.management@13.0.1
jdk.management.agent@13.0.1
jdk.management.jfr@13.0.1
jdk.naming.dns@13.0.1
jdk.naming.rmi@13.0.1
jdk.net@13.0.1
jdk.pack@13.0.1
jdk.rmic@13.0.1
jdk.scripting.nashorn@13.0.1
jdk.scripting.nashorn.shell@13.0.1
jdk.sctp@13.0.1
jdk.security.auth@13.0.1
jdk.security.jgss@13.0.1
jdk.unsupported@13.0.1
jdk.unsupported.desktop@13.0.1
jdk.xml.dom@13.0.1
jdk.zipfs@13.0.1