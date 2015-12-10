// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

import grails.plugins.springsecurity.SecurityConfigType

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.includes = ['/img/**', '/images/skin/**']
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true	
		grails.serverURL = "http://localhost:8080/${appName}"		
    }
    production {
        grails.logging.jul.usebridge = false
		//grails.serverURL = "http://development.redoaxaca.gob.mx:8080/${appName}"
    //grails.serverURL = "http://172.31.0.101:8080/${appName}"
    //grails.serverURL = "http://www.conocemisderechos.oaxaca.gob.mx"
        // TODO: grails.serverURL = "http://www.changeme.com"		
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails {
	mail {
	  host = "smtp.gmail.com"
	  port = 465
	  username = "USER"
	  password = "PASSWORD"
	  props = ["mail.smtp.auth":"true",
			   "mail.smtp.socketFactory.port":"465",
			   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			   "mail.smtp.socketFactory.fallback":"false"]
	}
 }

grails.plugins.springsecurity.rejectIfNoRule = true
grails.plugins.springsecurity.securityConfigType = "Annotation"
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
	'/css/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/img/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
  '/bootstrap/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/images/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/js/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/json/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/fonts/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/datatables/**':  ['IS_AUTHENTICATED_ANONYMOUSLY']

]

//Export Plug-in
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
	xml: ['text/xml', 'application/xml'],
	text: 'text-plain',
	js: 'text/javascript',
	rss: 'application/rss+xml',
	atom: 'application/atom+xml',
	css: 'text/css',
	csv: 'text/csv',
	pdf: 'application/pdf',
	rtf: 'application/rtf',
	excel: 'application/vnd.ms-excel',
	ods: 'application/vnd.oasis.opendocument.spreadsheet',
	all: '*/*',
	json: ['application/json','text/json'],
	form: 'application/x-www-form-urlencoded',
	multipartForm: 'multipart/form-data'
  ]



// Added by the Spring Security Core plugin:
mx.indesti.cednna.valores.directoriouploads="/var/lib/cednnafiles/"
mx.indesti.cednna.valores.directorioLogotipos="/var/lib/cednnafiles/logotipos/"
mx.indesti.cednna.valores.directorioInfografias="/var/lib/cednnafiles/infografias/"
grails.plugins.springsecurity.userLookup.userDomainClassName = 'mx.gob.redoaxaca.cednna.seguridad.Usuario'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'mx.gob.redoaxaca.cednna.seguridad.UsuarioRol'
grails.plugins.springsecurity.authority.className = 'mx.gob.redoaxaca.cednna.seguridad.Rol'

//Agregado para plugin de overlay
mx.gob.redoaxaca.utils.domainClassName="mx.gob.redoaxaca.cednna.domino.Valor"
mx.gob.redoaxaca.utils.campoSuspendido="msjSuspendido"
mx.gob.redoaxaca.utils.campoSuspendidoHabilitado="suspendidoHabilitado"
mx.gob.redoaxaca.utils.campoTitulo="tituloSuspendido"
mx.gob.redoaxaca.utils.clave="key"
mx.gob.redoaxaca.utils.descripcion="valor"
mx.gob.redoaxaca.utils.campoPermitirDeshabilitarSuspendido="permitirDeshabilitarSuspendido"