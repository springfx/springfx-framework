package org.springfx

import org.springframework.boot.Banner

/**
 * Spring FX application launcher.
 *
 * @author Stephan Grundner
 */
class Launcher {

    private static Launcher launcher

    static Launcher get() {
        launcher
    }

    private Class<? extends Application> applicationClass = Application

    protected final Set<Class<?>> sources = new LinkedHashSet<>()
    protected boolean usingProjections
    protected Banner.Mode bannerMode

    Launcher(Class<?> source) {
        sources.add(source)
    }

    Launcher() {

    }

    /**
     * Application sources.
     *
     * @param sources The sources
     */
    Launcher sources(Class<?>... sources) {
        this.sources.addAll(sources)

        this
    }

    /**
     * Using projections.
     *
     * @see {@link org.springfx.scene.Projection}
     * @param usingProjections True if application should use projections, otherwise false
     */
    Launcher usingProjections(boolean usingProjections) {
        this.usingProjections = usingProjections

        this
    }

    /**
     * Banner mode.
     *
     * @see {@link org.springframework.boot.builder.SpringApplicationBuilder#bannerMode(org.springframework.boot.Banner.Mode)}
     * @param bannerMode The banner mode
     */
    Launcher bannerMode(Banner.Mode bannerMode) {
        this.bannerMode = bannerMode

        this
    }

    /**
     * Launch the application.
     *
     * @param args The arguments
     */
    void launch(String[] args) {
        launcher = this
        Application.launch(applicationClass, args)
    }
}
