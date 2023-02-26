package csis3275.project.seasell.account;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import csis3275.project.seasell.account.dto.DepositRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.service.DepositRequestService;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Import(ClientDepositRequestController.class)
class ClientDepositRequestControllerTest {
    @MockBean
    DepositRequestService depositRequestService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getDepositRequests() throws Exception {
        when(depositRequestService.getUserDepositRequests(RequestStatus.PENDING)).thenReturn(List.of(getMockDto()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/depositRequests").queryParam("status", "PENDING"))
                .andExpect(content().json(
                        "[{\"id\":1,\"createdAt\":\"2023-02-21T16:00:00Z\",\"createdByEmail\":\"user@test.io\",\"createdById\":0,"
                                + "\"depositDate\":\"2023-02-21\",\"bankReference\":\"281723817\",\"amount\":120,\"status\":\"PENDING\",\"rejectReason\":null}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void addDepositRequests() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/client/depositRequests").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"depositDate\":\"2023-01-16\", \"bankReference\":\"123\", \"amount\":110}"))
                .andExpect(status().isCreated());
        verify(depositRequestService).addDepositRequest(ArgumentMatchers.refEq(expectedDto()));
    }

    public DepositRequestDto expectedDto() {

        DepositRequestDto dto = new DepositRequestDto();
        dto.setDepositDate(LocalDate.of(2023, 1, 16));
        dto.setAmount(new BigDecimal(110));
        dto.setBankReference("123");
        return dto;
    }

    public DepositRequestDto getMockDto() {
        DepositRequestDto dto = new DepositRequestDto();
        dto.setDepositDate(LocalDate.of(2023, 2, 21));
        dto.setCreatedAt(OffsetDateTime.of(2023, 2, 21, 16, 0, 0, 0, ZoneOffset.UTC));
        dto.setAmount(new BigDecimal(120));
        dto.setStatus(RequestStatus.PENDING);
        dto.setCreatedByEmail("user@test.io");
        dto.setBankReference("281723817");
        dto.setId(1);
        return dto;
    }
}