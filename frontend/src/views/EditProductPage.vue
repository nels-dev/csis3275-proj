<template>
  <EditProductForm :item="item" />
</template>
<script>
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
  components: { EditProductForm },
  mounted() {
    productService.getProduct(this.id).then((resp) => {
      console.log(resp.data);
      this.item = resp.data;
    });
  },
};
</script>
