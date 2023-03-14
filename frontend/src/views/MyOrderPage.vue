<template>
  <HeaderPage />

  <v-main>
    <OrderTable />
  </v-main>

  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import OrderTable from "@/components/OrderTable.vue";

export default {
  data() {
    return {};
  },
  components: {
    HeaderPage,
    FooterPage,
    OrderTable,
  },
};
</script>
