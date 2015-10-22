package org.springfx.iconfonts

import javafx.scene.text.Text
import org.springframework.core.io.Resource

/**
 *
 * <pre>
 *     <dependency>
 *         <groupId>org.webjars</groupId>
 *         <artifactId>font-awesome</artifactId>
 *         <version>4.4.0</version>
 *     </dependency>
 * </pre>
 *
 * @author Stephan Grundner
 */
interface IconFont {

    Properties getMappings()
    Resource getFontResource()

    Text getIcon(String name)
}
