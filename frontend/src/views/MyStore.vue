<template>
  <HeaderPage />
  <v-main>
    <!-- <div v-for="product in sellerProducts" :key="product.id">
      <h6>{{ product.status }}</h6>
    </div> -->
    <TabForm />
  </v-main>
  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import TabForm from "@/components/TabForm.vue";
export default {
  components: { HeaderPage, FooterPage, TabForm },
  data: () => ({}),
};
</script>
