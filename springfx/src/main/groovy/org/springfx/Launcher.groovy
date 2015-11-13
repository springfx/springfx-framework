package org.springfx

/**
 *
 * @author Stephan Grundner
 */
class Launcher {

    private static Launcher launcher

    static Launcher get() {
        launcher
    }

    private Class<? extends Application> applicationClass = Application

    protected Set<Class<?>> sources = new LinkedHashSet<>()

    Launcher sources(Class<?>... sources) {
        this.sources.addAll(sources)
        return this
    }

    void launch(String[] args) {
        launcher = this
        Application.launch(applicationClass, args)
    }
}
