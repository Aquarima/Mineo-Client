package com.exalow.mineo.views.layout;

import javafx.scene.Node;

public interface GridCellFactory<T> {

    Node newCell(T item);
}
