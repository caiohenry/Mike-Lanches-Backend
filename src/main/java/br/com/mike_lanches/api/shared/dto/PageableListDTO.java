package br.com.mike_lanches.api.shared.dto;

// Imports
import java.util.List;

// Create DTO - Pageable List
public class PageableListDTO<T> {

    // Declare variables
    private List<T> items;
    private long totalElements;

    // Constructor
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