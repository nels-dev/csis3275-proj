<template>
  <HeaderPage />
  <v-main>
    <router-view />
  </v-main>
  <FooterPage />
</template>
<script>
import FooterPage from "@/components/FooterPage.vue";
import HeaderPage from "@/components/HeaderPage.vue";

export default {
  name: "ClientPage",
  components: { HeaderPage, FooterPage },
};
</script>
