package br.com.mike_lanches.api.shared.models;

// Imports
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterModel {
    
    private String field;
    private String value;
    private String matchMode;
}
