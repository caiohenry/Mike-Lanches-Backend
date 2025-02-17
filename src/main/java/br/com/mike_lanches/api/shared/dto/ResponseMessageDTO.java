package br.com.mike_lanches.api.shared.dto;


// Response Sucess DTO by system
public record ResponseMessageDTO(int statusCode, String type, String timestamp, Object message) {
} 
