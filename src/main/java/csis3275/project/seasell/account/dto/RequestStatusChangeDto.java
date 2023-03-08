package csis3275.project.seasell.account.dto;

import csis3275.project.seasell.account.model.RequestStatus;
import lombok.Data;

@Data
public class RequestStatusChangeDto {
    private RequestStatus status;
    private String rejectReason;
}
