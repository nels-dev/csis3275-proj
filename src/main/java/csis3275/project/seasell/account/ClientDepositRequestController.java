package csis3275.project.seasell.account;

import csis3275.project.seasell.account.dto.DepositRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.DepositRequestService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/depositRequests")
public class ClientDepositRequestController {

    @Autowired
    DepositRequestService depositRequestService;

    @GetMapping
    public List<DepositRequestDto> getDepositRequests(@RequestParam RequestStatus status) {
        return depositRequestService.getUserDepositRequests(status);
    }

    @PostMapping
    public ResponseEntity<?> submitDepositRequest(@RequestBody @Valid DepositRequestDto dto) {
        depositRequestService.addDepositRequest(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
