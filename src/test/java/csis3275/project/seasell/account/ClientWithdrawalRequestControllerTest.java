package csis3275.project.seasell.account;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import csis3275.project.seasell.account.dto.WithdrawalRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.WithdrawRequestService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(useDefaultFilters = false, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@Import(ClientWithdrawalRequestController.class)
class ClientWithdrawalRequestControllerTest {
    @MockBean
    WithdrawRequestService withdrawRequestService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getWithdrawalRequest() throws Exception {
        when(withdrawRequestService.getUserWithdrawalRequests(RequestStatus.PENDING)).thenReturn(List.of(getMockDto()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/withdrawalRequests").queryParam("status", "PENDING"))
                .andExpect(content().json(
                        "[{\"id\":1,\"createdAt\":\"2023-02-21T16:00:00Z\",\"createdByEmail\":\"user@test.io\",\"createdById\":2,"
                                + "\"bankInstitutionNumber\":\"003\",\"bankTransitNumber\":\"10294\",\"bankAccountNumber\":\"29576383\","
                                + "\"amount\":120,\"status\":\"PENDING\",\"rejectReason\":null}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void addWithdrawalRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/client/withdrawalRequests")
                .contentType(MediaType.APPLICATION_JSON).content(
                        "{\"bankAccountNumber\":\"12378573\", \"bankInstitutionNumber\":\"004\",\"beneficiaryName\":\"Cliff\", \"bankTransitNumber\":\"65245\", \"amount\":250}"))
                .andExpect(status().isCreated());
        verify(withdrawRequestService).addWithdrawalRequest(ArgumentMatchers.refEq(expectedDto()));
    }

    public WithdrawalRequestDto expectedDto() {

        WithdrawalRequestDto dto = new WithdrawalRequestDto();
        dto.setBankAccountNumber("12378573");
        dto.setBankInstitutionNumber("004");
        dto.setBankTransitNumber("65245");
        dto.setBeneficiaryName("Cliff");
        dto.setAmount(new BigDecimal(250));
        return dto;
    }

    public WithdrawalRequestDto getMockDto() {
        WithdrawalRequestDto dto = new WithdrawalRequestDto();
        dto.setCreatedAt(OffsetDateTime.of(2023, 2, 21, 16, 0, 0, 0, ZoneOffset.UTC));
        dto.setAmount(new BigDecimal(120));
        dto.setStatus(RequestStatus.PENDING);
        dto.setCreatedByEmail("user@test.io");
        dto.setBankInstitutionNumber("003");
        dto.setBankTransitNumber("10294");
        dto.setBankAccountNumber("29576383");
        dto.setCreatedById(2);
        dto.setId(1);
        return dto;
    }
}
