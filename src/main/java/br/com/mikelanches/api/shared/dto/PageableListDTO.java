package br.com.mikelanches.api.shared.dto;

import java.util.List;

public class PageableListDTO<T> {
    private List<T> items;
    private long totalElements;

    public PageableListDTO(List<T> items, long totalElements) {
        this.items = items;
        this.totalElements = totalElements;
    }

    // Getters e setters
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}