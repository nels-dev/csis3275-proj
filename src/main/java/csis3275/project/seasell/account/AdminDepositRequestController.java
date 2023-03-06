package csis3275.project.seasell.account;

import csis3275.project.seasell.account.dto.DepositRequestDto;
import csis3275.project.seasell.account.dto.RequestStatusChangeDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.DepositRequestService;
import csis3275.project.seasell.common.exception.InvalidStateException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/depositRequests")
public class AdminDepositRequestController {

    @Autowired
    DepositRequestService depositRequestService;

    @GetMapping
    public List<DepositRequestDto> getDepositRequests(@RequestParam RequestStatus status) {
        return depositRequestService.getAllDepositRequestsForAdmin(status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeRequestStatus(@PathVariable long id, @RequestBody RequestStatusChangeDto dto) {
        depositRequestService.changeStatus(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<?> handleInvalidState() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Request has already been fulfilled or rejected");

    }
}
