<template>
  <HeaderPage />
  <v-main>
    <ListingTable />
  </v-main>
  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import ListingTable from "@/components/ListingTable.vue";
import FooterPage from "@/components/FooterPage.vue";
export default {
  data() {
    return {};
  },
  components: { HeaderPage, ListingTable, FooterPage },
};
</script>
