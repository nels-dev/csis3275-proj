<template>
  <v-card>
    <v-img
      src="@/assets/plus-solid.svg"
      height="250"
      @click="$router.push('/createproduct')"
      type="button"
      style="fill: red !important"
    >
    </v-img>
    <v-card-title @click="$router.push('/createproduct')">
      Add a new product to SeaSell
    </v-card-title>
    <!-- <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-h6"> C${{ item.price?.toFixed(2) }} </v-col>
      </v-row>
    </v-card-text> -->
    <!-- <v-card-actions>
      <v-btn color="primary" text>some function</v-btn>
      <v-spacer></v-spacer>
      <v-btn color="secondary" text
        ><font-awesome-icon icon="fa-solid fa-gear"
      /></v-btn>
      <v-btn color="error" text>
        <font-awesome-icon icon="fa-solid fa-trash-can" />
      </v-btn>
    </v-card-actions> -->
  </v-card>
  <!-- end of the card -->
</template>
<script>
export default {
  props: ["item"],
};
</script>
