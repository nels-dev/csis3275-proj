<template>
  <v-container>
    <v-card class="mx-auto">
      <v-card-title>
        <v-row>
          <v-col align-self="center"> Withdrawal Requests </v-col>
          <v-spacer></v-spacer>
          <v-col align-self="center" cols="auto">
            <v-select
              v-model="status"
              hide-details="true"
              density="compact"
              :items="filterItems()"
            ></v-select>
          </v-col>
          <v-col align-self="center" cols="auto">
            <WithdrawalRequestForm
              @withdrawal-submitted="withdrawalSubmitted"
            />
          </v-col>
        </v-row>
      </v-card-title>
      <v-table height="30vh" class="ma-6">
        <thead>
          <tr>
            <th class="text-left">Submitted at</th>
            <th class="text-left">Amount</th>
            <th class="text-left">Beneficiary</th>
            <th class="text-left">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="request in withdrawalRequests" :key="request.id">
            <td>{{ moment(request.createdAt).fromNow() }}</td>
            <td>{{ request.amount }}</td>
            <td>
              {{ `${request.beneficiaryName}` }}<br />
              <small>{{
                `${request.bankInstitutionNumber}-${request.bankTransitNumber}-${request.bankAccountNumber}`
              }}</small>
            </td>
            <td>{{ translateStatus(request.status) }}</td>
          </tr>
        </tbody>
      </v-table>
    </v-card>
  </v-container>
</template>
<script>
import accountService from "@/services/account.service";
import WithdrawalRequestForm from "./WithdrawalRequestForm.vue";
const statuses = {
  PENDING: "Pending",
  FULFILLED: "Fulfilled",
  REJECTED: "Rejected",
};
export default {
  inject: ["moment"],
  data: () => {
    return {
      withdrawalRequests: [],
      status: "PENDING",
      statuses: statuses,
      showWithdrawalForm: false,
    };
  },
  name: "WithdrawalRequests",
  mounted() {
    this.refreshData(this.status);
  },
  methods: {
    translateStatus: (status) => statuses[status],
    refreshData(status) {
      accountService.getWithdrawalRequests(status).then((resp) => {
        this.withdrawalRequests = resp.data ?? [];
      });
    },
    filterItems() {
      return Object.keys(statuses).map((key) => {
        return { title: statuses[key], value: key };
      });
    },
    withdrawalSubmitted() {
      this.refreshData(this.status);
    },
  },
  watch: {
    status: {
      handler(newStatus, oldStatus) {
        if (newStatus !== oldStatus) {
          this.refreshData(newStatus);
        }
      },
    },
  },
  components: { WithdrawalRequestForm },
};
</script>
