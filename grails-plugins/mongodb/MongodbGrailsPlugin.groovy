
import org.grails.datastore.gorm.mongo.plugin.support.*

class MongodbGrailsPlugin {
    def license = "Apache 2.0 License"
    def organization = [ name: "SpringSource", url: "http://www.springsource.org/" ]
    def developers = [
        [ name: "Graeme Rocher", email: "grocher@vmware.com" ] ]
    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMONGODB" ]
    def scm = [ url: "https://github.com/SpringSource/grails-data-mapping" ]

    def version = "1.0.0.M7"
    def grailsVersion = "1.3.5 > *"
    def observe = ['services']
    def loadAfter = ['domainClass', 'hibernate', 'services', 'cloudFoundry']
    def author = "Graeme Rocher"
    def authorEmail = "graeme.rocher@springsource.com"
    def title = "MongoDB GORM"
    def description = 'A plugin that integrates the Mongo document datastore into Grails, providing a GORM API onto it'

    def documentation = "http://grails.org/plugin/mongodb"

    def doWithSpring = new MongoSpringConfigurer().getSpringCustomizer()
    def doWithDynamicMethods = { ctx ->

        def datastore = ctx.mongoDatastore
        def transactionManager = ctx.mongoTransactionManager
        def methodsConfigurer = new MongoMethodsConfigurer(datastore, transactionManager)    
        methodsConfigurer.hasExistingDatastore = manager.hasGrailsPlugin("hibernate")        
        methodsConfigurer.configure()
    }

    def onChange = { event ->
        def onChangeHandler = new MongoOnChangeHandler()
        onChangeHandler.onChange(delegate, event)
    }
    

    
}
