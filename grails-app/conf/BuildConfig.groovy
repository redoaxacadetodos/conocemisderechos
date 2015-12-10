grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
        

        
        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "http://repo.grails.org/grails/plugins"      

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		
    }

    dependencies {
		//Envio de archivos sftp
		compile 'com.jcraft:jsch:0.1.49'
		
		compile 'commons-codec:commons-codec:1.6'
         compile 'org.apache.httpcomponents:httpcore:4.1.2' 
        compile 'org.apache.httpcomponents:httpclient:4.1.2' 
        runtime 'org.apache.httpcomponents:httpcore:4.1.2'
        runtime 'org.apache.httpcomponents:httpclient:4.1.2'
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        // runtime 'mysql:mysql-connector-java:5.1.22'
		runtime 'postgresql:postgresql:9.0-801.jdbc4'
		
    }

    plugins {
        runtime ':hibernate:3.6.10.16' 
        runtime ":jquery:1.8.3"
        runtime ':resources:1.2.8'

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

        build ':tomcat:7.0.54'


        runtime ':database-migration:1.4.0'

        compile ':cache:1.0.1'
		compile ":export:1.6"
    }
}
