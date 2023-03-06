<template>
  <v-main>
    <HeaderPage />
    <HeroSection />
    <v-container>
      <v-row>
        <v-col v-for="item in items" :key="item" cols="12" md="4" xl="3">
          <ProductCard :item="item" />
        </v-col>
      </v-row>
    </v-container>
  </v-main>
  <FooterPage />
</template>

<script>
import userService from "@/services/user.service";
import productService from "@/services/product.service";
import HeaderPage from "@/components/HeaderPage.vue";
import ProductCard from "@/components/ProductCard.vue";
import HeroSection from "@/components/HeroSection.vue";
import FooterPage from "@/components/FooterPage.vue";

export default {
  //  start of slideshow
  data() {
    return {
      user: {
        initials: "",
        fullName: "",
        email: "",
      },
      items: [],
    };
  },

  components: { HeaderPage, ProductCard, HeroSection, FooterPage },

  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.user.fullName = resp.data.preferredUserName;
      this.user.email = resp.data.email;
      this.user.initials = resp.data.preferredUserName[0];
    });
    productService.getProducts().then((resp) => {
      console.log(resp.data);
      this.items = resp.data;
    });
  },
};
</script>
