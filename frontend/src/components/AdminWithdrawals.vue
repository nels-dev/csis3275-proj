<template>
  <v-container>
    <v-row>
      <v-col>
        <h2>Withdraw Requests</h2>
      </v-col>
      <v-spacer></v-spacer>
      <v-col>
        <v-select
          v-model="status"
          hide-details="true"
          density="compact"
          :items="filterItems()"
        ></v-select>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-table>
          <thead>
            <tr>
              <th class="text-left">Submitted by</th>
              <th class="text-left">Submitted at</th>
              <th class="text-left">Amount</th>
              <th class="text-left">Beneficiary</th>
              <th class="text-left">Account number</th>
              <th class="text-left">Status</th>
              <th class="text-left" v-if="status === 'PENDING'">Actions</th>
              <th class="text-left" v-if="status === 'REJECTED'">Reason</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="request in withdrawalRequests" :key="request.id">
              <td>{{ request.createdByEmail }}</td>
              <td>{{ moment(request.createdAt).fromNow() }}</td>
              <td>{{ request.amount }}</td>
              <td>
                {{ request.beneficiaryName }}
              </td>
              <td>
                {{
                  `${request.bankInstitutionNumber}-${request.bankTransitNumber}-${request.bankAccountNumber}`
                }}
              </td>
              <td>{{ translateStatus(request.status) }}</td>
              <td v-if="status === 'PENDING'">
                <v-btn
                  class="ma-2"
                  color="primary"
                  @click="fulfill(request.id)"
                >
                  Fulfill
                  <v-icon end icon="mdi-checkbox-marked-circle"></v-icon>
                </v-btn>
                <v-dialog v-model="rejectDialog" persistent width="600">
                  <template v-slot:activator="{ props }">
                    <v-btn class="ma-2" color="red" v-bind="props">
                      Reject
                      <v-icon end icon="mdi-cancel"></v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5">Reject Withdrawal Request</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12">
                            <v-text-field
                              v-model="rejectReason"
                              label="Reject Reason"
                              required
                            ></v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="secondary"
                        variant="text"
                        @click="rejectDialog = false"
                      >
                        Cancel
                      </v-btn>
                      <v-btn
                        color="primary"
                        variant="text"
                        @click="reject(request.id)"
                      >
                        Submit
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </td>
              <td v-if="status === 'REJECTED'">{{ request.rejectReason }}</td>
            </tr>
          </tbody>
        </v-table>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import accountService from "@/services/account.service";
const statuses = {
  PENDING: "Pending",
  FULFILLED: "Fulfilled",
  REJECTED: "Rejected",
};
export default {
  inject: ["moment"],
  data() {
    return {
      withdrawalRequests: [],
      status: "PENDING",
      rejectDialog: false,
      rejectReason: "",
    };
  },
  mounted() {
    this.refreshData(this.status);
  },
  methods: {
    translateStatus: (status) => statuses[status],
    refreshData(status) {
      accountService.getWithdrawalRequestsForAdmin(status).then((resp) => {
        this.withdrawalRequests = resp.data ?? [];
      });
    },
    filterItems() {
      return Object.keys(statuses).map((key) => {
        return { title: statuses[key], value: key };
      });
    },
    fulfill(id) {
      accountService.fulfillWithdrawalRequest(id).then(() => {
        this.$store.dispatch("alert/pushInfo", "Withdrawal fulfilled");
        this.refreshData(this.status);
      });
    },
    reject(id) {
      accountService.rejectWithdrawalRequest(id, this.rejectReason).then(() => {
        this.$store.dispatch("alert/pushInfo", "Withdrawal rejected");
        this.rejectDialog = false;
        this.rejectReason = "";
        this.refreshData(this.status);
      });
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
};
</script>
