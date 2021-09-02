package com.exalow.mineo.views.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.function.Predicate;

public interface GridView<T> {

    ObservableList<T> getItems();

    void setItems(ObservableList<T> items);

    void addItem(T item);

    void removeItem(T item);

    int getPages();

    int getPage();

    void setPage(int index);

    int nextPage();

    int previousPage();

    void setColumnProperty(int columns);

    void setRowProperty(int rows);

    void setCellFactory(GridCellFactory<T> cellFactory);

    void setPlaceholder(Node node);

    void showOnly(Predicate<T> options);
}
