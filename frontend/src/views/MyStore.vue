<template>
  <HeaderPage />
  <v-main>
    <!-- <div v-for="product in sellerProducts" :key="product.id">
      <h6>{{ product.status }}</h6>
    </div> -->
    <v-container fluid class="container">
      <v-row>
        <v-col>
          <h2>Listing</h2>
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
          cols="auto"
        >
          <SellerProductCard :item="product" />
        </v-col>
      </v-row>
    </v-container>
  </v-main>
  <FooterPage />
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import storeService from "@/services/store.service";
import SellerProductCard from "@/components/SellerProductCard.vue";
export default {
  data() {
    return {
      search: "",
      sellerProducts: [],
    };
  },
  components: { HeaderPage, FooterPage, SellerProductCard },
  mounted() {
    storeService.getSellerProducts().then((res) => {
      this.sellerProducts = res.data;
    });
  },
  computed: {
    filteredProducts() {
      if (!this.search) {
        return this.sellerProducts;
      }
      return this.sellerProducts.filter((product) =>
        product.name.toLowerCase().includes(this.search.toLowerCase())
      );
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
