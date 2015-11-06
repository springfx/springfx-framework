package biz.jovido.framework

import biz.jovido.framework.config.JovidoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springfx.theater.Theater

class JovidoApplication extends Theater {

    static void main(String[] args) {
        launch(JovidoApplication, args)
    }

    @Override
    protected SpringApplicationBuilder createBuilder() {
        return super.createBuilder().sources(JovidoConfiguration)
    }
}
