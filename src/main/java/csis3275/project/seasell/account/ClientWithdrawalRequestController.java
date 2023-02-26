package csis3275.project.seasell.account;

import csis3275.project.seasell.account.dto.WithdrawalRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.WithdrawRequestService;
import csis3275.project.seasell.common.exception.InsufficientBalanceException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/withdrawalRequests")
public class ClientWithdrawalRequestController {

    @Autowired
    WithdrawRequestService withdrawRequestService;

    @GetMapping
    public List<WithdrawalRequestDto> getWithdrawalRequests(@RequestParam RequestStatus status) {
        return withdrawRequestService.getUserWithdrawalRequests(status);
    }

    @PostMapping
    public ResponseEntity<?> submitWithdrawalRequest(@Valid @RequestBody WithdrawalRequestDto dto) {
        withdrawRequestService.addWithdrawalRequest(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalance(){
        return ResponseEntity.badRequest().body("You do not have sufficient credit available in your account.");
    }
}
