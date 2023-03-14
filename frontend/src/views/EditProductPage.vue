<template>
  <v-main>
    <HeaderPage />
  </v-main>
  <v-main>
    <EditProductForm :item="item" />
  </v-main>
  <v-main>
    <FooterPage />
  </v-main>
</template>
<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import productService from "@/services/product.service";
import EditProductForm from "@/components/EditProductForm.vue";

export default {
  data: () => ({
    item: [],
  }),
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  components: { HeaderPage, FooterPage, EditProductForm },
  mounted() {
    productService.getProduct(this.id).then((resp) => {
      console.log(resp.data);
      this.item = resp.data;
    });
  },
};
</script>
