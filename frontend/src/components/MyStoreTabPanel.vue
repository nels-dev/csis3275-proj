<template>
  <v-tabs v-model="tab" bg-color="primary" align-tabs="center">
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
      <MyStoreProductList :status="'ALL'" />
    </v-window-item>
    <v-window-item value="LISTED">
      <MyStoreProductList :status="'LISTED'" />
    </v-window-item>
    <v-window-item value="UNLISTED">
      <MyStoreProductList :status="'UNLISTED'" />
    </v-window-item>
    <v-window-item value="SOLD">
      <MyStoreProductList :status="'SOLD'" />
    </v-window-item>
    <v-window-item value="DELETED">
      <MyStoreProductList :status="'DELETED'" />
    </v-window-item>
    <v-window-item value="ORDERED">
      <MyStoreProductList :status="'ORDERED'" />
    </v-window-item>
    <v-window-item value="SHIPPED">
      <MyStoreProductList :status="'SHIPPED'" />
    </v-window-item>
    <v-window-item value="DELIVERED">
      <MyStoreProductList :status="'DELIVERED'" />
    </v-window-item>
  </v-window>
</template>

<script>
import MyStoreProductList from "@/components/MyStoreProductList.vue";
import "@mdi/font/css/materialdesignicons.css";

export default {
  name: "MyStoreTabPanel",
  components: {
    MyStoreProductList,
  },
  data: () => ({
    tab: null,
  }),
};
</script>
<style>
.special-product-card {
  background-color: white;
  border: 1px solid black;
}
</style>
