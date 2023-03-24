<template>
  <ProductForm :item="item" />
</template>
<script>
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
  components: { ProductForm },
  mounted() {
    productService.getProduct(this.id).then((resp) => {
      console.log(resp.data);
      this.item = resp.data;
    });
  },
};
</script>
