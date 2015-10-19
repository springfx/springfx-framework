package org.springfx.context

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.*
import org.springframework.core.env.Environment

/**
 *
 * @author grs
 */
@Configuration
@ComponentScan('org.springfx')
@PropertySources([
        @PropertySource(value = 'classpath:application.properties', ignoreResourceNotFound = true)])
class ApplicationConfiguration {

    @Autowired
    Environment env

    @Autowired
    ApplicationContext applicationContext

//    @Lazy
//    @Bean(name = 'primarySceneManager')
//    SceneManager primarySceneManager(@Qualifier('primaryStage') Stage primaryStage) {
//        new SceneManager(applicationContext, primaryStage)
//    }
//
//    @Bean(name = 'fxmlLoader')
//    @Scope('prototype')
//    FXMLLoader fxmlLoader() {
//        def loader = new FXMLLoader()
//        loader.setClassLoader(applicationContext.classLoader)
//        loader.controllerFactory = new Callback<Class<?>, Object>() {
//            @Override
//            Object call(Class<?> controllerType) {
//                applicationContext.getBeanOrInstance(controllerType)
//            }
//        }
//        loader
//    }
//
//    @PostConstruct
//    protected void initialize() {
//        def basePackage = env.getProperty('application.base-package')
//        if (basePackage == null) {
//            basePackage = applicationContext.application.class.package.name
//        }
//        applicationContext.scan(basePackage)
//    }
}
