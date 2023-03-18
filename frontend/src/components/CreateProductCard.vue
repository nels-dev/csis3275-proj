<template>
  <v-card height="100%" @click="($event) => $router.push('/createproduct')">
    <v-img
      src="@/assets/plus-solid.svg"
      height="250"
      type="button"
      class="pa-15"
    >
    </v-img>
    <v-card-title align="center" class="pa-10"
      >Add product to store</v-card-title
    >
  </v-card>
</template>
<script>
export default {
  props: ["item"],
};
</script>
