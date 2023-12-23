package sv.edu.ues.libues.dto;

import java.time.LocalDate;

public record ProductDTO (Long idProduct,AreaDTO area,ProviderDTO provider,EditorialDTO editorial,ProductTypeDTO productType,
                          String nameProduct,int correlative,int existence,int hall,int minimumUnit,int measureUnit,
                          String country,String isbn,LocalDate creationDate,Double price,Double unitCost,
                          String specificExpenses,String consignation,int previousProvider,int marked,int quantityLabel){}