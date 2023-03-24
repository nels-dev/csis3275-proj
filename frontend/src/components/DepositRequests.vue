<template>
  <v-container>
    <v-card class="mx-auto">
      <v-card-title>
        <v-row>
          <v-col align-self="center"> Deposit Requests </v-col>
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
            <DepositRequestForm @deposit-submitted="depositSubmitted" />
          </v-col>
        </v-row>
      </v-card-title>
      <v-table class="ma-6">
        <thead>
          <tr>
            <th class="text-left">Submitted at</th>
            <th class="text-left">Date of deposit</th>
            <th class="text-left">Amount</th>
            <th class="text-left">Bank reference</th>
            <th class="text-left">Status</th>
            <th class="text-left" v-if="status === 'REJECTED'">Reason</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="request in depositRequests" :key="request.id">
            <td>{{ moment(request.createdAt).fromNow() }}</td>
            <td>{{ request.depositDate }}</td>
            <td>{{ request.amount }}</td>
            <td>{{ request.bankReference }}</td>
            <td>{{ translateStatus(request.status) }}</td>
            <td v-if="status === 'REJECTED'">{{ request.rejectReason }}</td>
          </tr>
        </tbody>
      </v-table>
    </v-card>
  </v-container>
</template>
<script>
import accountService from "@/services/account.service";
import DepositRequestForm from "./DepositRequestForm.vue";
const statuses = {
  PENDING: "Pending",
  FULFILLED: "Fulfilled",
  REJECTED: "Rejected",
};
export default {
  inject: ["moment"],
  data: () => {
    return {
      depositRequests: [],
      status: "PENDING",
      statuses: statuses,
      showDepositForm: false,
    };
  },
  name: "DepositRequests",
  mounted() {
    this.refreshData(this.status);
  },
  methods: {
    translateStatus: (status) => statuses[status],
    refreshData(status) {
      accountService.getDepositRequests(status).then((resp) => {
        this.depositRequests = resp.data ?? [];
      });
    },
    filterItems() {
      return Object.keys(statuses).map((key) => {
        return { title: statuses[key], value: key };
      });
    },
    depositSubmitted() {
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
  components: { DepositRequestForm },
};
</script>
