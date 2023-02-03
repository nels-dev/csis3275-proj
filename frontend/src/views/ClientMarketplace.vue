<template>
  <v-main>
    <HeaderPage />
    <HeroSection />
    <v-container>
      <v-row>
        <v-col v-for="n in 4" :key="n" cols="4">
          <ProductCard />
        </v-col>
      </v-row>
    </v-container>
  </v-main>
  <FooterPage />
</template>

<script>
import userService from "@/services/user.service";
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
      items: [
        {
          src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
        },
        {
          src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
        },
        {
          src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
        },
        {
          src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
        },
      ],
    };
  },

  components: { HeaderPage, ProductCard, HeroSection, FooterPage },

  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.user.fullName = resp.data.preferredUserName;
      this.user.email = resp.data.email;
      this.user.initials = resp.data.preferredUserName[0];
    });
  },
};
</script>
