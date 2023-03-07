<template>
  <v-container>
    <v-row align="center">
      <v-col>
        <h2>My Store - {{ this.status }}</h2>
      </v-col>
      <v-col>
        <v-text-field
          v-model="search"
          outlined
          append-icon="mdi-magnify"
          label="Search"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
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
export default {
  data() {
    return {
      search: "",
      sellerProducts: [],
    };
  },
  components: { SellerProductCard },

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
