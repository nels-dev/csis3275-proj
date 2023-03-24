<template>
  <v-card
    color="primary"
    height="100%"
    @click="($event) => $router.push('/client/createproduct')"
  >
    <v-card-title align="center" class="pa-15">
      <v-img src="@/assets/plus-solid.svg" aspect-ratio="1.778" type="button">
      </v-img
    ></v-card-title>
    <v-card-text align="center"
      ><span class="text-h6">Add an item</span></v-card-text
    >
  </v-card>
</template>
<script>
export default {
  name: "CreateProductCard",
};
</script>
