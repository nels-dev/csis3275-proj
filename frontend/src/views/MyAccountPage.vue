<template>
  <HeaderPage />
  <v-main>
    <MyAccountOverview />
    <DepositRequests />
    <WithdrawalRequests />
  </v-main>
  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import MyAccountOverview from "@/components/MyAccountOverview";
import DepositRequests from "@/components/DepositRequests.vue";
import WithdrawalRequests from "@/components/WithdrawalRequests.vue";
export default {
  data() {
    return {};
  },
  components: {
    MyAccountOverview,
    HeaderPage,
    FooterPage,
    DepositRequests,
    WithdrawalRequests,
  },
};
</script>
