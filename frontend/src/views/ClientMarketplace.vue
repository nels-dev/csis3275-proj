<template>
  <!-- <HeroSection /> -->
  <v-container class="mb-10">
    <v-row>
      <v-col>
        <v-text-field
          v-model="search"
          outlined
          append-icon="mdi-magnify"
          label="Search Your Interested Product Here!"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-for="item in filteredItems" :key="item" cols="6" md="4" xl="3">
        <ProductCard :item="item" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import userService from "@/services/user.service";
import productService from "@/services/product.service";
import ProductCard from "@/components/ProductCard.vue";

export default {
  data() {
    return {
      user: {
        initials: "",
        fullName: "",
        email: "",
      },
      items: [],
      search: "",
    };
  },

  components: { ProductCard },

  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.user.fullName = resp.data.preferredUserName;
      this.user.email = resp.data.email;
      this.user.initials = resp.data.preferredUserName[0];
    });
    this.loadData();
  },

  computed: {
    filteredItems() {
      if (!this.search) {
        return this.items;
      }
      return this.items.filter((item) =>
        item.name.toLowerCase().includes(this.search.toLowerCase())
      );
    },
  },

  methods: {
    loadData() {
      productService.getProducts({ search: this.search }).then((resp) => {
        console.log(resp.data);
        this.items = resp.data;
      });
    },
  },

  watch: {
    search() {
      this.loadData();
    },
  },
};
</script>
