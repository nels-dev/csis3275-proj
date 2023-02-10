<template>
  <v-chip
    size="large"
    class="ma-2"
    prepend-icon="mdi-currency-usd"
    @click="openAccount"
  >
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
    accountService.getAccount().then((resp) => {
      this.credit = resp.data?.availableBalance ?? 0;
    });
  },
  methods: {
    openAccount() {
      this.$router.push("/account");
    },
  },
};
</script>
