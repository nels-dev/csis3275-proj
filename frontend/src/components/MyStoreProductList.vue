<template>
  <v-container>
    <v-row align="center">
      <v-spacer />
      <v-col cols="12">
        <v-text-field
          v-model="search"
          outlined
          append-icon="mdi-magnify"
          label="Search"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4" v-if="this.status === 'ALL'">
        <CreateProductCard
      /></v-col>
      <v-col
        v-for="product in filteredProducts"
        :key="product.id"
        cols="12"
        md="4"
      >
        <SellerProductCard :item="product" />
      </v-col>
    </v-row>

    <!-- <TabForm /> -->
  </v-container>
</template>

<script>
import storeService from "@/services/store.service";
import SellerProductCard from "@/components/SellerProductCard.vue";
import CreateProductCard from "@/components/CreateProductCard.vue";
export default {
  data() {
    return {
      search: "",
      sellerProducts: [],
    };
  },
  components: { SellerProductCard, CreateProductCard },

  mounted() {
    this.loadData();
  },
  props: ["status"],
  computed: {
    filteredProducts() {
      if (!this.search) {
        return this.sellerProducts;
      }
      return this.sellerProducts.filter((product) =>
        product.name.toLowerCase().includes(this.search.toLowerCase())
      );
    },
    lastRefresh() {
      return this.$store.state.mystore.lastRefresh;
    },
  },
  watch: {
    lastRefresh() {
      this.loadData();
    },
  },
  methods: {
    loadData() {
      if (this.status != "ALL") {
        storeService.getSellerProductsByStatus(this.status).then((res) => {
          this.sellerProducts = res.data;
        });
      } else {
        storeService.getSellerProducts().then((res) => {
          this.sellerProducts = res.data;
        });
      }
    },
  },
};
</script>
<style>
.container {
  border: 1px solid #ddd;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  width: 73%;
  padding: 12px;
}
</style>
