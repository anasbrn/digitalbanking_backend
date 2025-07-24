package org.digitalbanking.digitalbanking_backend.dtos;

import lombok.Data;
import org.digitalbanking.digitalbanking_backend.enums.OperationType;

import java.util.Date;

@Data
public class OperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
}
