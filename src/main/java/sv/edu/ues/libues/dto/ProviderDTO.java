package sv.edu.ues.libues.dto;

public record ProviderDTO(Long idProvider, String providerName, String addressProvider, String providerPhone,
                          String providerContact1, String providerContact2, boolean status){}