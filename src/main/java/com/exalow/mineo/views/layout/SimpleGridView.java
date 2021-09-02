package com.exalow.mineo.views.layout;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.function.Predicate;

public class SimpleGridView<T> implements GridView<T> {

    private final GridPane grid;

    private final ObservableList<T> observableList;
    private final Map<T, Node> loadedCells;
    private GridCellFactory<T> factory;
    private Predicate<T> predicate;
    private int columnProperty;
    private int rowProperty;
    private int currentPage;
    private Node placeholder;

    public SimpleGridView(GridPane grid, int columnProperty, int rowProperty) {
        this(grid, FXCollections.observableArrayList(), columnProperty, rowProperty);
    }

    public SimpleGridView(GridPane grid, ObservableList<T> items, int columnProperty, int rowProperty) {
        this.grid = grid;
        this.observableList = items;
        this.setRowProperty(rowProperty);
        this.setColumnProperty(columnProperty);
        this.rowProperty = rowProperty;
        this.loadedCells = new HashMap<>();
        this.currentPage = 0;
        this.initialize();
    }

    public void initialize() {
        observableList.addListener((ListChangeListener<T>) c -> {
            if (c.next()) {

                Set<T> copy = Set.copyOf(loadedCells.keySet());

                for (T element : copy) {

                    if (!observableList.contains(element)) {
                        loadedCells.remove(element);
                    }

                }

                setPage(currentPage);
            }
        });
    }

    @Override
    public ObservableList<T> getItems() {
        return observableList;
    }

    @Override
    public void setItems(ObservableList<T> items) {
        observableList.forEach(this::removeItem);
        items.forEach(this::addItem);
    }

    @Override
    public void addItem(T item) {

        observableList.add(item);

        if (currentPage == 0) {
            this.nextPage();
        }

        setPage(currentPage);
    }

    @Override
    public void removeItem(T item) {

        observableList.remove(item);

        while (currentPage > getPages()) {
            this.currentPage = currentPage - 1;
        }

        setPage(currentPage);
    }

    @Override
    public int getPages() {

        List<T> items = this.observableList;

        if (predicate != null) {
            items = this.observableList.filtered(predicate);
        }

        int itemsPerPage = (rowProperty * columnProperty);
        int itemAmount = items.size();

        if ((itemAmount % itemsPerPage) != 0) {
            return (itemAmount / itemsPerPage) + 1;
        }

        return itemAmount / itemsPerPage;
    }

    @Override
    public int getPage() {
        return currentPage;
    }

    @Override
    public void setPage(int index) {

        List<T> items = this.observableList;

        if (predicate != null) {
            items = this.observableList.filtered(predicate);
        }

        int itemAmount = items.size();
        int itemsPerPage = rowProperty * columnProperty;

        if ((index < 0) || index > getPages()) {
            throw new InputMismatchException("Page '" + index + "' does not exist");
        }

        grid.getChildren()
                .clear();

        if (itemAmount == 0) {

            if (placeholder != null) {
                grid.add(placeholder, 0, 0);
            }

            return;
        }

        if (index == 0)
            return;

        int firstIndex = itemsPerPage * (index - 1);
        int lastIndex = firstIndex + itemsPerPage;

        if (lastIndex > itemAmount) {
            lastIndex = firstIndex + (itemAmount % itemsPerPage);
        }

        Iterator<T> selection = items.subList(firstIndex, lastIndex)
                .iterator();

        for (int y = 0; y < rowProperty; y++) {

            for (int x = 0; x < columnProperty; x++) {

                if (factory == null) {
                    factory = item -> new Label(item.toString());
                }

                if (selection.hasNext()) {

                    T item = selection.next();

                    Node node = loadedCells.get(item);

                    if (node == null) {
                        node = factory.newCell(item);
                        loadedCells.put(item, node);
                    }

                    grid.add(node, x, y);
                }

            }

        }

        this.currentPage = index;
    }

    @Override
    public int nextPage() {

        int index = currentPage;

        if (currentPage < getPages()) {
            index = +1;
        }

        setPage(index);
        return index;
    }

    @Override
    public int previousPage() {

        if (currentPage <= 1)
            return currentPage;

        int index = currentPage - 1;

        setPage(index);
        return index;
    }

    @Override
    public void setColumnProperty(int columns) {
        if (columns >= 0)
            this.columnProperty = columns;
    }

    @Override
    public void setRowProperty(int rows) {
        if (rows >= 0)
            this.rowProperty = rows;
    }

    @Override
    public void setCellFactory(GridCellFactory<T> cellFactory) {
        this.factory = cellFactory;
    }

    @Override
    public void setPlaceholder(Node node) {
        this.placeholder = node;
    }

    @Override
    public void showOnly(Predicate<T> options) {

        this.predicate = options;

        if (getPages() >= 1) {
            setPage(currentPage = 1);
            return;
        }

        setPage(currentPage = 0);
    }
}
