package com.exalow.mineo.views.factories;

import com.exalow.mineo.util.ViewLoader;
import com.exalow.mineo.views.TagCellController;
import com.exalow.mineo.views.layout.GridCellFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;

import java.util.function.Consumer;

public class TagCellFactory implements GridCellFactory<String> {

    private final ApplicationContext context;
    private final Consumer<String> onClick;

    public TagCellFactory(ApplicationContext context, Consumer<String> onClick) {
        this.context = context;
        this.onClick = onClick;
    }

    public TagCellFactory(ApplicationContext context) {
        this(context, null);
    }

    @Override
    public Node newCell(String item) {
        TagCellController controller = context.getBean(TagCellController.class);
        Parent parent = ViewLoader.load(controller);
        controller.initName(item);
        controller.setOnClick(onClick);
        return parent;
    }
}
