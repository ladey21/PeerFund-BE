package com.peerfund.peerfundapp.dto;

import com.peerfund.peerfundapp.entities.Enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {

    private Long id;

    private String groupId;

    private RequestStatus requestStatus;
}
