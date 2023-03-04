<template>
  <!-- start of the card -->
  <v-card>
    <v-img
      :src="imageUrl"
      height="250"
      @click="$router.push('/productdetails/' + item.id)"
      type="button"
    >
      <v-card-title
        v-text="item.status"
        style="
          position: absolute;
          bottom: 0;
          width: 100%;
          background-color: rgba(0, 0, 0, 0.5);
          font-size: 12px;
          color: white;
          line-height: 1.1;
        "
      ></v-card-title>
    </v-img>
    <v-card-title @click="$router.push('/productdetails/' + item.id)">
      {{ item.name }}
    </v-card-title>
    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-h6"> C${{ item.price?.toFixed(2) }} </v-col>
      </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" text>some function</v-btn>
      <v-spacer></v-spacer>
      <v-btn color="secondary" text
        ><font-awesome-icon icon="fa-solid fa-gear"
      /></v-btn>
      <v-btn color="error" text>
        <font-awesome-icon icon="fa-solid fa-trash-can" />
      </v-btn>
    </v-card-actions>
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
