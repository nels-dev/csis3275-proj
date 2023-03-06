<template>
  <!-- start of the card -->
  <v-card @click="$router.push('/productdetails/' + item.id)" type="button">
    <v-img :src="imageUrl" height="250" />
    <v-card-title>
      {{ item.name }}
    </v-card-title>
    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-h6"> C${{ item.price?.toFixed(2) }} </v-col>
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
