<template>
  <v-chip prepend-icon="mdi-currency-usd" @click="openAccount">
    {{ credit.toFixed(2) }}
  </v-chip>
</template>

<script>
import accountService from "@/services/account.service";

export default {
  data: () => {
    return {
      credit: 0,
    };
  },
  name: "CreditChip",
  mounted() {
    this.getData();
  },
  methods: {
    openAccount() {
      this.$router.push("/client/account");
    },
    getData() {
      accountService.getAccount().then((resp) => {
        this.credit = resp.data?.availableBalance ?? 0;
      });
    },
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
