<template>
  <HeaderPage />
  <v-main>
    <MyAccountOverview />
  </v-main>
  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import MyAccountOverview from "@/components/MyAccountOverview";
export default {
  data() {
    return {};
  },
  components: { MyAccountOverview, HeaderPage, FooterPage },
};
</script>
