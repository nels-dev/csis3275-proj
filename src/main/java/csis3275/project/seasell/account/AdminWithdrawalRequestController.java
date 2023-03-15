package csis3275.project.seasell.account;

import csis3275.project.seasell.account.dto.RequestStatusChangeDto;
import csis3275.project.seasell.account.dto.WithdrawalRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.WithdrawRequestService;
import csis3275.project.seasell.common.exception.InsufficientBalanceException;
import csis3275.project.seasell.common.exception.InvalidStateException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/withdrawalRequests")
public class AdminWithdrawalRequestController {

    @Autowired
    WithdrawRequestService withdrawRequestService;

    @GetMapping
    public List<WithdrawalRequestDto> getWithdrawalRequests(@RequestParam RequestStatus status) {
        return withdrawRequestService.getAllWithdrawalRequestsForAdmin(status);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> submitWithdrawalRequest(@PathVariable long id, @RequestBody RequestStatusChangeDto dto) {
        withdrawRequestService.changeStatus(id, dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalance() {
        return ResponseEntity.badRequest().body("You do not have sufficient credit available in your account.");
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<?> handleInvalidState() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Request has already been fulfilled or rejected");

    }
}
