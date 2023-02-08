<template>
  <!-- start of the card -->
  <v-card class="mx-auto" max-width="344">
    <v-img
      :src="imageUrl"
      height="200px"
      cover
      @click="$router.push('/productdetails/' + item.id)"
    ></v-img>

    <v-card-title @click="$router.push('/productdetails' + item.id)">
      {{ item.name }}
    </v-card-title>
    <v-card-title> C{{ item.price?.toFixed(2) }} </v-card-title>

    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
  </v-card>
  <!-- end of the card -->
</template>
<script>
export default {
  props: ["item"],
  computed: {
    imageUrl() {
      return process.env.VUE_APP_API_URL + "/images/" + this.item.images[0];
    },
  },
};
</script>
