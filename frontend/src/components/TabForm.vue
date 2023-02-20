<template>
  <v-card>
    <v-tabs v-model="tab" bg-color="primary">
      <v-tab value="LISTED"> LISTED</v-tab>
      <v-tab value="UNLISTED">UNLISTED</v-tab>
      <v-tab value="SOLD">SOLD</v-tab>
      <v-tab value="DELETED">DELETED</v-tab>
      <v-tab value="ORDERED">ORDERED</v-tab>
      <v-tab value="SHIPPED">SHIPPED</v-tab>
      <v-tab value="DELIVERED">DELIVERED</v-tab>
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="LISTED"> <SellerProductCardForm /></v-window-item>
        <v-window-item value="UNLISTED">UNLISTED</v-window-item>
        <v-window-item value="SOLD"> SOLD</v-window-item>
        <v-window-item value="DELETED"> DELETED </v-window-item>
        <v-window-item value="ORDERED"> ORDERED </v-window-item>
        <v-window-item value="SHIPPED"> SHIPPED</v-window-item>
        <v-window-item value="DELIVERED"> DELIVERED</v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>
<script>
import SellerProductCardForm from "@/components/SellerProductCardForm.vue";
export default {
  components: { SellerProductCardForm },
  data: () => ({
    tab: null,
  }),
};
</script>
