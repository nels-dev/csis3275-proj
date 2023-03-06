<template>
  <v-tabs v-model="tab" bg-color="secondary">
    <v-tab value="ALL"> ALL</v-tab>

    <v-tab value="LISTED"> LISTED</v-tab>
    <v-tab value="ORDERED">ORDERED</v-tab>
    <v-tab value="SHIPPED">SHIPPED</v-tab>
    <v-tab value="DELIVERED">DELIVERED</v-tab>
    <v-tab value="SOLD">SOLD</v-tab>
    <v-tab value="UNLISTED">UNLISTED</v-tab>
    <v-tab value="DELETED">DELETED</v-tab>
  </v-tabs>

  <v-window v-model="tab">
    <v-window-item value="ALL">
      <SellerProductCardForm :status="'ALL'"
    /></v-window-item>
    <v-window-item value="LISTED">
      <SellerProductCardForm :status="'LISTED'"
    /></v-window-item>
    <v-window-item value="UNLISTED"
      ><SellerProductCardForm :status="'UNLISTED'"
    /></v-window-item>
    <v-window-item value="SOLD">
      <SellerProductCardForm :status="'SOLD'"
    /></v-window-item>
    <v-window-item value="DELETED">
      <SellerProductCardForm :status="'DELETED'" />
    </v-window-item>
    <v-window-item value="ORDERED">
      <SellerProductCardForm :status="'ORDERED'"
    /></v-window-item>
    <v-window-item value="SHIPPED">
      <SellerProductCardForm :status="'SHIPPED'"
    /></v-window-item>
    <v-window-item value="DELIVERED">
      <SellerProductCardForm :status="'DELIVERED'"
    /></v-window-item>
  </v-window>
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
