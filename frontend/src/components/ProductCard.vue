<template>
  <!-- start of the card -->
  <v-card
    elevation="0"
    @click="$router.push('/client/productdetails/' + item.id)"
    type="button"
  >
    <v-img :src="imageUrl" aspect-ratio="1.7778" />
    <v-card-title>
      <span class="text-info font-weight-bold">{{ item.name }}</span>
    </v-card-title>
    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-subtitle-1"> C${{ item.price?.toFixed(2) }} </v-col>
      </v-row>
    </v-card-text>
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
