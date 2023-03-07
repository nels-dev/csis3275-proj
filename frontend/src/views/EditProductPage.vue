<template>
  <v-main>
    <HeaderPage />
  </v-main>

  <v-main>
    <ProductForm :item="item" />
  </v-main>

  <v-main>
    <FooterPage />
  </v-main>
</template>
<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import ProductForm from "@/components/ProductForm.vue";
import productService from "@/services/product.service";

export default {
  data: () => ({
    item: [],
  }),
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  components: { HeaderPage, FooterPage, ProductForm },
  mounted() {
    productService.getProduct(this.id).then((resp) => {
      console.log(resp.data);
      this.item = resp.data;
    });
  },
};
</script>
