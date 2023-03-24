<template>
  <MyAccountOverview />
  <DepositRequests />
  <WithdrawalRequests />
</template>

<script>
import MyAccountOverview from "@/components/MyAccountOverview";
import DepositRequests from "@/components/DepositRequests.vue";
import WithdrawalRequests from "@/components/WithdrawalRequests.vue";
export default {
  data() {
    return {};
  },
  components: {
    MyAccountOverview,
    DepositRequests,
    WithdrawalRequests,
  },
};
</script>
