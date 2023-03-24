<template>
  <!-- start of the slideshow -->
  <v-carousel :show-arrows="false" hide-delimiters cycle>
    <v-carousel-item
      :src="require(`../assets/banner1.png`)"
      cover
    ></v-carousel-item>
    <v-carousel-item
      :src="require(`../assets/banner2.png`)"
      cover
    ></v-carousel-item>
  </v-carousel>
  <!-- end of the slideshow -->
</template>

<script>
<img src="@/assets/banner1.png" />;
export default {
  //  start of slideshow
};
</script>
