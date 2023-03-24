<template>
  <v-container>
    <v-row>
      <v-col md="6" lg="4">
        <v-card
          class="mx-auto"
          title="Available Balance"
          subtitle="Your balance with us available for spending"
        >
          <v-card-text>
            <v-row align="center">
              <v-col class="text-h3" cols="12">
                C${{ availableBalance.toFixed(2) }}
              </v-col>
              <v-col cols="12"> Last updated: {{ lastUpdate }} </v-col>
            </v-row>
          </v-card-text>

          <v-divider></v-divider>
        </v-card>
      </v-col>
      <v-col md="6" lg="4">
        <v-card
          class="mx-auto"
          title="On Hold"
          subtitle="Your credit is held with us securely after a purchase"
        >
          <v-card-text>
            <v-row align="center">
              <v-col class="text-h3" cols="12">
                C${{ heldBalance.toFixed(2) }}
              </v-col>
              <v-col cols="12">
                <span v-if="heldBalance > 0"
                  >Auto released after 14 days of item delivery.</span
                >
                <span v-else>Any held credit is released within 14 days</span>
              </v-col>
            </v-row>
          </v-card-text>

          <v-divider></v-divider>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import accountService from "@/services/account.service";
import moment from "moment";
export default {
  data: () => {
    return {
      availableBalance: 0,
      heldBalance: 0,
      lastUpdate: "-",
    };
  },
  name: "MyAccountOverview",
  methods: {
    getData() {
      accountService.getAccount().then((resp) => {
        this.availableBalance = resp.data?.availableBalance ?? 0;
        this.heldBalance = resp.data?.heldBalance ?? 0;
        this.lastUpdate = moment(resp.data?.lastUpdate).fromNow();
        this.$store.dispatch("account/balanceChanged");
      });
    },
  },
  mounted() {
    this.getData();
  },
  computed: {
    lastRefresh() {
      return this.$store.state.account.lastRefresh;
    },
  },
  watch: {
    lastRefresh() {
      this.getData();
    },
  },
};
</script>
