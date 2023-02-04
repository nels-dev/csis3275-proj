<template>
  <!-- start of the slideshow -->
  <v-carousel :show-arrows="false">
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="item.src"
      cover
    ></v-carousel-item>
  </v-carousel>
  <!-- end of the slideshow -->
</template>

<script>
export default {
  //  start of slideshow
  data() {
    return {
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
};
</script>
