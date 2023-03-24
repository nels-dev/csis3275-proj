<template>
  <HeroSection />
  <v-container class="mb-10">
    <h1 class="my-5">Newest Arrivals</h1>
    <v-row>
      <v-col v-for="item in items" :key="item" cols="12" md="3">
        <ProductCard :item="item" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import productService from "@/services/product.service";
import ProductCard from "@/components/ProductCard.vue";
import HeroSection from "@/components/HeroSection.vue";
export default {
  data() {
    return {
      items: [],
    };
  },

  components: { ProductCard, HeroSection },

  mounted() {
    this.loadData();
  },

  methods: {
    loadData() {
      productService.getLatestProducts().then((resp) => {
        this.items = resp.data;
      });
    },
  },
};
</script>
